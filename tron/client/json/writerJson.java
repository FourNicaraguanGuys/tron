package tron.client.json;

import com.google.gson.Gson;

public class writerJson {
    private String columns;
    private String rows;
    public writerJson (String columns, String rows) {
        this.setColumns(columns);
        this.setRows(rows);

    }
    

	    public static void main (String[] args){
	    	writerJson obj = new writerJson("2", "3");
	    	Gson gson = new Gson();
			String jsonString = gson.toJson(obj);
	    	System.out.println(jsonString);
	    }


		public String getColumns() {
			return columns;
		}


		public void setColumns(String columns) {
			this.columns = columns;
		}


		public String getRows() {
			return rows;
		}


		public void setRows(String rows) {
			this.rows = rows;
		}

}
