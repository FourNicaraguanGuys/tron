package server.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import server.serverJson.ServerJson;

public class Handler extends Thread {
    private String Jstring;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    static ServerJson serverJson = new ServerJson();
    static JSONArray JAMain;
   


    public Handler(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        try {

            // Create character streams for the socket.
            in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
         
            while (true) {
                Jstring = in.readLine();
                JSONArray js = serverJson.encodeJArray(serverJson.decodeJArray(Jstring));
                if (js.get(0)!= null && js.get(1) != null){
                	Integer r = (Integer) js.get(0);
                	Integer c = (Integer) js.get(1);
                	JAMain = serverJson.createJson(r, c);
                	System.out.println(JAMain);
                	out.print(JAMain);
                	break;
                } 
            }
           
            while (true) {
                String input = in.readLine();
                if (input == null) {
                    return;
                }else{
                	String name = serverJson.decodeJSPlayer(input);
                	String key = serverJson.decodeJSKey(input);
                	System.out.println("Jugador: " + name + " apretó " + key );
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (ParseException e) {
			e.printStackTrace();
		} finally {
            
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
