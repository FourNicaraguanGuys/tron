package tron.data;

import linkedlist.simple.List;
import linkedlist.simple.Node;
import tron.logic.Game;
import tron.server.Handler;

public class Data implements DataAccess {
	
	private static int userIdCounter;
	//private static int gameIdCounter;
	private List<User> userList;
	private Game game;
	//private List<Logic> gameList;
	
	public Data(){
		setUserList(new List<User>());
	}
	
	public User createUser(Handler handler) {
		User user = new User(generateUserId(),handler);
		userList.insertHead(user);
		return user;
	}
	
	public Game createGame(int rows, int columns) {
		game = new Game(rows,columns);
		return game;
	}
	
	public boolean joinGame(int userId) {
		boolean joined = false;
		if(game.assignBikeToPlayer(userId)) {
			joined = true;
			getUser(userId).getHandler().getProtocol().updateGameInfo(game.generateMatrix()); 
		}
		return joined;	
	}
	
	public void updateDirection(int userId, String direction) {
		game.modifyDirection(userId,direction);
		if(game.isUpdated()) {
			if (game.isGameOver()) {
				Node<User> userListPointer = userList.getHead();
				while(userListPointer != null) {
					userListPointer.getData().getHandler().getProtocol().updateGameInfo();
					userListPointer = userListPointer.getNextNode();	
				}
			}
			else {
				Node<User> userListPointer = userList.getHead();
				while(userListPointer != null) {
					userListPointer.getData().getHandler().getProtocol().updateGameInfo(game.generateMatrix());
					userListPointer = userListPointer.getNextNode();	
				}
			}	
			game.outdate();
		}
	}
	
	public User getUser(int userId) {
		Node<User> userListPointer = userList.getHead();
		User user = null;
		while(userListPointer != null) {
			if(userListPointer.getData().getId() == userId) {
				user = userListPointer.getData();
				break;
			}
			userListPointer = userListPointer.getNextNode();
		}
		return user;
	}
	

	

	

	
	public int generateUserId() {
		return userIdCounter++;
	}
	
	
	
	public void setMatrixSize(int rowLenght, int columnLenght) {
		// TODO Auto-generated method stub
		
	}

	public void generateBikes() {
		// TODO Auto-generated method stub
		
	}
	
	public void modifyDirections(String[] directionsArray) {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public String[][] generateMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
}
