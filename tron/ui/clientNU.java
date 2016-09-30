package tron.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class clientNU {

	private JFrame frame;
	private JTextField ncolumns;
	private JTextField nrows;
	private String Rows;
	private String Columns;


	public clientNU() {
		initialize();
		frame.setVisible(true);
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblColumnas = new JLabel("Columnas");
		lblColumnas.setBounds(85, 196, 68, 14);
		frame.getContentPane().add(lblColumnas);
		
		ncolumns = new JTextField();
		ncolumns.setBounds(174, 193, 86, 20);
		frame.getContentPane().add(ncolumns);
		ncolumns.setColumns(10);
		
		JLabel lblFilas = new JLabel("Filas");
		lblFilas.setBounds(107, 166, 46, 14);
		frame.getContentPane().add(lblFilas);
		
		nrows = new JTextField();
		nrows.setBounds(174, 163, 86, 20);
		frame.getContentPane().add(nrows);
		nrows.setColumns(10);
		
		JLabel lblNuevoJuego = new JLabel("Nuevo Juego");
		lblNuevoJuego.setFont(new Font("Comic Sans MS", Font.PLAIN, 39));
		lblNuevoJuego.setBounds(107, 11, 269, 82);
		frame.getContentPane().add(lblNuevoJuego);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnStart.setBounds(366, 263, 89, 23);
		frame.getContentPane().add(btnStart);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rows = nrows.getText();
				Columns = ncolumns.getText();
				
				System.out.println(Rows);
				System.out.println(Columns);
			}
		});
		btnSave.setBounds(267, 263, 89, 23);
		frame.getContentPane().add(btnSave);
	}
	
	public void sendColumnsAndRows(){
		
	}
}
