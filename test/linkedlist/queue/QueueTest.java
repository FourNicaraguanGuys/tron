package test.linkedlist.queue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import linkedlist.queue.Queue;


public class QueueTest {
	
	@Test
	public void testEnqueueEmptyQueue() {
		Queue<String> queue = new Queue<>();
		String element = "element";
		queue.enqueue(element);
		assertEquals(element,queue.peek());
	}

	@Test
	public void testEnqueueFilledQueue() {
		Queue<String> queue = new Queue<>();
		String firstElement = "first element";
		String secondElement = "second element";
		String thirdElement = "third element";
		queue.enqueue(firstElement);
		queue.enqueue(secondElement);
		queue.enqueue(thirdElement);
		assertEquals(firstElement,queue.peek());
	}

	@Test
	public void testDequeueEmptyQueue() {
		Queue<String> queue = new Queue<>();
		assertEquals(null, queue.dequeue());
	}

	@Test
	public void testDequeueFilledQueue() {
		Queue<String> queue = new Queue<>();
		String firstElement = "first element";
		String secondElement = "second element";
		String thirdElement = "third element";
		queue.enqueue(firstElement);
		queue.enqueue(secondElement);
		queue.enqueue(thirdElement);
		assertEquals(firstElement,queue.dequeue());
	}
	
}
