package linkedlist.simple.client;

import javax.swing.JPanel;

public class LsNode {
	public JPanel info;
	public LsNode next;
	public LsNode(JPanel el) {
	this(el,null);
	}
	public LsNode(JPanel el, LsNode n) {
	info = el; next = n;
	}
	public JPanel getinfo(){
		return info;
	}
}

