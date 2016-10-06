package tron.ui;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import linkedlist.simple.client.LsNode;
import linkedlist.simple.client.listaSimplePrueba;



public class gamePanelNU implements KeyListener{
	private JFrame frame;
	//***************************************//
	private String[][] array;
	//***************************************//
	private listaSimplePrueba lista = new listaSimplePrueba();
	private Random rand = new Random();
	private clientLogic logic;
	//***************************************//
	private int columns;
	private int rows;
	private int pointer = 0;
	private int pointer2 = 0;
	private int pointer4 = 0;
	private int xpos = 0;
	private int ypos = 0;
	//***************************************//

	
	public gamePanelNU (String[][] array, String columns, String rows){//String[][] array, String columns, String rows
		this.array = array;
		this.columns = Integer.parseInt(columns);
		this.rows = Integer.parseInt(rows);
		createJpanels();
		printJpanels();
		frame.setVisible(true);
	}
	
	public void createJpanels(){
		frame = new JFrame();
		frame.addKeyListener(this);
		frame.setBounds(100, 100, 928, 676);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		
		while(pointer4 != rows){
			while(pointer != columns){
				JPanel panel = new JPanel();
				panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
				panel.setBackground(Color.WHITE);
				panel.setBounds(0+xpos, 0+ypos, 25, 25);
				pointer = pointer + 1;
				xpos = xpos +25;
				lista.addToTail(panel);
			}
			xpos = 0;
			pointer = 0;
			ypos = ypos+25;
			pointer4 = pointer4 +1;
		}
	}
	public void printJpanels(){
		LsNode temp = lista.getHead();
		while (pointer2 != lista.getL()+1){
			frame.getContentPane().add(temp.getinfo());
			temp = temp.next;
			pointer2 = pointer2 + 1;
		}
	
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();

		  if (key == KeyEvent.VK_LEFT) {

			  System.out.println("Presionó Izquierda!");
		  }
		  if (key == KeyEvent.VK_RIGHT) {
			  
			  System.out.println("Presionó Derecha!");
		  }
		  if (key == KeyEvent.VK_UP){
			  
			  System.out.println("Presionó Arriba!" );
		  }
		  if (key == KeyEvent.VK_DOWN){
			  
			  System.out.println("Presionó Abajo!");
		  }

		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Soltó una tecla");
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
}


