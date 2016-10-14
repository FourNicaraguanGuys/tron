package tron.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;

import tron.client.Json.*;

public class clientLogic extends Thread{
	//****************************************//
	private BufferedReader dataIn;
	private PrintWriter dataOut;
	//****************************************//
	private int port = 9001;
	private String host = "";
	private Socket socketClient;
	public String[][] arrayMap;
	private String jsonId;
	private int intId;
	//****************************************//
	Gson jsonConvert;
	Gson jsonConvertAction;
	//****************************************//
	private clientJson cj = new clientJson();
	private gamePanelNU game;
	//****************************************//
	
	
	public clientLogic(String ip, String rows, String columns, boolean nGame){
		this.host = ip;
		game =new gamePanelNU();
		try{
			socketClient = new Socket(host, port);
			dataIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			dataOut = new PrintWriter(socketClient.getOutputStream(), true);
			if (nGame == true){
				jsonId = dataIn.readLine();
				System.out.println(jsonId);
				intId = cj.decodeIdPlayer(jsonId);
				System.out.println(intId);
				sendMatrix(intId, columns, rows);
				String json = dataIn.readLine();
				System.out.println(json);
				arrayMap = cj.decodeBlocks(json);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		game.createJpanels(arrayMap);
		game.setArray(arrayMap);
		game.repaint();
	}
	

	public void run(){
		while(true){
			try{
				sendAction(intId, "updateDirection", "forward");
				Thread.sleep(500);
				String datos = dataIn.readLine();
				String status = cj.decodeJsStatus(datos);
				
				if (status.equals("inGame")) {
				
					arrayMap = cj.decodeBlocks(datos);
				
					game.setArray(arrayMap);
					game.repaint();
				
					game.createJpanels(arrayMap);
					game.getFrame().repaint();
				
					System.out.println(datos);
				}	
				else {
					System.out.println(status);
					break;
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

	}


	public void sendMatrix(int sId, String columns, String rows){
		writerJson matrix = new writerJson();
		matrix.dimensions(sId,"newGame", columns, rows);
		jsonConvert = new Gson();
		String jsonString = jsonConvert.toJson(matrix);
		System.out.println(jsonString + " signal");
		dataOut.println(jsonString);
	}
	
	public void sendAction(int id, String event, String direction){
		writerJson action = new writerJson();
		action.signals(id, event, direction);
		jsonConvertAction = new Gson();
		String jsonStringAction = jsonConvertAction.toJson(action);
		System.out.println(jsonStringAction);
		dataOut.println(jsonStringAction);
	}
	
	public String[][] getMatrix(){
		return arrayMap;
	}


	public int getIntId() {
		return intId;
	}


	public void setIntId(int intId) {
		this.intId = intId;
	}
	
	
	
	
}
	
