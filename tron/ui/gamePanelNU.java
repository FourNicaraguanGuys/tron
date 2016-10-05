package tron.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import linkedlist.simple.*;
import linkedlist.simple.client.LsNode;
import linkedlist.simple.client.listaSimplePrueba;



public class gamePanelNU{
	
	private JFrame frame;
	//***************************************//
	private String[][] array;
	//***************************************//
	private listaSimplePrueba lista = new listaSimplePrueba();
	private listaSimplePrueba listaR = new listaSimplePrueba();
	//***************************************//
	private int columns;
	private int rows;
	private int printer = 0;
	private int printer2 = 0;
	private int xpos = 0;
	private int ypos = 0;
	//***************************************//

	
	public gamePanelNU (String[][] array, String columns, String rows){//String[][] array, String columns, String rows
		this.array = array;
		this.columns = Integer.parseInt(columns);
		this.rows = Integer.parseInt(rows);
		initialize();
		imp();
		frame.setVisible(true);
	}
	


	
	private void initialize() {
		int math = 300/rows;
		int ro = (int) (math*(rows-0.85));
		int printer4 = 0;
		int col = (int) (40*(columns-0.75));
		
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		while(printer != columns){
			printer = printer + 1;
			JPanel panel = new JPanel();
			panel.setBackground(Color.MAGENTA);
			panel.setBounds(46, 90+ypos, ro, 10);
			ypos = ypos + 40;
			lista.addToTail(panel);
			
		}

		while (printer4 != rows){
			printer4 = printer4 + 1;
			JPanel panelC = new JPanel();
			panelC.setBackground(Color.MAGENTA);
			panelC.setBounds(46+xpos, 90, 10, col);
			xpos = xpos + math;
			listaR.addToTail(panelC);

		}
		

		
	}
	
	public void imp(){
		LsNode temp = lista.getHead();
		while (printer2 != lista.getL()+1){
			frame.getContentPane().add(temp.getinfo());
			temp = temp.next;
			printer2 = printer2 + 1;
		}
		
		temp = listaR.getHead();
		int printer3 =  0;
		while (printer3 != listaR.getL()+1){
			frame.getContentPane().add(temp.getinfo());
			temp = temp.next;
			printer3 = printer3 + 1;
		}
	}


}


