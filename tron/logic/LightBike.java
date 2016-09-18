package tron.logic;

import linkedlist.simple.List;
import linkedlist.simple.Node;

public class LightBike extends Element {
	
	 private String direction;
	 private List<LightTrail> trail;
	 
	 public void move() {
		 if (direction.equals(NORTH) && matrixPosition.getNorthNode() != null) {
			 moveTrail(direction);
		 }
		 else if (direction.equals(EAST) && matrixPosition.getEastNode() != null) {
			 moveTrail(direction);
		 }
		 
		 else if (direction.equals(SOUTH) && matrixPosition.getSouthNode() != null) {
			 moveTrail(direction);
		 }
		 
		 else if (direction.equals(WEST) && matrixPosition.getWestNode() != null) {
			 moveTrail(direction);
		 }
		 else {
			 destroyBike();
		 }
	 }
	 
	 public void moveTrail(String orientation) {
		 
	 }
	 
	 public void destroyBike() {
		 matrixPosition.setData(null);
		 Node<LightTrail> nodePointer = trail.getHead();
		 while(nodePointer != null) {
			 nodePointer.getData().getMatrixPosition().setData(null);
			 nodePointer = nodePointer.getNextNode();
		 }
	 }

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<LightTrail> getTrail() {
		return trail;
	}

	public void setTrail(List<LightTrail> trail) {
		this.trail = trail;
	}
	 	
}

