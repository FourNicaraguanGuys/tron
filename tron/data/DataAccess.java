package tron.data;

import tron.logic.Game;
import tron.server.Handler;

public interface DataAccess {

	public User createUser(Handler handler);
	
	public Game createGame(int rows, int columns);

	public boolean joinGame(int userId);

	public void updateDirection(int userId, String direction);
	
}
