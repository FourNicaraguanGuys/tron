package tron.client.Json;

public class writerJson {
    private String columns;
    private String rows;
    private String ID;
    private String action;
    
    public void dimensions (String columns, String rows){
    	this.columns = columns;
    	this.rows = rows;
    }
    
    public void signals(String ID, String action){
    	this.ID = ID;
    	this.action = action;
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
