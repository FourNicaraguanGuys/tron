package linkedlist.stack;
import linkedlist.simple.Node;

/** Esta clase define una pila generica y su comportamiento
 * 
 * @author jeanpaul
 *
 * @param <T>
 */

public class Stack <T> {

   private Node<T> head;
   private int lenght;
   
   /**este metodo recibe como parametro un dato T 
    * y lo inserta en el Head de la lista simple
    * 
    * @param data
    */
   public void push(T data) {
      if (head == null) {
          head = new Node<T>(data);
      }
      else {
         Node<T> tmpNode = new Node<T>(data);
         tmpNode.setNextNode(head);
         head = tmpNode;
      }
      lenght++;
   }
   
   /**
    * este medoto devuelve la informacion de la 
    * cabeza de la listaluego la elimina
    * @return data
    */
    public T pop() {
    	T data = null;
		if (head != null) {
			data = head.getData();
			if (head.getNextNode() == null) {
				head = null;
			}
			else {
				head = head.getNextNode();
			}
			lenght--;
		}
		return data;
    }
    
    /**retorna la informacion que contiene el head
     * 
     * @return data
     */
    public T peek() {
    	T data = null;
        if (head != null) {
            data = head.getData();
        } 
        return data;
    }
  
    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getHead() {
        return head;
    }
   
}
