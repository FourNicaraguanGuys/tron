package tron.server;

public interface Constants {
	
	public static final String NORTH = "north";
	public static final String EAST = "east";
	public static final String SOUTH = "south";
	public static final String WEST = "west";
	
	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	
	public static final String BIKE = "bike";
	public static final String TRAIL = "trail";
	
	public static final int INITIAL_TRAIL_LENGHT = 3;
	public static final float FUEL_CONSUMPTION_RATE = 0.2f;
	
	public static final int MINIMUM_SPEED = 1;
	public static final int MAXIMUM_SPEED = 10;
	
	public static final int MINIMUM_FUEL = 1;
	public static final int MAXIMUM_FUEL = 100;
	
	public static final String FUEL_CELL = "fuel cell";
	public static final String TRAIL_UPGRADE = "trail upgrade";
	public static final String MINE = "mine";
	
	public static final String SHIELD = "shield";
	public static final String HYPER_SPEED = "hyper speed";
	
	public static final int MINIMUM_SPEED_BOOST = 1;
	public static final int MAXIMUM_SPEED_BOOST = 3;
	
	public static final int DEFAULT_HYPER_SPEED_MOVEMENTS = 5;
	public static final int DEFAULT_NUMBER_OF_BIKES = 4; 
	public static final int DEFAULT_USER_ID = -1;
	
	public static final int PORT_NUMBER = 9001;
}
