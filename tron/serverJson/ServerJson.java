package tron.serverJson;

import org.json.simple.JSONArray;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;

public class ServerJson {
		
	@SuppressWarnings("unchecked")
	public static JSONObject encodeUserId(int id) {
		JSONObject json = new JSONObject();
		json.put("status", "userIdCreated");
		json.put("userId", id);
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject encodeStatus(String status) {
		JSONObject json = new JSONObject();
		json.put("status", status);
		return json;
	}
	
	public static String decodeDirection(String jsonString) {
		JSONParser parser = new JSONParser();
		String direction = null;
		try {         
			Object obj = parser.parse(jsonString);  
	         
			JSONObject jsonObject = (JSONObject) obj;  
	        
			direction = (String)jsonObject.get("direction");  
	        
		} catch (ParseException e) {  
			e.printStackTrace();  
		}  
		return direction;	
	}
	
	public static int decodeUserId(String jsonString) {
		JSONParser parser = new JSONParser();
		int userId = -1;
		try {         
			Object obj = parser.parse(jsonString);  
	         
			JSONObject jsonObject = (JSONObject) obj;
	        /*
			String userIdString = (String)jsonObject.get("userId");  
			userId = Integer.parseInt(userIdString);
	        */
	        userId = (int)(long)jsonObject.get("userId");
	        
		} catch (ParseException e) {  
			e.printStackTrace();  
		}  
		return userId;	
	}
	
	public static String decodeAction(String jsonString) {
		JSONParser parser = new JSONParser();
		String action = null;   
		try {         
			Object obj = parser.parse(jsonString);  	     
			JSONObject jsonObject = (JSONObject) obj;          
			action = (String)jsonObject.get("action");  	        
		} catch (ParseException e) {  
			e.printStackTrace();  
		}  
		return action;	    
	}
	
	public static int decodeRows(String jsonString) {
		JSONParser parser = new JSONParser();
		int rows = 0;
		try {         
			Object obj = parser.parse(jsonString);  
	         
			JSONObject jsonObject = (JSONObject) obj;  
			
			String rowsString = (String)jsonObject.get("rows");
			rows = Integer.parseInt(rowsString);
	        
		} catch (ParseException e) {  
			e.printStackTrace();  
		}  
		return rows;	    
	}
	
	public static int decodeColumns(String jsonString) {

		JSONParser parser = new JSONParser();
		int columns = 0;
	      
		try {         
			Object obj = parser.parse(jsonString);  
	         
			JSONObject jsonObject = (JSONObject) obj;  
	        
			String columnsString = (String)jsonObject.get("columns");  
			columns = Integer.parseInt(columnsString);
	        
		} catch (ParseException e) {  
			e.printStackTrace();  
		}  
		return columns;	    
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray createJson(int row, int column){
		int n = 0;
		JSONArray jMatrix = new JSONArray();
		for (int i = 0; i < row; i++){
			JSONArray jrow = new JSONArray();
			for (int j = 0; j < column; j++ ){
				jrow.add(n);
				n += 1;
			}
			jMatrix.add(jrow);
		}
		return jMatrix;
	}
	
	public static int[][] decodeJArray(String jArray) throws ParseException{
		JSONParser parser = new JSONParser();
		int[][] dmatrix = null;
		try{
			Object ob = parser.parse(jArray);
			JSONArray jsonArray = (JSONArray) ob;
			JSONArray rowArray =  (JSONArray) jsonArray.get(0);
			int[][] matrix = new int[jsonArray.size()][rowArray.size()];
			
			for (int iRow = 0; iRow < jsonArray.size(); iRow++){
				rowArray = (JSONArray) jsonArray.get(iRow);
				int[] dataArray = new int[rowArray.size()];
				for (int jColumn = 0; jColumn < rowArray.size(); jColumn++){
					dataArray[jColumn] = (int)(long) rowArray.get(jColumn);
				}
				matrix[iRow] = dataArray;
			}
			dmatrix = matrix;
			
		 } catch (ParseException e) {  
	         e.printStackTrace();  
	     }    
		return dmatrix;
			
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject encodeJArray(String[][] dmatrix){
		JSONArray jMatrix = new JSONArray();
		for (int i = 0; i < dmatrix.length; i++){
			JSONArray jrow = new JSONArray();
			for (int j = 0; j < dmatrix[i].length; j++ ){
				jrow.add(dmatrix[i][j]);
			}
			jMatrix.add(jrow);
		}
		
		JSONObject matrixStatus = new JSONObject();
		matrixStatus.put("status", "inGame");
		matrixStatus.put("matrix", jMatrix);
		
		return matrixStatus;
	}
	
	public static String decodeJSPlayer(String JString){
		JSONParser parser = new JSONParser();
	    String state = null;    
	    try {  
	         
	       Object obj = parser.parse(JString);       
	       JSONObject jsonObject = (JSONObject) obj;  
	         
	       state = (String) jsonObject.get("Player");
	         
	       } catch (ParseException e) {  
	         e.printStackTrace();  
	        }      
	    return state;
	}
	public static String decodeJSKey(String JString){
		JSONParser parser = new JSONParser();
	    String state = null;    
	    try {  
	         
	       Object obj = parser.parse(JString);       
	       JSONObject jsonObject = (JSONObject) obj;  
	         
	       state = (String) jsonObject.get("Key");
	         
	       } catch (ParseException e) {  
	         e.printStackTrace();  
	        }      
	    return state;
	}
	public static int decodeJRow(String JString){
		JSONParser parser = new JSONParser();
		String ss = "";
		int state = -1;   
	    try {  
	         
	       Object obj = parser.parse(JString);       
	       JSONObject jsonObject = (JSONObject) obj;  
	         
	       //state = (int) jsonObject.get("rows");
	       ss = (String) jsonObject.get("rows");
	       state = Integer.parseInt(ss);
	       
	       } catch (ParseException e) {  
	         e.printStackTrace();  
	        }      
	    return state;
	}
	public static int decodeJColumn(String JString){
		JSONParser parser = new JSONParser();
		String ss = "";
	    int state = -1;    
	    try {  
	         
	       Object obj = parser.parse(JString);     
	       JSONObject jsonObject = (JSONObject) obj;  
	         
	       //state = (int) jsonObject.get("columns");
	       ss = (String) jsonObject.get("columns");
	       state = Integer.parseInt(ss);
	
	         
	       } catch (ParseException e) {  
	         e.printStackTrace();  
	        }      
	    return state;
	}
	
}
