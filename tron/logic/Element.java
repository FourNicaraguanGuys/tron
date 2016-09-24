package tron.logic;

import linkedlist.quadruple.QuadrupleNode;

public class Element implements Constants {
	
	protected String type;
	protected QuadrupleNode<Element> matrixPosition;
	
	public Element(String type, QuadrupleNode<Element> matrixPosition) {
		this.type = type;
		this.matrixPosition = matrixPosition;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public QuadrupleNode<Element> getMatrixPosition() {
		return matrixPosition;
	}
	public void setMatrixPosition(QuadrupleNode<Element> matrixPosition) {
		this.matrixPosition = matrixPosition;
	}
	
}
