package tron.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.simple.JSONArray;

import com.google.gson.Gson;

import tron.client.json.clientJson;
import tron.client.json.writerJson;

public class clientLogic extends Thread {
	private BufferedReader dataIn;
	private PrintWriter dataOut;
	private int port = 9001;
	private String host = "";
	private Socket socketClient;
	public int[][] arrayMap;
	private String columns;
	private String rows;
	
	public clientLogic (String ip){
		this.host = ip;
		try{
			socketClient = new Socket (host, port);
			dataIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			dataOut = new PrintWriter(socketClient.getOutputStream(), true);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		try{
			while (true){
				String arrayJson = dataIn.readLine();
				System.out.println(arrayJson);
				arrayMap = clientJson.readJSON(arrayJson);//transforma a un array
				arrayMap[0][1] = 0;
				System.out.print(getArray());
				JSONArray Json1 = clientJson.encodeJSON(arrayMap);
				dataOut.println(Json1);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void firstConnection(){
    	writerJson obj = new writerJson(columns, rows);
    	Gson gson = new Gson();
		String jsonString = gson.toJson(obj);
    	dataOut.println(jsonString);
	}
	
	public int[][] getArray (){
		return arrayMap;
	}
	
	public void getRows(String nRowsc){
		this.rows = nRowsc;
	}
	public void getColumns(String nColumnsc){
		this.columns = nColumnsc;
	}

}
	
	
	

	
/**	
	private BufferedReader dataIn;
	private PrintWriter dataOut;
	private int port = 9001;
	private String host = "";
	private Socket socketClient;
	public int[][] arrayMap;
	private int nRow = 0;
	private int nColumn = 0;
	
	
	public clientLogic (String ip){
		this.host = ip;
		try{
			socketClient = new Socket (host, port);
			dataIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			dataOut = new PrintWriter(socketClient.getOutputStream(), true);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while (true){
				String arrayJson = dataIn.readLine();
				System.out.println(arrayJson);
				arrayMap = clientJson.readJSON(arrayJson);//transforma a un array
				arrayMap[0][1] = 0;
				System.out.print(getArray());
				JSONArray Json1 = clientJson.encodeJSON(arrayMap);
				dataOut.println(Json1);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public void setRow (int row){
		this.nRow = row;
	}
	
	public void setColumn(int column){
		this.nColumn = column;
	}
	
	public int[][] getArray (){
		return arrayMap;
	}
}
**/