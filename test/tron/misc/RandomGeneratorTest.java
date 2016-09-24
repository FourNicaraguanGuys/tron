package test.tron.misc;

import static org.junit.Assert.*;
import org.junit.Test;

import tron.misc.RandomGenerator;

public class RandomGeneratorTest {
	
	@Test
	public void testNextInt() {
		int min = 0, max = 10;
		int randomNum = RandomGenerator.nextInt(min, max);
		assertTrue(min <= randomNum && randomNum <= max);
	}
	
	@Test
	public void testNextFloat() {
		int min = 0, max = 10;
		float randomNum = RandomGenerator.nextInt(min, max);
		assertTrue(min <= randomNum && randomNum <= max);
	}

}
