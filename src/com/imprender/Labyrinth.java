package com.imprender;

public class Labyrinth {
	private final int[] INITIAL_ESCAPER_POSITION;
	private final char WALL_CHARACTER;
	private final int[] COIN_POSITION;
	private char[][] map;                              //Contains the game field

	private boolean[][] discovered;


	/**
	 * @param map              (String[][]): playing field of the game
	 * @param WALL_CHARACTER   (char): character that represents a wall
	 * @param INITIAL_POSITION (int[2]): initial position of the player
	 * @param coinPosition     (int[2]): position of the coin if it exists
	 *                         discovered (boolean[][]): GameMapUtils of the same size of the game field keep the places the escaper has already
	 *                         discovered
	 */
	public Labyrinth(char[][] map, char WALL_CHARACTER, int[] INITIAL_POSITION, int[] coinPosition) {
		this.map = map;
		discovered = new boolean[map.length][map[0].length];

		//Initializing the discovered array to false in all the positions...
		for (int i = 0; i < discovered.length; i++) {
			for (int j = 0; j < discovered[0].length; j++) {
				discovered[i][j] = false;
			}
		}
		//...except the initial position
		discovered[INITIAL_POSITION[0]][INITIAL_POSITION[1]] = true;
		this.WALL_CHARACTER = WALL_CHARACTER;
		this.INITIAL_ESCAPER_POSITION = INITIAL_POSITION;
		this.COIN_POSITION = coinPosition;
	}


	public char[][] getMap() {
		return map;
	}

	public int[] getINITIAL_ESCAPER_POSITION() {
		return INITIAL_ESCAPER_POSITION;
	}

	public boolean[][] getDiscovered() {
		return discovered;
	}

	/**
	 * Given a coordinate composed by:
	 *
	 * @param vPosition to check
	 * @param hPosition " " " "
	 * @return true if the labyrinth has a wall in that coordinate
	 */
	public boolean isWall(int vPosition, int hPosition) {
		return map[vPosition][hPosition] == WALL_CHARACTER;
	}

	/**
	 * Given a coordinate composed by:
	 *
	 * @param vPosition to check
	 * @param hPosition " " " "
	 * @return true if the labyrinth has a coin in that coordinate
	 */
	public boolean isCoin(int vPosition, int hPosition) {
		return COIN_POSITION[0] == vPosition && COIN_POSITION[1] == hPosition;
	}


	/**
	 * Given a target coordinate of
	 * @param moveDestiny (int[])
	 * @return true if the target coordinate is out of the labyrinth (used to avoid get an exception)
	 */
	public boolean outOfBorders(int[] moveDestiny) {

		boolean outOfBorders = !((moveDestiny[0] >= 0 && moveDestiny[0] < map.length) &&
				(moveDestiny[1] >= 0 && moveDestiny[1] < map[0].length));

		return outOfBorders;
	}

	public char getContent(int vCoordinate, int hCoordinate) {
		return map[vCoordinate][hCoordinate];
	}
}
