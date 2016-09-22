package tron.logic;

import linkedlist.quadruple.QuadrupleNode;
import linkedlist.simple.List;
import linkedlist.simple.Node;

public class LightBike extends Element implements Constants {
	
	private int id;
	private Boolean active;
	private int speed;
	private float fuel;
	private String direction;
	private List<LightTrail> trailList;
	
	public LightBike(QuadrupleNode<Element> matrixPosition, int id) {
		this.type = BIKE;
		this.matrixPosition = matrixPosition;
		
		this.id = id;
		this.active = true;
		//speed random
		//fuel random
		// direction random
		createTrail(INITIAL_TRAIL_LENGHT);
	}
	
	public LightBike(QuadrupleNode<Element> matrixPosition, int id, 
			int speed, float fuel, String direction, int trailLenght) {
		this.type = BIKE;
		this.matrixPosition = matrixPosition;
		
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
			if (ableToMove()) {
				moveBikeAndTrail();
				consumeFuel();
			}
			else {
				destroyBike();
				//spreadItems();
				//spreadPowerUps();
			}
		}	
	}

	private boolean ableToMove() {
		return (matrixPosition.getNode(direction) != null &&
				matrixPosition.getNode(direction).getData() == null &&
				fuel > 0);
	}
	 
	private void moveBikeAndTrail() {
		
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
	 	
}

