package test.linkedlist.quadruple;

import static org.junit.Assert.*;

import org.junit.Test;

import linkedlist.quadruple.QuadrupleList;
import linkedlist.quadruple.QuadrupleNode;

public class QuadrupleListTest {

	@Test 
	public void testCreation() {
		QuadrupleList<String> matrix = new QuadrupleList<String>(100,100);
		assertEquals(null, matrix.getNode(100, 100).getData());
	}
	
	@Test
	public void testInsertionAndRead() {	
		QuadrupleList<String> matrix = new QuadrupleList<String>(4,3);
		matrix.setNodeData("test", 4 , 3);
		assertEquals("insercion y lectura",matrix.getNode(4,3).getData(),"test");
	}
	
	@Test 
	public void testNodeSouthLinks() {
		QuadrupleList<String> matrix = new QuadrupleList<String>(4,3);
		matrix.setNodeData("test", 4 , 0);
		QuadrupleNode<String> pointer = matrix.getOrigin();
		while(pointer.getSouthNode() != null) {
			pointer = pointer.getSouthNode();
		}
		assertEquals("recorrido hacia el sur",pointer.getData(),"test");
	}
	
	@Test
	public void testNodeEastLinks() {
		QuadrupleList<String> matrix = new QuadrupleList<String>(4,3);
		matrix.setNodeData("test", 0 , 3);
		QuadrupleNode<String> pointer = matrix.getOrigin();
		while(pointer.getEastNode() != null) {
			pointer = pointer.getEastNode();
		}
		assertEquals("recorrido hacia el este",pointer.getData(),"test");
	}
	
	@Test
	public void testMatrixMultiplePosition() {
		QuadrupleList<String> matrix = new QuadrupleList<String>(2,2);
		matrix.setNodeData("1,1", 1, 1);
		matrix.setNodeData("2,1", 2, 1);
		matrix.setNodeData("1,2", 1, 2);
		matrix.setNodeData("2,2", 2, 2);
		String[] expected = {"1,1","1,2","2,1","2,2"};
		String[] result = {matrix.getNode(1, 1).getData(),
				matrix.getNode(1, 2).getData(),
				matrix.getNode(2, 1).getData(),
				matrix.getNode(2, 2).getData()};
		assertArrayEquals(expected, result);
	}
	
	/*
	@Test
	public void testGenerateMatrix() {
		QuadrupleList<String> matrix = new QuadrupleList<String>(2,2);
		matrix.setNodeData("1,1", 1, 1);
		matrix.setNodeData("2,1", 2, 1);
		matrix.setNodeData("1,2", 1, 2);
		matrix.setNodeData("2,2", 2, 2);
		System.out.println(Arrays.deepToString(matrix.generateMatrix()));
	}
	*/
}
