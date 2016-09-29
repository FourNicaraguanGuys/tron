package server.server;



import java.net.ServerSocket;


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