package tron.server;

import tron.data.DataAccess;
import tron.serverJson.ServerJson;
import org.json.simple.JSONObject;

/**
 * 
 * @author jeanpaul
 *
 */
public class Protocol {
	
	private DataAccess dataAccess;
	private JSONObject response;
	
	/**
	 * 
	 * @param dataAccess
	 */
	public Protocol(DataAccess dataAccess) {
		setDataAccess(dataAccess);
	}
	
	public void init(Handler handler) {
		JSONObject jsonResponse = ServerJson.encodeUserId(dataAccess.createUser(handler).getId());
		response = jsonResponse;
	}
	
	public void analyze(String jsonString) {
		String action = ServerJson.decodeAction(jsonString);
		int userId = ServerJson.decodeUserId(jsonString);
		if(action.equals("newGame")) {
			int rows = ServerJson.decodeRows(jsonString);
			int columns = ServerJson.decodeColumns(jsonString);
			dataAccess.createGame(rows,columns);
			if(!dataAccess.joinGame(userId)){
				response = ServerJson.encodeStatus("waiting");
			}
		} 
		
		else if(action.equals("joinGame")) {
				if(!dataAccess.joinGame(userId)) {
					response = ServerJson.encodeStatus("waiting");
				}
		}
		
		else if(action.equals("updateDirection")) {
			String direction = ServerJson.decodeDirection(jsonString);
			dataAccess.updateDirection(userId, direction);
			while(response == null) {}			
		}
		/*
		else if(action.equals("endConnection")) {
    		dataAccess.endUserConnection(userId);
    		response = Json
    		try {
				socket.close();
			} catch (IOException e) {e.printStackTrace();}
    		//Crea un objeto json con la respuesta
    		JSONObject jsonEnv = new JSONObject();
    		jsonEnv.put("actividad", "cerrar");
    		return jsonEnv;
    	}*/
	}
	
	public void updateGameInfo() {
		response = ServerJson.encodeStatus("gameOver");
	}
	
	public void updateGameInfo(String[][] matrix) {
		response = ServerJson.encodeJArray(matrix);
		System.out.println(response);
	}

	public DataAccess getDataAccess() {
		return dataAccess;
	}

	public void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}

	public JSONObject getResponse() {
		return response;
	}

	public void setResponse(JSONObject response) {
		this.response = response;
	}
	
}
