package tron.logic;

import linkedlist.simple.List;

public interface Logic {

	//public setMatrix(int rowLenght, int columnLenght);
	public void modifyDirections(List<String> list);
	public void update();
	public String[][] generateMatrix();
	
}
