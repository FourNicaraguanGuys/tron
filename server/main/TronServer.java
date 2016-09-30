package server.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.JSONArray;


import linkedlist.simple.List;
import server.serverJson.ServerJson;

/**
 * En esta clase se tiene la clase principal  que es el main del server
 * y una calse secundaria que maneja la lógica del juego
 * 
 * @author Manuel
 *
 */

public class TronServer {

    private static final int PORT = 9001;
    static ServerJson serverJson = new ServerJson();
    static JSONArray JAMain;
    static List<PrintWriter> listPrintWriter = new List<PrintWriter>();
    static List<String> listbikeID = new List<String>();
    

    public static void main(String[] args) throws Exception {
    	System.out.println("manu");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }
    private static class Handler extends Thread {
        private String Jstring;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String name;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {

                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                listPrintWriter.insertHead(out);
             
                while (true) {
                	System.out.println("entró");
                	if (listbikeID.getHead() == null){
                		
                		Jstring = in.readLine();
                		if (Jstring!= null){
                			Integer r = (Integer) serverJson.decodeJRow(Jstring);
                			Integer c = (Integer) serverJson.decodeJColumn(Jstring);
                			JAMain = serverJson.createJson(r, c); //generate matrix
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
                    	name = serverJson.decodeJSPlayer(input);
                    	String key = serverJson.decodeJSKey(input);
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
                    	/** Logic.modifyDireccion(keyArray);
                    	logic.update();
                    	String[][] matrixShared = logic.getMatrix();
                    	JAMain = serverJson.encodeJArray(matrixShared);
                    	for (PrintWriter writer : listPrintWriter){
                    		writer.println(JAMain);
                    	}*/
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
        }
    }

}