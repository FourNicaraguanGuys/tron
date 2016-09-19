package linkedlist.quadruple;

public class QuadrupleNode<T> implements Constants {
	
	private T data = null;
	private QuadrupleNode<T> northNode = null;
	private QuadrupleNode<T> eastNode = null;
	private QuadrupleNode<T> southNode= null;
	private QuadrupleNode<T> westNode= null;
	
	public QuadrupleNode() {}
	
	public QuadrupleNode(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public QuadrupleNode<T> getNode(String direction) {
		QuadrupleNode<T> node = null;
		if (direction.equals(NORTH)) {
			node = northNode;
		}
		else if (direction.equals(EAST)) {
			node = eastNode;
		}
		else if (direction.equals(SOUTH)) {
			node = southNode;
		}
		else {
			node = westNode;
		}
		return node;
	}

	public QuadrupleNode<T> getNorthNode() {
		return northNode;
	}

	public void setNorthNode(QuadrupleNode<T> northNode) {
		this.northNode = northNode;
	}

	public QuadrupleNode<T> getEastNode() {
		return eastNode;
	}

	public void setEastNode(QuadrupleNode<T> eastNode) {
		this.eastNode = eastNode;
	}

	public QuadrupleNode<T> getSouthNode() {
		return southNode;
	}

	public void setSouthNode(QuadrupleNode<T> southNode) {
		this.southNode = southNode;
	}

	public QuadrupleNode<T> getWestNode() {
		return westNode;
	}

	public void setWestNode(QuadrupleNode<T> westNode) {
		this.westNode = westNode;
	}
	
}
