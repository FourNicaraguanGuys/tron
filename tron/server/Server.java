package tron.server;

import java.io.IOException;
import java.net.ServerSocket;

import tron.data.DataAccess;

/**
 * En esta clase se tiene la clase principal  que es el main del server
 * y una calse secundaria que maneja la logica del juego
 * 
 * @author Manuel
 *
 */

public class Server implements Runnable, Constants {
	
	private int portNumber;
	private boolean listening;
	private DataAccess dataAccess;
	
	public Server(DataAccess dataAccess) {
		setPortNumber(PORT_NUMBER);
		setListening(true);
		setDataAccess(dataAccess);
	}
    
	public void run(){
		System.out.println("Server Initiated");
		try (ServerSocket serverSocket = new ServerSocket(getPortNumber())) { 
			while (isListening()) {
				new Handler(serverSocket.accept(),new Protocol(getDataAccess())).start();
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

	public DataAccess getDataAccess() {
		return dataAccess;
	}

	public void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}
	
}