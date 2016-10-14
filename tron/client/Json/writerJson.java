package tron.client.Json;

public class writerJson {
    private String columns;
    private String rows;
    private int userId;
    private String gamaeId;
    private String action;
    private String direction;
    
    public void dimensions (int userId ,String action, String columns, String rows){
    	this.userId = userId;
    	this.action = action;
    	this.columns = columns;
    	this.rows = rows;
    }
    
    public void signals(int userId, String action, String direction){ //gameId
    	this.userId = userId;
    	this.action = action;
    	this.direction = direction;
    }
    
/**
	    public static void main (String[] args){
	    	writerJson obj = new writerJson("2", "3");
	    	Gson gson = new Gson();
			String jsonString = gson.toJson(obj);
	    	System.out.println(jsonString);
	    }
**/
}
