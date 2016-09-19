package tron.logic;

import linkedlist.quadruple.QuadrupleNode;
import linkedlist.simple.List;
import linkedlist.simple.Node;

public class LightBike extends Element {
	
	private int id;
	private Boolean active;
	private String direction;
	private List<LightTrail> trailList;
	
	public LightBike(int id, String direction, int trailLenght) {
		this.id = id;
		this.direction = direction;
		this.active = true;
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
		if (ableToMove()) {
			moveBikeAndTrail();
		}
		else {
			destroyBike();
		}
	}

	private boolean ableToMove() {
		return (matrixPosition.getNode(direction) != null &&
				matrixPosition.getNode(direction).getData() == null);
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
	 	
}

