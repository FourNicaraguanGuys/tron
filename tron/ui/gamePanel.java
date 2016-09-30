package tron.ui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class gamePanel  extends Thread{

	private JFrame frame;
	private String ip = "";
	private JTextField textField;
	private JTextField textField_1;
	
	gamePanel(){
		start();
	}
	
	public void run(){
		initialize();
		frame.setVisible(true);
		ip = JOptionPane.showInputDialog(frame, "Enter IP Address", "Welcome, The TRON Game", JOptionPane.QUESTION_MESSAGE);
		
		clientLogic logic = new clientLogic (ip);
		logic.run();
		logic.start();
	}
	
	private void initialize(){
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblColumnas = new JLabel("Columnas");
		lblColumnas.setBounds(85, 166, 68, 14);
		frame.getContentPane().add(lblColumnas);
		
		textField = new JTextField();
		textField.setBounds(163, 163, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblFilas = new JLabel("Filas");
		lblFilas.setBounds(85, 196, 46, 14);
		frame.getContentPane().add(lblFilas);
		
		textField_1 = new JTextField();
		textField_1.setBounds(163, 194, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNuevoJuego = new JLabel("Nuevo Juego");
		lblNuevoJuego.setFont(new Font("Comic Sans MS", Font.PLAIN, 39));
		lblNuevoJuego.setBounds(107, 11, 269, 82);
		frame.getContentPane().add(lblNuevoJuego);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(366, 263, 89, 23);
		frame.getContentPane().add(btnStart);
		
	}
	
	
}
