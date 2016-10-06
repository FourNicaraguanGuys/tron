package tron.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.simple.JSONArray;

import linkedlist.simple.List;
import linkedlist.simple.Node;
import tron.logic.Logic;
import tron.serverJson.ServerJson;

public class Handler extends Thread {
	
    private ServerJson serverJson = new ServerJson();
    private JSONArray JAMain;
    private List<PrintWriter> listPrintWriter = new List<PrintWriter>();
    private List<String> listbikeID = new List<String>();
    private String Jstring;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String name;
    private Logic logic;

    public Handler(Socket socket,Logic logic) {
        setSocket(socket);
        setLogic(logic);
    }

    public void run() {
        try {

            // Create character streams for the socket.
            in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            listPrintWriter.insertHead(out);
         
            while (true) {
            	System.out.println("entr�");
            	if (listbikeID.getHead() == null){
            		
            		Jstring = in.readLine();
            		if (Jstring!= null){
            			Integer r = (Integer) serverJson.decodeJRow(Jstring);
            			Integer c = (Integer) serverJson.decodeJColumn(Jstring);
            			JAMain = serverJson.createJson(r, c); //generate matrix
            			System.out.println(JAMain); 
            			out.print(JAMain);
            			out.print("hola wey");
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
                	
                	logic.modifyDirections(keyArray);
                	logic.update();
                	String[][] matrixShared = logic.generateMatrix();
                	JAMain = serverJson.encodeJArray(matrixShared);
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
    }

	public ServerJson getServerJson() {
		return serverJson;
	}

	public void setServerJson(ServerJson serverJson) {
		this.serverJson = serverJson;
	}

	public JSONArray getJAMain() {
		return JAMain;
	}

	public void setJAMain(JSONArray jAMain) {
		JAMain = jAMain;
	}

	public List<PrintWriter> getListPrintWriter() {
		return listPrintWriter;
	}

	public void setListPrintWriter(List<PrintWriter> listPrintWriter) {
		this.listPrintWriter = listPrintWriter;
	}

	public List<String> getListbikeID() {
		return listbikeID;
	}

	public void setListbikeID(List<String> listbikeID) {
		this.listbikeID = listbikeID;
	}

	public String getJstring() {
		return Jstring;
	}

	public void setJstring(String jstring) {
		Jstring = jstring;
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

	public Logic getLogic() {
		return logic;
	}

	public void setLogic(Logic logic) {
		this.logic = logic;
	}
    
}
