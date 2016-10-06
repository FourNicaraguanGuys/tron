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
    		System.out.println("New Connection");
    		out.println(protocol.getResponse());
    		
    		while ((input = in.readLine()) != null) {                      
    			protocol.analyze(input);
    			out.println(protocol.getResponse());
    			protocol.setResponse(null);
    		}
    		socket.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
	
     /*
        try {


         
            while (true) {
            	System.out.println("entro");
            	if (listbikeID.getHead() == null){
            		String jsonString = in.readLine();
            		if (jsonString!= null){
            			System.out.println(jsonString);
            			Integer r = (Integer) ServerJson.decodeJRow(jsonString);
            			Integer c = (Integer) ServerJson.decodeJColumn(jsonString);
            			logic.setMatrixSize(r, c);
            			SArray = logic.generateMatrix();
            			JSONArray JAMain = ServerJson.encodeJArray(SArray); //generate matrix
            			System.out.println(JAMain); 
            			out.print(JAMain);
            			break;
            		}
            	}else{
            		break; 
            	}
            }
           
            while (true) {
                String input = in.readLine();
                if (input == null) {
                    return;
                }else{
                	String[] keyArray = new String[4];
                	name = ServerJson.decodeJSPlayer(input);
                	String key = ServerJson.decodeJSKey(input);
                	if (listbikeID.exist(name)){
                		if (name.equals("bike1")){
                			keyArray[0] = key;
                		}else if(name.equals("bike2")){
                			keyArray[1] = key;
                		}else if(name.equals("bike3")){
                			keyArray[2] = key;
                		}else if (name.equals("bike4")){
                			keyArray[3] = key;
                		}
                	}else{
                		listbikeID.insertTail(name);
                		if (name.equals("bike1")){
                			keyArray[0] = key;
                		}else if(name.equals("bike2")){
                			keyArray[1] = key;
                		}else if(name.equals("bike3")){
                			keyArray[2] = key;
                		}else if (name.equals("bike4")){
                			keyArray[3] = key;
                		}
                	}
                	
                	logic.modifyDirections(keyArray);
                	logic.update();
                	String[][] matrixShared = logic.generateMatrix();
                	JSONArray JAMain = ServerJson.encodeJArray(matrixShared);
                	Node<PrintWriter> temp = listPrintWriter.getHead();
                	for (int i = 0;i<listPrintWriter.getLenght();i++){
                		PrintWriter writer = temp.getData();
                		writer.println(JAMain);
                		temp = temp.getNextNode();
                	}
                	
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
        */
    

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
