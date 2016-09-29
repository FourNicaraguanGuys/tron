package server.main;



import java.net.ServerSocket;

import server.serverThread.Handler;


public class TronServer {

    private static final int PORT = 9001;

    public static void main(String[] args) throws Exception {
    	System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        System.out.println("The chat server is running.");
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }
}