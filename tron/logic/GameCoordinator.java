package tron.logic;

import linkedlist.quadruple.QuadrupleList;
import linkedlist.quadruple.QuadrupleNode;
import linkedlist.simple.List;
import linkedlist.simple.Node;
import tron.misc.RandomGenerator;

public class GameCoordinator implements Logic, Constants {
	
	private boolean gameOver;
	private QuadrupleList<Element> matrix;
	private List<LightBike> bikesList;
	
	public void setMatrixSize(int rowLenght, int columnLenght) {
		setMatrix(new QuadrupleList<Element>(rowLenght,columnLenght));
	}
	
	public void generateBikes() {
		generateBikeList(4);
	}
	
	private List<LightBike> generateBikeList(int numberOfBikes) {
		List<LightBike> lightBikeList = new List<>();
		for(int counter = 0; counter < numberOfBikes; counter++) {
			lightBikeList.insertTail(new LightBike(randomBikeMatrixPosition(),counter+1,this.matrix));
		}
		return lightBikeList;
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
	
	@Override
	public void modifyDirections(String[] directionsArray) {
		Node<LightBike> bikeNode = bikesList.getHead();
		for(int index = 0; index < directionsArray.length; index++) {
			if (bikeNode != null) {
				bikeNode.getData().modifyDirection(directionsArray[index]);
				bikeNode = bikeNode.getNextNode();
			}
		}	
	}

	private QuadrupleNode<Element> randomBikeMatrixPosition() {
		QuadrupleNode<Element> randomMatrixPosition = null;
		while(randomMatrixPosition == null) {
			int row = RandomGenerator.nextInt(4, matrix.getRows()-4);
			int column = RandomGenerator.nextInt(4, matrix.getColumns()-4);
			QuadrupleNode<Element> tmpMatrixPosition = matrix.getNode(row, column);
			if(tmpMatrixPosition.getData() == null) {
				randomMatrixPosition = tmpMatrixPosition;
				}
			}
		return randomMatrixPosition;
	}
	
	public void update() {
		moveBikes();
		generateElements();
	}
	
	private void moveBikes() {
		Node<LightBike> nodePointer = bikesList.getHead();
		while(nodePointer != null) {
			if(nodePointer.getData().isActive()) {
				nodePointer.getData().move();
			}
			nodePointer = nodePointer.getNextNode();
		}
	}
	
	private void generateElements() {
		if(RandomGenerator.porcentageChance(5)) {
			int randomInt = RandomGenerator.nextInt(1, 5);
			if(randomInt == 1) {
				generateElement(FUEL_CELL);
			} else if(randomInt == 2) {
				generateElement(TRAIL_UPGRADE);
			} else if(randomInt == 3) {
				generateElement(MINE);
			} else if(randomInt == 4) {
				generateElement(SHIELD);
			} else {
				generateElement(HYPER_SPEED);
			}
		}
	}
	
	private void generateElement(String type) {
		new Element(type, randomMatrixPosition());
	}

	public void checkState() {
		Node<LightBike> nodePointer = bikesList.getHead();
		boolean gameOver = true;
		boolean bikeActive = false;
		while(nodePointer != null) {
			if(nodePointer.getData().isActive()) {
				if(bikeActive) {
					gameOver = false;
					break;
				}
				else {
					bikeActive = true;
				}
			}
			nodePointer = nodePointer.getNextNode();
		}
		this.gameOver = gameOver;
	}

	public String[][] generateMatrix() {
		Element[][] elementMatrix = (Element[][])matrix.generateMatrix();
		String[][] stringMatrix = new String[matrix.getRows()][matrix.getColumns()];
		for(int row = 0; row < matrix.getRows()-1; row++) {
			for(int column = 0; column < matrix.getColumns()-1; column++) {
				Element currentElement = elementMatrix[row][column];
				if(currentElement != null) {
					if(currentElement.getType() == BIKE) {
						stringMatrix[row][column] = currentElement.getType() + 
								((LightBike)currentElement).getId();
					} else if (currentElement.getType() == TRAIL) {
						stringMatrix[row][column] = currentElement.getType() + 
								((LightTrail)currentElement).getId();
					} else {
						stringMatrix[row][column] = currentElement.getType();
					}
				}	
			} 
		}	
		return stringMatrix;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public QuadrupleList<Element> getMatrix() {
		return matrix;
	}

	public void setMatrix(QuadrupleList<Element> matrix) {
		this.matrix = matrix;
	}

	public List<LightBike> getBikesList() {
		return bikesList;
	}

	public void setBikesList(List<LightBike> bikesList) {
		this.bikesList = bikesList;
	}
	
}
