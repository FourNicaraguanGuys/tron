package tron.data;

import tron.logic.Game;
import tron.server.Handler;

public class User {
	
	private int id;
	private Handler handler;
	private Game game; //<<<<<<<<<<<<<<<<<<<<<<revisar si necesario
	
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
