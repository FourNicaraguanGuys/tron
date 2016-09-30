package tron.client.Json;

import org.json.simple.parser.*;
import org.json.simple.JSONArray;

public class clientJson {
	
	public static int[][] readJSON(String jArray) throws ParseException {
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
	
	
}
