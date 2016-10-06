package tron.logic;

import linkedlist.quadruple.QuadrupleList;
import linkedlist.quadruple.QuadrupleNode;
import linkedlist.simple.List;
import linkedlist.simple.Node;
import tron.misc.RandomGenerator;
import tron.server.Constants;

public class Game implements Constants {
	
	private boolean gameOver;
	private int numberOfPlayers;
	private QuadrupleList<Element> matrix;
	private List<LightBike> bikesList;
	private int playersUpdated;
	private boolean updated;
	
	public Game(int rowLenght, int columnLenght) {
		setGameOver(false);
		setNumberOfPlayers(0);
		setMatrixSize(rowLenght,columnLenght);
		setBikesList(generateBikeList(DEFAULT_NUMBER_OF_BIKES));
		setPlayersUpdated(0);
		setUpdated(false);
	}
	
	public void setMatrixSize(int rowLenght, int columnLenght) {
		setMatrix(new QuadrupleList<Element>(rowLenght,columnLenght));
	}
	
	private List<LightBike> generateBikeList(int numberOfBikes) {
		List<LightBike> lightBikeList = new List<>();
		for(int counter = 0; counter < numberOfBikes; counter++) {
			lightBikeList.insertTail(new LightBike(randomBikeMatrixPosition(),counter+1,this.matrix));
		}
		return lightBikeList;
	}
	
	public boolean assignBikeToPlayer(int userId) {
		Node<LightBike> pointer = bikesList.getHead();
		boolean playerAdded = false;
		while(pointer!=null) {
			if(pointer.getData().getUserId() < 0) {
				pointer.getData().setUserId(userId);
				numberOfPlayers++;
				playerAdded = true;
				break;
			}
			pointer = pointer.getNextNode();
		}
		return playerAdded;
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
	
	public void modifyDirection(int bikeId, String direction) {
		Node<LightBike> bikeNode = bikesList.getHead();
		while(bikeNode != null) {
			if(bikeNode.getData().getId() == bikeId) {
				bikeNode.getData().setDirection(direction);
				playersUpdated++;
				break;
			}
			bikeNode = bikeNode.getNextNode();
		}
		
		if(numberOfPlayers <= playersUpdated) {
			update();
			setUpdated(true);
		}
	}
	
	public void outdate() {
		setUpdated(false);
		setPlayersUpdated(0);
	}
	
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
		checkState();
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
				generateHyperSpeed();
			}
		}
	}
	
	private void generateElement(String type) {
		new Element(type, randomMatrixPosition());
	}
	
	private void generateHyperSpeed() {
		new HyperSpeed(randomMatrixPosition() );
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
		setGameOver(gameOver);
	}

	public String[][] generateMatrix() {
		Object[][] elementMatrix = matrix.generateMatrix();
		String[][] stringMatrix = new String[matrix.getRows()][matrix.getColumns()];
		for(int row = 0; row < matrix.getRows()-1; row++) {
			for(int column = 0; column < matrix.getColumns()-1; column++) {
				Element currentElement = Element.class.cast(elementMatrix[row][column]);
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

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public int getPlayersUpdated() {
		return playersUpdated;
	}

	public void setPlayersUpdated(int playersUpdated) {
		this.playersUpdated = playersUpdated;
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	
}
