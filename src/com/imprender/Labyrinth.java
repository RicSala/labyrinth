package com.imprender;

public class Labyrinth {
	private final Point initialEscaperPosition;
	private final char wallCharacter;
	private final Point coinPosition;
	private char[][] map;

	private boolean[][] discovered;


	/**
	 * @param map             (String[][]): playing field of the game
	 * @param wallCharacter   (char): character that represents a wall
	 * @param initialPosition (int[2]): initial position of the player
	 * @param coinPosition     (int[2]): position of the coin if it exists
	 *                         discovered (boolean[][]): GameMapUtils of the same size of the game field keep the places the escaper has already
	 *                         discovered
	 */
	public Labyrinth(char[][] map, char wallCharacter, Point initialPosition, Point coinPosition) {
		this.map = map;
		discovered = new boolean[map.length][map[0].length];

		//Initializing the discovered array to false in all the positions...
		for (int i = 0; i < discovered.length; i++) {
			for (int j = 0; j < discovered[0].length; j++) {
				discovered[i][j] = false;
			}
		}
		//...except the initial position
		discovered[initialPosition.getY()][initialPosition.getX()] = true;
		this.wallCharacter = wallCharacter;
		this.initialEscaperPosition = initialPosition;
		this.coinPosition = coinPosition;
	}


	public char[][] getMap() {
		return map;
	}

	public Point getInitialEscaperPosition() {
		return initialEscaperPosition;
	}

	public boolean[][] getDiscovered() {
		return discovered;
	}

	/**
	 * Given a coordinate composed by:
	 *
	 * @param coordinates to check
	 * @return true if the labyrinth has a wall in that coordinate
	 */
	public boolean isWall(Point coordinates) {
		return map[coordinates.getY()][coordinates.getX()] == wallCharacter;
	}

	/**
	 * Given a coordinate composed by:
	 *
	 * @param coordinates to check
	 * @return true if the labyrinth has a coin in that coordinate
	 */
	public boolean isCoin(Point coordinates) {
		return coordinates == coinPosition;
	}


	/**
	 * Given a target coordinate of
	 * @param coordinate (int[])
	 * @return true if the target coordinate is out of the labyrinth (used to avoid get an exception)
	 */
	public boolean outOfBorders(Point coordinate) {
		return GameMapUtils.outOfBorders(map, coordinate);
	}

}
