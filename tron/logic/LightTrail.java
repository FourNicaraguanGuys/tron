package tron.logic;

import linkedlist.quadruple.QuadrupleNode;

public class LightTrail extends Element {
	
	private int id;
	
	public LightTrail(int id, QuadrupleNode<Element> matrixPosition) {	
		super(TRAIL,matrixPosition);
		this.id = id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
