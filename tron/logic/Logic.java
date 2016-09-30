package tron.logic;

public interface Logic {

	public void setMatrixSize(int rowLenght, int columnLenght);
	public void generateBikes();
	
	public void modifyDirections(String[] directionsArray);
	public void update();
	public String[][] generateMatrix();
	
}
