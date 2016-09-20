package linkedlist.queue;

import linkedlist.simple.Node;

/**esta clase implementa un queue
 * 
 * @author jeanpaul
 *
 * @param <T>
 */
public class Queue<T> {
	
	private Node<T> head;
	private Node<T> tail;
	private int lenght;

	/** This method enqueues data
	 * 
	 * @param data
	 */
	public void enqueue(T data) {
	      if (head == null) {
	         head = tail = new Node<T>(data);
	      }
	      else{
	         Node<T> tmpNode = new Node<>(data);
	         tail.setNextNode(tmpNode);
	         tail = tmpNode;
	      }
	      lenght++;
	   }

	/**This method return the first data inserted then before 
	 * eliminating it from the queue
	 * 
	 * @return data 
	 */
	public T dequeue() {
		T data = null;
		if (head != null) {
			data = head.getData();
			if (head == tail) {
				head = tail = null;
			}
			else {
				head = head.getNextNode();
			}
			lenght--;
		}
		return data;
	}
	
	public T peek() {
		T data = null;
		if(head != null) {
			data = head.getData();
		}
		return data;
	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public Node<T> getTail() {
		return tail;
	}

	public void setTail(Node<T> tail) {
		this.tail = tail;
	}
	
}
