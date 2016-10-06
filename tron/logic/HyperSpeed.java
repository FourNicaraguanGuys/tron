package tron.logic;

import linkedlist.quadruple.QuadrupleNode;
import tron.misc.RandomGenerator;

public class HyperSpeed extends Element {
	
	private int movementsLeft;
	private int speedBoost;

	public HyperSpeed(QuadrupleNode<Element> matrixPosition) {
		super(HYPER_SPEED, matrixPosition);
		setMovementsLeft(DEFAULT_HYPER_SPEED_MOVEMENTS);
		setSpeedBoost(RandomGenerator.nextInt(MINIMUM_SPEED_BOOST,MAXIMUM_SPEED_BOOST));
	}
	
	public void decreaseMovementsLeft() {
		movementsLeft--;
	}
	
	public void resetMovementsLeft() {
		setMovementsLeft(DEFAULT_HYPER_SPEED_MOVEMENTS);
	}

	public int getMovementsLeft() {
		return movementsLeft;
	}

	public void setMovementsLeft(int movementsLeft) {
		this.movementsLeft = movementsLeft;
	}

	public int getSpeedBoost() {
		return speedBoost;
	}

	public void setSpeedBoost(int speedBoost) {
		this.speedBoost = speedBoost;
	}

}
