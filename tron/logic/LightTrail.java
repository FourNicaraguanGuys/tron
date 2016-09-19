package tron.logic;

import linkedlist.quadruple.QuadrupleNode;

public class LightTrail extends Element {
	
	private int id;
	
	public LightTrail(int id, QuadrupleNode<Element> matrixPosition) {
		this.id = id;
		this.matrixPosition = matrixPosition;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
