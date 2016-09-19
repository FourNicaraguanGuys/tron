package linkedlist.simple;


/**esta clase define un nodo Generico
 * 
 * @author jeanpaul
 *
 * @param <T>
 */
public class Node <T> {

   private T data;
   private Node<T> nextNode;
   
   /**este constructor crea un nuevoo nodo que contiene 
    * el dato especificado como parametro
    * 
    * @param data
    */
   public Node(T data) {
      this.data = data;
      this.nextNode = null;
   }
   
   public T getData() {
      return data;
   }

   public void setData(T data) {
      this.data = data;
   }
   
   public void setNextNode(Node<T> node) {
      this.nextNode = node;
   }
   
   public Node<T> getNextNode() {
      return nextNode;
   }

}
