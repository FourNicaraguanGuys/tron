package test.linkedlist.stack;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import linkedlist.stack.Stack;


public class StackTest {
	
	@Test
	public void testPushEmptyStack() {
		Stack<String> stack = new Stack<>();
		String element = "element";
		stack.push(element);
		assertEquals(element,stack.peek());
	}

	@Test
	public void testPushFilledStack() {
		Stack<String> stack = new Stack<>();
		String firstElement = "first element";
		String secondElement = "second element";
		String thirdElement = "third element";
		stack.push(firstElement);
		stack.push(secondElement);
		stack.push(thirdElement);
		assertEquals(thirdElement,stack.peek());
	}

	@Test
	public void testPopEmptyStack() {
		Stack<String> stack = new Stack<>();
		assertEquals(null, stack.pop());
	}

	@Test
	public void testPopFilledStack() {
		Stack<String> stack = new Stack<>();
		String firstElement = "first element";
		String secondElement = "second element";
		String thirdElement = "third element";
		stack.push(firstElement);
		stack.push(secondElement);
		stack.push(thirdElement);
		assertEquals(thirdElement,stack.pop());
	}

}
