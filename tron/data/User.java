package tron.data;

import tron.server.Handler;

public class User {
	
	private int id;
	private Handler handler;
	
	public User(int id, Handler handler) {
		setId(id);
		setHandler(handler);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
