package tron.logic;

import linkedlist.quadruple.QuadrupleNode;
import linkedlist.queue.Queue;
import linkedlist.simple.List;
import linkedlist.simple.Node;
import linkedlist.stack.Stack;
import tron.misc.RandomGenerator;

public class LightBike extends Element {
	
	private int id;
	private Boolean active;
	private int speed;
	private float fuel;
	private String direction;
	private List<LightTrail> trailList;
	private Queue<Element> itemQueue;
	private Stack<Element> powerUpStack;
	//gameCoordinator
	
	public LightBike(QuadrupleNode<Element> matrixPosition, int id) {
		
		super(BIKE,matrixPosition);
		
		this.id = id;
		this.active = true;
		this.speed = RandomGenerator.nextInt(MINIMUM_SPEED,MAXIMUM_SPEED);
		this.fuel = RandomGenerator.nextFloat(MINIMUM_FUEL,MAXIMUM_FUEL);
		//fuel random
		// direction random
		createTrail(INITIAL_TRAIL_LENGHT);
	}
	
	public LightBike(QuadrupleNode<Element> matrixPosition, int id, 
			int speed, float fuel, String direction, int trailLenght) {
		
		super(BIKE,matrixPosition);
		
		this.id = id;
		this.active = true;
		this.speed = speed;
		this.fuel = fuel;
		this.direction = direction;
		createTrail(trailLenght);
	}

	private void createTrail(int trailLenght) {
		
		List<LightTrail> newTrailList = new List<LightTrail>();
		QuadrupleNode<Element> matrixPointer = matrixPosition.getNode(getOppositeDirection());
		
		for (int counter = 0; counter < trailLenght; counter++) {
			LightTrail lightTrail = new LightTrail(id,matrixPointer);
			newTrailList.insertTail(lightTrail);
			
			matrixPointer = matrixPointer.getNode(getOppositeDirection());
		}
		trailList =  newTrailList;
		
	}
	 
	public void move() {
		for(int counter = 0; counter < speed; counter++) {
			
			if(outOfBounds() || unableToMove() || outOfFuel()) {
				destroyBike();
				//informGameCoordinator
				//spreadItems();
				//spreadPowerUps();
			}
			else {
				collectUpgradesInPath();
				moveBikeAndTrail();
				consumeFuel();
			}
		}	
	}
	
	private boolean outOfBounds() {
		return matrixPosition.getNode(direction) == null;
	}

	private boolean unableToMove() {
		boolean result = false;
		if (matrixPosition.getNode(direction).getData() != null) {
			result = (matrixPosition.getNode(direction).getData().getType() == MINE ||
					matrixPosition.getNode(direction).getData().getType() == BIKE ||
					matrixPosition.getNode(direction).getData().getType() == TRAIL); 
		}
		return result;
	}
	
	private boolean outOfFuel() {
		return (fuel <= 0);
	}
	
	private void collectUpgradesInPath() {
		if(matrixPosition.getNode(direction).getData() != null) {
			if(matrixPosition.getNode(direction).getData().getType() == FUEL_CELL) {
				itemQueue.enqueue(matrixPosition.getNode(direction).getData());
				matrixPosition.getNode(direction).getData().setMatrixPosition(null);
				increaseFuel();
			}
			else if(matrixPosition.getNode(direction).getData().getType() == SHIELD) {
				powerUpStack.push(matrixPosition.getNode(direction).getData());
				matrixPosition.getNode(direction).getData().setMatrixPosition(null);
			}
			else if(matrixPosition.getNode(direction).getData().getType() == HYPER_SPEED) {
				powerUpStack.push(matrixPosition.getNode(direction).getData());
				matrixPosition.getNode(direction).getData().setMatrixPosition(null);
			}
		}
	}
	
	private void increaseFuel() {
		fuel += RandomGenerator.nextInt(MINIMUM_FUEL, MAXIMUM_FUEL);
	}
	 
	private void moveBikeAndTrail() {
		
		if(matrixPosition.getNode(direction).getData() != null && 
				matrixPosition.getNode(direction).getData().getType() == TRAIL_UPGRADE){
			itemQueue.enqueue(matrixPosition.getNode(direction).getData());
			matrixPosition.getNode(direction).getData().setMatrixPosition(null);
		
			QuadrupleNode<Element> lightTrailNextMatrixPosition = matrixPosition;
			
			matrixPosition = matrixPosition.getNode(direction);
			
			LightTrail lightTrail = new LightTrail(id, lightTrailNextMatrixPosition);
			trailList.insertHead(lightTrail);

		} else {
		
			QuadrupleNode<Element> lightTrailNextMatrixPosition = matrixPosition;
		
			matrixPosition = matrixPosition.getNode(direction);
		
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
	 
	private void destroyBike() {
		active = false;
		matrixPosition.setData(null);
		Node<LightTrail> nodePointer = trailList.getHead();
		while(nodePointer != null) {
			nodePointer.getData().getMatrixPosition().setData(null);
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

	public Stack<Element> getPowerUpStack() {
		return powerUpStack;
	}

	public void setPowerUpStack(Stack<Element> powerUpStack) {
		this.powerUpStack = powerUpStack;
	}
	 	
}

