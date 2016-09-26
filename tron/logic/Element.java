package tron.logic;

import linkedlist.quadruple.QuadrupleNode;

public class Element implements Constants {
	
	protected String type;
	protected QuadrupleNode<Element> matrixPosition;
	
	public Element(String type, QuadrupleNode<Element> matrixPosition) {
		this.type = type;
		setMatrixPosition(matrixPosition);
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setMatrixPosition(QuadrupleNode<Element> matrixPosition) {
		if(this.matrixPosition == null && matrixPosition != null) {
			this.matrixPosition = matrixPosition;
			matrixPosition.setData(this);
		}
		else if(matrixPosition == null) {
			this.matrixPosition.setData(null);
			this.matrixPosition = null;
		}
		else {
			this.matrixPosition.setData(null);
			this.matrixPosition = matrixPosition;
			matrixPosition.setData(this);
		}
		
	}
	
	public QuadrupleNode<Element> getMatrixPosition() {
		return matrixPosition;
	}
		
}
