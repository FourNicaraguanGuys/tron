package tron.mainserver;

import tron.logic.GameCoordinator;
import tron.logic.Logic;
import tron.server.Server;

public class MainServer {
	
	public static void main(String args[]) {
		Logic logic = new GameCoordinator();
		Server server = new Server(logic); 
		server.run();
	}

}
