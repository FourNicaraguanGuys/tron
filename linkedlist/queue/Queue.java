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

	/** This method enqueques data
	 * 
	 * @param data
	 */
	public void enqueque(T data) {
	      if (head == null) {
	         head = tail = new Node<T>(data);
	      }
	      else{
	         Node<T> tmpNode = new Node<>(data);
	         tail.setNextNode(tmpNode);
	         tail = tmpNode;
	      }
	   }

	/**This method retrun the firt data inserted then before 
	 * elimintationg it from the queue
	 * 
	 * @return data 
	 */
	public T dequeque() {
		T data = null;
		if (head != null) {
			data = head.getData();
			if (head == tail) {
				head = tail = null;
			}
			else {
				head = head.getNextNode();
			}
		}
		return data;
	}

}
