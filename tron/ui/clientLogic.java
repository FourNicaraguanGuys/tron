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
	//****************************************//
	Gson jsonConvert;
	Gson jsonConvertAction;
	//****************************************//
	private clientJson cj = new clientJson();
	//****************************************//
	
	
	public clientLogic(String ip, String rows, String columns, boolean nGame){
		this.host = ip;
		try{
			socketClient = new Socket(host, port);
			dataIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			dataOut = new PrintWriter(socketClient.getOutputStream(), true);
			if (nGame == true){
				sendMatrix(columns, rows);
				String json = dataIn.readLine();
				System.out.println(json);
				arrayMap = cj.readJSON(json);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public void run(){
		while(true){
			try{
				String datos = dataIn.readLine();
				System.out.println(datos);
				//arrayMap = cj.readJSON(datos);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

	}
	

	public void sendMatrix(String columns, String rows){
		writerJson matrix = new writerJson();
		matrix.dimensions(columns, rows);
		jsonConvert = new Gson();
		String jsonString = jsonConvert.toJson(matrix);
		dataOut.println(jsonString);
	}
	
	public void sendAction(String id, String event){
		writerJson action = new writerJson();
		action.signals(id, event);
		jsonConvertAction = new Gson();
		String jsonStringAction = jsonConvertAction.toJson(action);
		dataOut.println(jsonStringAction);
	}
	
	public String[][] getMatrix(){
		return arrayMap;
	}
	
}
	
