package linkedlist.quadruple;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuadrupleListTest {

	@Test
	public void test() {	
		QuadrupleList<String> matrix = new QuadrupleList<String>(4,3);
		matrix.setNodeData("hola", 4 , 3);
		assertEquals("insercion y lectura",matrix.getNode(4,3).getData(),"hola");
	}

}
