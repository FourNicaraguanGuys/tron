package tron.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler extends Thread {
    
    private Socket socket;
    private Protocol protocol;
    private BufferedReader in;
    private PrintWriter out;

    public Handler(Socket socket,Protocol protocol) {
        setSocket(socket);
        setProtocol(protocol);
    }
    

    public void run() {
        
    	try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
             
    		String input;         
    		protocol.init(this);     
    		out.println(protocol.getResponse());
    		
    		while ((input = in.readLine()) != null) { 
    			System.out.println(input);
    			protocol.analyze(input);
    			while(protocol.getResponse() == null) {}
    			System.out.println(protocol.getResponse());
    			out.println(protocol.getResponse());
    			protocol.setResponse(null);
    		}
    		socket.close();
        } catch (IOException e ) {
            e.printStackTrace();
         }
    }

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}


	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}
	    
}
