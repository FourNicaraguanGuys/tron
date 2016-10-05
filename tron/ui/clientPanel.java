package tron.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class clientPanel {

	private JFrame frame;
	private String ip = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientPanel window = new clientPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public clientPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		frame.setBounds(100, 100, 400, 284);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTron = new JLabel("Tron");
		lblTron.setFont(new Font("Comic Sans MS", Font.BOLD, 38));
		lblTron.setBounds(131, 11, 132, 136);
		frame.getContentPane().add(lblTron);
		
		JButton btnSNG = new JButton("Start New Game");
		btnSNG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientNU nuevo = new clientNU ();
				frame.setVisible(false);
			}
		});
		btnSNG.setBounds(31, 166, 132, 48);
		frame.getContentPane().add(btnSNG);
		
		JButton btnJG = new JButton("Join Game");
		btnJG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
				
			}
		});
		btnJG.setBounds(218, 166, 132, 48);
		frame.getContentPane().add(btnJG);
	}

}
