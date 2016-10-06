package tron.client.Json;

import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class clientJson {
	
	public String[][] readJSON(String jArray) throws ParseException {
	    JSONParser parser = new JSONParser();
	    String[][] dmatrix = null;
	    try{
	      Object ob = parser.parse(jArray);
	      JSONArray jsonArray = (JSONArray) ob;
	      JSONArray rowArray =  (JSONArray) jsonArray.get(0);
	      String[][] matrix = new String[jsonArray.size()][rowArray.size()];
	      
	      for (int iRow = 0; iRow < jsonArray.size(); iRow++){
	        rowArray = (JSONArray) jsonArray.get(iRow);
	        String[] dataArray = new String[rowArray.size()];
	        for (int jColumn = 0; jColumn < rowArray.size(); jColumn++){
	          dataArray[jColumn] = (String) rowArray.get(jColumn);
	        }
	        matrix[iRow] = dataArray;
	      }
	      dmatrix = matrix;
	      
	     } catch (ParseException e) {  
	           e.printStackTrace();  
	       }    
	    return dmatrix;
	      
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
	
	public String decodeJsMatrix(String JString){
		JSONParser parser = new JSONParser();
	    String state = null;    
	    try {  
	         
	       Object obj = parser.parse(JString);       
	       JSONObject jsonObject = (JSONObject) obj;  
	         
	       state = (String) jsonObject.get("matrix");
	         
	       } catch (ParseException e) {  
	         e.printStackTrace();  
	        }      
	    return state;
	}
	
}
