package test.tron.logic;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import linkedlist.quadruple.QuadrupleList;
import tron.logic.Element;
import tron.logic.LightBike;

public class LightBikeTest {
	
	@Test
	public void testSingleMove() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10);
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, 1 ,1,"east",3);
		lightBike.move();
		assertEquals(matrix.getNode(5, 6), lightBike.getMatrixPosition());
	}
	
	@Test
	public void testDoubleMove() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10);
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, 2 ,1,"east",3);
		System.out.println(Arrays.deepToString(matrix.generateMatrix()));
		lightBike.move();
		System.out.println(Arrays.deepToString(matrix.generateMatrix()));
		assertEquals(matrix.getNode(5, 7), lightBike.getMatrixPosition());
	}
	
	@Test
	public void testMine() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10);
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, 1 ,1,"east",3);
		new Element("mine",matrix.getNode(5, 6));
		lightBike.move();
		assertFalse(lightBike.isActive());
	}

}
