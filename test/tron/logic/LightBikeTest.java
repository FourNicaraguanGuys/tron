package test.tron.logic;

import static org.junit.Assert.*;

import org.junit.Test;

import linkedlist.quadruple.QuadrupleList;
import tron.logic.Element;
import tron.logic.HyperSpeed;
import tron.logic.LightBike;

public class LightBikeTest {
	
	@Test
	public void testBikeCollision() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10); 
		LightBike lightBikeOne = new LightBike(matrix.getNode(5, 5), 1, matrix, 1 ,1,"east",3);
		LightBike lightBikeTwo = new LightBike(matrix.getNode(5, 6), 1, matrix, 1 ,1,"east",3);
		lightBikeOne.move(); 
		lightBikeTwo.move();
		assertFalse(lightBikeOne.isActive());
	}
	
	@Test
	public void testSingleMove() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, matrix, 1 ,1,"east",3);
		lightBike.move(); 
		assertEquals(matrix.getNode(5, 6), lightBike.getMatrixPosition());
	}
	
	@Test
	public void testDoubleMove() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1,matrix, 2 ,1,"east",3);
		lightBike.move();
		assertEquals(matrix.getNode(5, 7), lightBike.getMatrixPosition());
	}
	
	@Test
	public void testMine() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, matrix, 1 ,1,"east",3);
		new Element("mine",matrix.getNode(5, 6));
		lightBike.move();
		assertFalse(lightBike.isActive());
	}
	
	@Test
	public void testCollectFuel() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, matrix, 1 ,1,"east",3);
		new Element("fuel cell",matrix.getNode(5, 6));
		lightBike.move();
		assertEquals("fuel cell", lightBike.getItemQueue().getHead().getData().getType());
	}
	
	@Test
	public void testFuelIncrease() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, matrix, 1 ,1,"east",3);
		float previousFuel = lightBike.getFuel();
		new Element("fuel cell",matrix.getNode(5, 6));
		lightBike.move();
		assertTrue(previousFuel < lightBike.getFuel());
	}
	
	@Test
	public void testCollectSpeedIncrease() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, matrix, 1 ,1,"east",3);
		new HyperSpeed(matrix.getNode(5, 6));
		lightBike.move();
		assertTrue(lightBike.getHyperSpeedQueue().getHead() != null);
	}
	
	@Test
	public void testSpeedIncrease() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, matrix, 1 ,1,"east",3);
		int previousSpeed = lightBike.getSpeed();
		new HyperSpeed(matrix.getNode(5, 6));
		lightBike.move();
		assertTrue(previousSpeed < lightBike.getSpeed());
	}
	
	@Test
	public void testCollectShield() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, matrix, 1 ,1,"east",3);
		new Element("shield",matrix.getNode(5, 6));
		lightBike.move();
		assertTrue(lightBike.getShieldStack().getHead() != null);
	}
	
	@Test
	public void testSpreadItems() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, matrix, 1 ,1,"east",3);
		new Element("fuel cell",matrix.getNode(5, 6));
		new Element("fuel cell",matrix.getNode(5, 7));
		lightBike.move();
		lightBike.move();
		lightBike.spreadItems();
		assertTrue(lightBike.getItemQueue().getHead() == null);
	}
	
	@Test
	public void testSpreadShields() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, matrix, 1 ,1,"east",3);
		new Element("shield",matrix.getNode(5, 6));
		new Element("shield",matrix.getNode(5, 7));
		lightBike.move();
		lightBike.move();
		lightBike.spreadShields();
		assertTrue(lightBike.getShieldStack().getHead() == null);
	}
	
	@Test
	public void testSpreadHyperSpeed() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(10,10); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, matrix, 1 ,1,"east",3);
		new HyperSpeed(matrix.getNode(5, 6));
		new HyperSpeed(matrix.getNode(5, 7));
		lightBike.move();
		lightBike.move();
		lightBike.spreadHyperSpeed();
		assertTrue(lightBike.getHyperSpeedQueue().getHead() == null);
	}
	
	@Test
	public void testOutOfBounds() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(5,5); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, matrix,1 ,1,"east",3);
		lightBike.move();
		assertFalse(lightBike.isActive());
	}
	
	@Test
	public void testOutOfFuel() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(15,15); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 1, matrix, 10 , 1, "east", 3);
		lightBike.move();
		assertFalse(lightBike.isActive());
	}
	
	@Test
	public void testModifyDirectionRight() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(15,15); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 0, matrix, 0, 0, "east", 0);
		lightBike.modifyDirection("right");
		assertEquals("south",lightBike.getDirection());
	}
	
	@Test
	public void testModifyDirectionLeft() {
		QuadrupleList<Element> matrix = new QuadrupleList<>(15,15); 
		LightBike lightBike = new LightBike(matrix.getNode(5, 5), 0, matrix, 0 , 0, "east", 0);
		lightBike.modifyDirection("left");
		assertEquals("north",lightBike.getDirection());
	}
	
}
