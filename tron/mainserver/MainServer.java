package tron.mainserver;

import tron.data.Data;
import tron.data.DataAccess;
import tron.server.Server;

public class MainServer {
	
	public static void main(String args[]) {
		DataAccess data = new Data();
		Server server = new Server(data); 
		server.run();
	}
	
}
