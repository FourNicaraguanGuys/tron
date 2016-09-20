package test.linkedlist.simple;

import static org.junit.Assert.*;
import org.junit.Test;
import linkedlist.simple.List;

public class ListTest {
	
	@Test
	public void testInsertHeadNullList() {
		List<String> list = new List<>();
		String element = "element";
		list.insertHead(element);
		assertEquals("Head insertion with null list",
				element,list.getHead().getData());
	}
	
	@Test
	public void testInsertHeadFullList() {
		List<String> list = new List<>();
		String firstElement = "first element";
		String secondElement = "second element";
		String thirdElement = "third element";
		list.insertHead(firstElement);
		list.insertHead(secondElement);
		list.insertHead(thirdElement);
		assertEquals("Head insertion with full list",
				thirdElement,list.getHead().getData());
	}
	
	@Test
	public void testInsertTailNullList() {
		List<String> list = new List<>();
		String element = "element";
		list.insertTail(element);
		assertEquals("Tail insertion with null list",
				element,list.getTail().getData());
	}
	
	@Test
	public void testInsertTailFullList() {
		List<String> list = new List<>();
		String firstElement = "first element";
		String secondElement = "second element";
		String thirdElement = "third element";
		list.insertTail(firstElement);
		list.insertTail(secondElement);
		list.insertTail(thirdElement);
		assertEquals("Tail insertion with full list",
				thirdElement,list.getTail().getData());
	}
	
	@Test
	public void testDeleteHead() {
		List<String> list = new List<>();
		String firstElement = "first element";
		String secondElement = "second element";
		String thirdElement = "third element";
		list.insertHead(firstElement);
		list.insertHead(secondElement);
		list.insertHead(thirdElement);
		list.delete(firstElement);
		assertEquals("Head deletion",
				thirdElement,list.getHead().getData());
	}
	
	
	@Test
	public void testDeleteMiddle() {
		List<String> list = new List<>();
		String firstElement = "first element";
		String secondElement = "second element";
		String thirdElement = "third element";
		list.insertHead(firstElement);
		list.insertHead(secondElement);
		list.insertHead(thirdElement);
		list.delete(secondElement);
		assertEquals("Middle deletion",
				firstElement,list.getTail().getData());
	}
	
	@Test
	public void testDeleteTail() {
		List<String> list = new List<>();
		String firstElement = "first element";
		String secondElement = "second element";
		String thirdElement = "third element";
		list.insertHead(firstElement);
		list.insertHead(secondElement);
		list.insertHead(thirdElement);
		list.delete(thirdElement);
		assertEquals("Tail deletion",
				secondElement,list.getHead().getData());
	}
	
	@Test
	public void testExistTrue() {
		List<String> list = new List<>();
		String firstElement = "first element";
		String secondElement = "second element";
		String thirdElement = "third element";
		list.insertHead(firstElement);
		list.insertHead(secondElement);
		list.insertHead(thirdElement);
		assertEquals(true, list.exist(firstElement));
	}

	@Test
	public void testExistFalse() {
		List<String> list = new List<>();
		String firstElement = "first element";
		String secondElement = "second element";
		String thirdElement = "third element";
		String fourthElement = "fourth element";
		list.insertHead(firstElement);
		list.insertHead(secondElement);
		list.insertHead(thirdElement);
		assertEquals(false, list.exist(fourthElement));
	}
}
