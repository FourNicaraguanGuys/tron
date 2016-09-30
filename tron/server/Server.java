package tron.server;

import java.io.IOException;
import java.net.ServerSocket;

import tron.logic.Logic;
import tron.mainserver.Constants;

/**
 * En esta clase se tiene la clase principal  que es el main del server
 * y una calse secundaria que maneja la l�gica del juego
 * 
 * @author Manuel
 *
 */

public class Server implements Runnable, Constants {
	
	private int portNumber;
	private boolean listening;
	private Logic logic;
	
	public Server(Logic logic) {
		setPortNumber(PORT_NUMBER);
		setListening(true);
		setLogic(logic);
	}
    
	public void run(){
		System.out.println("Server Initiated");
		try (ServerSocket serverSocket = new ServerSocket(getPortNumber())) { 
			while (isListening()) {
				new Handler(serverSocket.accept(),getLogic()).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}
	}
	
	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public boolean isListening() {
		return listening;
	}

	public void setListening(boolean listening) {
		this.listening = listening;
	}

	public Logic getLogic() {
		return logic;
	}

	public void setLogic(Logic logic) {
		this.logic = logic;
	}

	/**
	public static void main(String[] args) throws Exception {
		ServerSocket listener = new ServerSocket(getPortNumber());
		try {
			while (true) {
				new Handler(listener.accept()).start();
			}
		} finally {
			listener.close();
		}
	}*/
	
	
}