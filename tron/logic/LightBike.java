package tron.logic;

import linkedlist.quadruple.QuadrupleList;
import linkedlist.quadruple.QuadrupleNode;
import linkedlist.queue.Queue;
import linkedlist.simple.List;
import linkedlist.simple.Node;
import linkedlist.stack.Stack;
import tron.misc.RandomGenerator;

public class LightBike extends Element {
	
	private Boolean active;
	private int id;
	private int userId;
	private QuadrupleList<Element> matrix;
	
	private int speed;
	private float fuel;
	private String direction;
	private List<LightTrail> trailList;
	
	private Queue<Element> itemQueue;
	private Queue<HyperSpeed> hyperSpeedQueue;
	private Stack<Element> shieldStack;

	public LightBike(QuadrupleNode<Element> matrixPosition, int id, QuadrupleList<Element> matrix) {
		super(BIKE,matrixPosition);
		this.id = id;
		this.userId = DEFAULT_USER_ID;
		this.active = true;
		this.setMatrix(matrix);
		this.speed = RandomGenerator.nextInt(MINIMUM_SPEED,MAXIMUM_SPEED);
		this.fuel = RandomGenerator.nextFloat(MINIMUM_FUEL,MAXIMUM_FUEL);
		this.direction = getRandomDirection();
		this.itemQueue = new Queue<Element>();
		this.hyperSpeedQueue = new Queue<HyperSpeed>();
		this.shieldStack = new Stack<Element>();
		createTrail(INITIAL_TRAIL_LENGHT);
	}
	
	public LightBike(QuadrupleNode<Element> matrixPosition, int id, QuadrupleList<Element> matrix, int speed,
			float fuel, String direction, int trailLenght) {
		super(BIKE,matrixPosition);
		this.id = id;
		this.userId = DEFAULT_USER_ID + 1;
		this.active = true;
		this.setMatrix(matrix);
		this.speed = speed;
		this.fuel = fuel;
		this.direction = direction;
		this.itemQueue = new Queue<Element>();
		this.hyperSpeedQueue = new Queue<HyperSpeed>();
		this.shieldStack = new Stack<Element>();
		createTrail(trailLenght);
	}

	private void createTrail(int trailLenght) {
		List<LightTrail> newTrailList = new List<LightTrail>();
		QuadrupleNode<Element> matrixPointer = matrixPosition.getNode(getOppositeDirection());
		for (int counter = 0; counter < trailLenght; counter++) {
			LightTrail lightTrail = new LightTrail(id, matrixPointer);
			newTrailList.insertTail(lightTrail);
			matrixPointer = matrixPointer.getNode(getOppositeDirection());
		}
		trailList =  newTrailList;
	}
	 
	public void move() {
		if(userId == DEFAULT_USER_ID) {
			analizeDirection();
		}
		
		for(int counter = 0; counter < speed; counter++) {
			if(isActive()) {
				if(outOfBounds() || bikeCollision() || mineInPath() || unableToMove() || outOfFuel()) {
					destroyBike();
					spreadItems();
					spreadShields();
					spreadHyperSpeed();
					break;
				}
				else {
					collectUpgradesInPath();
					moveBikeAndTrail();
					consumeHyperSpeed();
					consumeFuel(); 
				}
			}
			else {
				break;
			}
		}	
	}
	
	private void analizeDirection() {
		if(matrixPosition.getNode(direction) == null || 
				matrixPosition.getNode(direction).getData() != null) {
			moveRight();
		}
		else if(matrixPosition.getNode(direction) == null || 
				matrixPosition.getNode(direction).getData() != null) {
				moveLeft();
				moveLeft();
		}
	}

	public void spreadHyperSpeed() {
		while(hyperSpeedQueue.peek() != null) {
			hyperSpeedQueue.peek().resetMovementsLeft();
			hyperSpeedQueue.dequeue().setMatrixPosition(randomMatrixPosition());
		}
	}

	private void consumeHyperSpeed() {
		if(hyperSpeedQueue.peek() != null) {
			hyperSpeedQueue.peek().decreaseMovementsLeft();
			if(hyperSpeedQueue.peek().getMovementsLeft() <= 0) {
				speed -= hyperSpeedQueue.peek().getSpeedBoost();
				hyperSpeedQueue.dequeue();
			}
		}
	}

	public void spreadShields() {
		while(shieldStack.peek() != null) {
			shieldStack.pop().setMatrixPosition(randomMatrixPosition());
		}
	}

	public void spreadItems() {
		while(itemQueue.peek() != null) {
			itemQueue.dequeue().setMatrixPosition(randomMatrixPosition());
		}
	}

	public void modifyDirection(String direction) {
		 if(direction.equals(RIGHT)) {
			 moveRight(); 
		 }
		 else if(direction.equals(LEFT)) {
			 moveLeft();
		 }
	}
	
	private void moveRight() {
		if(this.direction.equals(NORTH)) {
			this.direction = EAST;
		} else if(this.direction.equals(EAST)) {
			this.direction = SOUTH;
		} else if(this.direction.equals(SOUTH)) {
			this.direction = WEST;
		} else {
			this.direction = NORTH;
		}
	}
	
	private void moveLeft() {
		if(this.direction.equals(NORTH)) {
			this.direction = WEST;
		} else if(this.direction.equals(WEST)) {
			this.direction = SOUTH;
		} else if(this.direction.equals(SOUTH)) {
			this.direction = EAST;
		} else {
			this.direction = NORTH;
		}
	}
	
	private boolean outOfBounds() {
		return getNextMatrixPosition() == null;
	}

	private boolean unableToMove() {	
		Boolean unableToMove = false;
		if(getNextMatrixPositionData() != null &&
				getNextMatrixPositionData().getType().equals(TRAIL)) {
			if(shieldStack.peek() != null) {
				shieldStack.pop();
			} else {
				unableToMove = true;
			}
		}
		return unableToMove;
	}
	
	private boolean mineInPath() {
		boolean result = false;
		if (getNextMatrixPositionData() != null && 
				getNextMatrixPositionData().getType().equals(MINE)) {
			if(shieldStack.peek() != null) {
				shieldStack.pop();
			} else {
				result = true;
			}
			getNextMatrixPositionData().setMatrixPosition(null);
		}
		return result;
	}
	
	private boolean outOfFuel() {
		return (fuel <= 0);
	}
	
	private boolean bikeCollision() {
		boolean collision = false;
		if(getNextMatrixPositionData() != null &&
				getNextMatrixPositionData().getType().equals(BIKE)) {
			LightBike.class.cast(getNextMatrixPosition().getData()).destroyBike();
			collision = true;
		}
		return collision;
	}
	
	private void collectUpgradesInPath() {
		if(getNextMatrixPositionData() != null &&
				getNextMatrixPositionData().getType().equals(FUEL_CELL)) {
			itemQueue.enqueue(getNextMatrixPositionData());
			getNextMatrixPositionData().setMatrixPosition(null);
			increaseFuel();
		}
		else if(getNextMatrixPositionData() != null &&
				getNextMatrixPositionData().getType().equals(SHIELD)) {
			shieldStack.push(getNextMatrixPositionData());
			getNextMatrixPositionData().setMatrixPosition(null);
		}
		else if(getNextMatrixPositionData() != null &&
				getNextMatrixPositionData().getType().equals(HYPER_SPEED)) {
			hyperSpeedQueue.enqueue(HyperSpeed.class.cast(getNextMatrixPositionData()));
			speed += hyperSpeedQueue.peek().getSpeedBoost();
			getNextMatrixPositionData().setMatrixPosition(null);
		}	
	}
	
	private void increaseFuel() {
		fuel += RandomGenerator.nextInt(MINIMUM_FUEL, MAXIMUM_FUEL);
	}
	 
	private void moveBikeAndTrail() {
		
		if(getNextMatrixPositionData() != null &&
				getNextMatrixPositionData().getType().equals(TRAIL_UPGRADE)){
			
			itemQueue.enqueue(getNextMatrixPositionData());
			getNextMatrixPositionData().setMatrixPosition(null);
		
			QuadrupleNode<Element> lightBikePreviousPosition = matrixPosition;
			
			setMatrixPosition(getNextMatrixPosition());
			
			LightTrail lightTrail = new LightTrail(id, lightBikePreviousPosition);
			trailList.insertHead(lightTrail);

		} else {
		
			QuadrupleNode<Element> lightTrailNextMatrixPosition = matrixPosition;
		
			setMatrixPosition(getNextMatrixPosition());
		
			Node<LightTrail> nodePointer = trailList.getHead();
			QuadrupleNode<Element> lightTrailCurrentMatrixPosition = null;
		
			while(nodePointer != null) {
				lightTrailCurrentMatrixPosition = nodePointer.getData().getMatrixPosition();
				nodePointer.getData().setMatrixPosition(lightTrailNextMatrixPosition);
				lightTrailNextMatrixPosition = lightTrailCurrentMatrixPosition;
				
				nodePointer = nodePointer.getNextNode();
			}	
		}
	}
	
	private void consumeFuel() {
		fuel -= FUEL_CONSUMPTION_RATE;
	}
	 
	public void destroyBike() {
		setActive(false);
		setMatrixPosition(null);
		Node<LightTrail> nodePointer = trailList.getHead();
		while(nodePointer != null) {
			nodePointer.getData().setMatrixPosition(null);
			nodePointer = nodePointer.getNextNode();
		}	
	}

	private String getOppositeDirection() {
		String orientation = null;
		if(direction.equals(NORTH)) {
			orientation = SOUTH;
		}
		else if(direction.equals(EAST)) {
			orientation = WEST;
		}
		else if(direction.equals(SOUTH)) {
			orientation = NORTH;
		}
		else {
			orientation = EAST;
		}
		return orientation;
	}
	
	private QuadrupleNode<Element> getNextMatrixPosition() {
		return matrixPosition.getNode(direction);
	}
	
	private Element getNextMatrixPositionData() {
		return matrixPosition.getNode(direction).getData();
	}
	
	private String getRandomDirection() {
		int number = RandomGenerator.nextInt(1,4);
		String orientation = null;
		if(number == 1) {
			orientation = NORTH;
		} else if(number == 2) {
			orientation = EAST;
		} else if(number == 3) {
			orientation = SOUTH;
		} else {
			orientation = WEST;
		}
		return orientation;
	}
	
	private QuadrupleNode<Element> randomMatrixPosition() {
		QuadrupleNode<Element> randomMatrixPosition = null;
		while(randomMatrixPosition == null) {
			int row = RandomGenerator.nextInt(1, matrix.getRows());
			int column = RandomGenerator.nextInt(1, matrix.getColumns());
			QuadrupleNode<Element> tmpMatrixPosition = matrix.getNode(row, column);
			if(tmpMatrixPosition.getData() == null) {
				randomMatrixPosition = tmpMatrixPosition;
				}
			}
		return randomMatrixPosition;
	}
	
	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDirection() {
		return direction;	
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<LightTrail> getTrailList() {
		return trailList;
	}

	public void setTrailList(List<LightTrail> trail) {
		this.trailList = trail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public float getFuel() {
		return fuel;
	}

	public void setFuel(float fuel) {
		this.fuel = fuel;
	}

	public Queue<Element> getItemQueue() {
		return itemQueue;
	}

	public void setItemQueue(Queue<Element> itemQueue) {
		this.itemQueue = itemQueue;
	}

	public QuadrupleList<Element> getMatrix() {
		return matrix;
	}

	public void setMatrix(QuadrupleList<Element> matrix) {
		this.matrix = matrix;
	}

	public Queue<HyperSpeed> getHyperSpeedQueue() {
		return hyperSpeedQueue;
	}

	public void setHyperSpeedQueue(Queue<HyperSpeed> hyperSpeedQueue) {
		this.hyperSpeedQueue = hyperSpeedQueue;
	}

	public Stack<Element> getShieldStack() {
		return shieldStack;
	}

	public void setShieldStack(Stack<Element> shieldStack) {
		this.shieldStack = shieldStack;
	}
	 	
}
