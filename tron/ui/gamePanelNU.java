package tron.ui;

import javax.swing.JFrame;

public class gamePanelNU extends Thread {

	private JFrame frame;
	private String ip = "localhost";

	gamePanelNU(){
		start();
	}
	
	public void run(){
		initialize();
		frame.setVisible(true);
		
		clientLogic logic = new clientLogic (ip);
		logic.run();
		logic.start();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
