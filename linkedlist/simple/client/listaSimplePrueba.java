package linkedlist.simple.client;

import javax.swing.JPanel;

public class listaSimplePrueba {
	protected LsNode head, tail;
	public listaSimplePrueba() {
	head = tail = null;
	}
	public boolean isEmpty() {
	return head == null;
	}
	
	public void addToTail(JPanel el) {
		if (!isEmpty()) {
		tail.next = new LsNode(el);
		tail = tail.next;
		}
		else head = tail = new LsNode(el);
		}
	public LsNode getHead(){
		return head;
		
	}
	public int getL(){
		LsNode temp = head;
		int cont = 0;
		while(temp.next != null){
			cont = cont+1;
			temp = temp.next;
		}
		return cont;
	}
}
