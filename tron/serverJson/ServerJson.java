package tron.serverJson;

import org.json.simple.JSONArray;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;

public class ServerJson {
			

	@SuppressWarnings("unchecked")
	public JSONArray encodeJArray(String[][] dmatrix){
		JSONArray jMatrix = new JSONArray();
		for (int i = 0; i < dmatrix.length; i++){
			JSONArray jrow = new JSONArray();
			for (int j = 0; j < dmatrix[i].length; j++ ){
				jrow.add(dmatrix[i][j]);
			}
			jMatrix.add(jrow);
		}
		return jMatrix;
	}
	
	public String decodeJSPlayer(String JString){
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
	public String decodeJSKey(String JString){
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
	public int decodeJRow(String JString){
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
	public int decodeJColumn(String JString){
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
