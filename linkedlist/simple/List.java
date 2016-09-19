package linkedlist.simple;

/** Esta clase generica define una lista simple y su comportamiento
 * 
 * @author jeanpaul
 *
 * @param <T>
 */

public class List <T> {

   private Node<T> head;
   private Node<T> tail;
   
   /**este metodo recibe como parametro un dato T 
    * y lo inserta en el Head de la lista simple
    * 
    * @param data
    */
   public void insertHead(T data) {
      if (head == null) {
          head = tail = new Node<T>(data);
      }
      else {
         Node<T> tmpNode = new Node<T>(data);
         tmpNode.setNextNode(head);
         head = tmpNode;
      }
   }

   /**este metodo recibe como parametro un dato T 
    * y lo inserta en el tail de la lista simple
    * 
    * @param data
    */
   public void insertTail(T data) {
      if (head == null) {
         head = tail = new Node<T>(data);
      }
      else{
         Node<T> tmpNode = new Node<>(data);
         tail.setNextNode(tmpNode);
         tail = tmpNode;
      }
   }
   
   /**
    * este medoto elimina el head de la lista
    */
   public void deleteHead() {
      if (head != null) {
         if (head == tail) {
            head = tail = null;
         }
         else {
            head = head.getNextNode();
         }
      }
   }
   
   /**
    * este metodo elimina el tail de la lista
    */
   public void deleteTail() {
      if (head != null) {
         if (head == tail) {
            head = tail = null;
         }
         else {
            Node<T> pointer = head;
            while(pointer.getNextNode() != tail) {
                  pointer = pointer.getNextNode();
            }
            pointer.setNextNode(null);
            tail = pointer;
         }
      }
   }
   
   /**este metodo recibe como parametro un dato
    * el cual sera buscado en la lista y de encontrarse una
    * instancia de este, sera eliminado
    * 
    * @param data
    */
   public void delete(T data) {
	   
	   Node<T> pointer = head;
	   while(pointer != null) {
		   if (pointer.getData().equals(data)) {
			   break;
		   }
		   else {
			   pointer = pointer.getNextNode();
		   }
	   }          
	   delete(pointer);
   }
   
   private void delete(Node<T> node) {
      
	   if (head == node) {
		   deleteHead();
	   }
	   
	   else if (tail == node) {
		   deleteTail();
	   }
	   
	   else {
		   Node<T> pointer = head;
		   while(pointer != tail) {
			   if (pointer.getNextNode() == node) {
				   pointer.setNextNode(pointer.getNextNode().getNextNode());
				   break;
			   }
			   else {
				   pointer = pointer.getNextNode();
			   }
		   }
	   }   
   }
   
   /**
    * este metodo muestra en consola los contenidos
    * de cada nodo de la lista
    */
   public void print() {
      if (head != null) {
         Node<T> pointer = head;
         while(pointer != null) {
            System.out.println(pointer.getData());
            pointer = pointer.getNextNode();
         }
         System.out.println("\n");
      }
   }
   
   public boolean exist(T data) {
	   boolean exist = false;
	   if (head != null) {
		   Node<T> pointer = getHead();
		   while(pointer != null) {
			   if (pointer.getData().equals(data)) {
				   exist = true;
				   break;
			   }
			   else {
				   pointer = pointer.getNextNode();
			   }
		   }
	   }
	   return exist;
   }

   public void setHead(Node<T> head) {
      this.head = head;
   }

   public Node<T> getHead() {
      return head;
   }

   public void setTail(Node<T> tail) {
      this.tail = tail;
   }

   public Node<T> getTail() {
      return tail;
   }
}