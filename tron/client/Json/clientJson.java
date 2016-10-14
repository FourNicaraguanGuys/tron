package tron.client.Json;

import org.json.simple.parser.*;

import linkedlist.simple.List;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class clientJson {
	
	public String[][] decodeBlocks(String serverInput) {

	      JSONParser parser = new JSONParser();
	      String[][] stringElements = null;
	      
	      try {        
	         Object obj = parser.parse(serverInput);       
	         JSONObject jsonObject = (JSONObject) obj;     
	         JSONArray jsonElementsArray = (JSONArray) jsonObject.get("matrix");
	         String[][] elementStringArray = new String[jsonElementsArray.size()][((JSONArray) (jsonElementsArray.get(0))).size()];
	         
	         for(int row = 0; row < elementStringArray.length; row++){
	            JSONArray jsonRowArray = (JSONArray) jsonElementsArray.get(row);   
	            String[] columnArray = new String[jsonRowArray.size()];
	            
	            for (int column = 0; column < columnArray.length; column++) {
	               columnArray[column] = (String)jsonRowArray.get(column);
	            }
	            elementStringArray[row] = columnArray;
	         }
	         stringElements = elementStringArray;
	      } catch (ParseException e) {  
	         e.printStackTrace();  
	      }      
	      return stringElements;
	   }
	
	public static JSONArray encodeJSON(int[][] dmatrix){
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
	
	public int decodeIdPlayer(String JString){
		JSONParser parser = new JSONParser();
	    int state = -1;    
	    try {  
	         
	       Object obj = parser.parse(JString);       
	       JSONObject jsonObject = (JSONObject) obj;  
	         
	       state = (int)(long) jsonObject.get("userId");
	         
	       } catch (ParseException e) {  
	         e.printStackTrace();  
	        }      
	    return state;
	}
	public String decodeJsStatus(String JString){
		JSONParser parser = new JSONParser();
	    String state = null;    
	    try {  
	         
	       Object obj = parser.parse(JString);       
	       JSONObject jsonObject = (JSONObject) obj;  
	         
	       state = (String) jsonObject.get("status");
	         
	       } catch (ParseException e) {  
	         e.printStackTrace();  
	        }      
	    return state;
	}
	/**
	public String[][] decodeJsMatrix(String JString){
		JSONParser parser = new JSONParser();
	    String state = null;
	    String[][] matrix = null;
	    
	    try {  
	         
	       Object obj = parser.parse(JString);       
	       JSONObject jsonObject = (JSONObject) obj;  
	         
	       //state = (String) jsonObject.get("matrix");
	       
	       matrix = readJSON((JSONArray) jsonObject.get("matrix"));
	         
	       } catch (ParseException e) {  
	         e.printStackTrace();  
	        }      
	    return matrix;
	}**/
	
	public void encodeJArray(String[][] dmatrix){
		  for (int i = 0; i < dmatrix.length; i++){
		   for (int j = 0; j < dmatrix[i].length; j++ ){
		    String pinga = dmatrix[i][j];
		    if(pinga.equals(null)) {
		    	
		    }
		   }
		  }
	}
}
