package tron.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;




@SuppressWarnings("serial")
public class gamePanelNU extends JPanel implements KeyListener{
	/**
	 * 
	 */
	private JFrame frame;
	//***************************************//
	private String[][] array;
	//***************************************//
	//clientLogic logic;
	//***************************************//
	private int xpos = 0;
	private int ypos = 0;

	//***************************************//

	
	public gamePanelNU (){//String[][] array, String columns, String rows
		frame = new JFrame();
		//frame.addKeyListener(this);
		frame.setBounds(100, 100, 928, 676);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		frame.setVisible(true);
		frame.setTitle("TRON");
	}
	
	
	public void createJpanels(String[][] array){

		  for (int i = 0; i < array.length; i++){
			  for (int j = 0; j < array[i].length; j++ ){
				  String listCompare = array[i][j];
				    if(listCompare == null) {
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(Color.WHITE);
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						
				    }
				    else if(listCompare.equals("bike1")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(Color.RED);
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						frame.paint(frame.getGraphics());
				    }
				    else if(listCompare.equals("trail1")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(Color.BLUE);
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						frame.paint(frame.getGraphics());
				    }
				    else if(listCompare.equals("bike2")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(Color.YELLOW);
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						frame.paint(frame.getGraphics());
				    }
				    else if(listCompare.equals("trail2")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(Color.GREEN);
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						frame.paint(frame.getGraphics());
				    }
				    else if(listCompare.equals("bike3")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(Color.BLACK);
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						frame.paint(frame.getGraphics());
				    }
				    else if(listCompare.equals("trail3")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(Color.ORANGE);
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						frame.paint(frame.getGraphics());
				    }
				    else if(listCompare.equals("bike4")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(Color.PINK);
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						frame.paint(frame.getGraphics());
				    }
				    else if(listCompare.equals("trail4")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(Color.GRAY);
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						frame.paint(frame.getGraphics());
				    }
				    else if(listCompare.equals("fuel cell")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(Color.magenta);
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						frame.paint(frame.getGraphics());
				    }
				    else if(listCompare.equals("trail upgrade")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(Color.CYAN);
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						frame.paint(frame.getGraphics());
				    }
				    else if(listCompare.equals("mine")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(Color.lightGray);
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						frame.paint(frame.getGraphics());
						
				    }
				    else if(listCompare.equals("shield")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(new Color(160,103,127));
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);
						frame.paint(frame.getGraphics());
						
				    }
				    else if(listCompare.equals("hyper speed")){
						JPanel panel = new JPanel();
						panel.setToolTipText("");
						panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panel.setBackground(new Color(103,154,0));
						panel.setBounds(0+xpos, 0+ypos, 25, 25);
						frame.getContentPane().add(panel);	
						frame.paint(frame.getGraphics());
				    }
				    xpos = xpos + 25;
			   }
			  ypos = ypos + 25;
			  xpos = 0;
			  frame.paint(frame.getGraphics());
			  frame.update(frame.getGraphics());
			  frame.repaint();
		  }
		  xpos = 0;
		  ypos = 0;
	}


	public String[][] getArray() {
		return array;
	}


	public void setArray(String[][] array) {
		this.array = array;
	}


	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public void repa(){
		frame.repaint();
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		 
		 if (key == KeyEvent.VK_LEFT) {
			 
		 }
		 if (key == KeyEvent.VK_RIGHT) {
			 
		 }
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}


