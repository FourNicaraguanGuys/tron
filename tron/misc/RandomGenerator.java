package tron.misc;

import java.util.Random;

public class RandomGenerator {
	
	/**
	 * Retorna un int entre mininmumNumber y maximumNumber inclusive
	 *
	 * @param minimumNumber 
	 * @param maximumNumber
	 * @return int
	 */
	public static int nextInt(int minimumNumber, int maximumNumber) {

		Random rand = new Random(); 
	    int randomNum = rand.nextInt((maximumNumber - minimumNumber) + 1) + minimumNumber;

	    return randomNum;
	}

	/**
	 * Retorna un float entre mininmumNumber y maximumNumber inclusive
	 *
	 * @param minimumNumber 
	 * @param maximumNumber
	 * @return float
	 */
	public static float nextFloat(int minimumNumber, int maximumNumber) {

		Random rand = new Random(); 
	    float randomNum = rand.nextInt((maximumNumber - minimumNumber) + 1) + minimumNumber;

	    return randomNum;
	}
	
}
