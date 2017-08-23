package com.imprender;

public class Labyrinth {
	private final int[] INITIAL_ESCAPER_POSITION;
	private final char WALL_CHARACTER;
	private final int[] COIN_POSITION;
	private Field map;                              //Constains the game field

	private boolean[][] discovered;                 //Field with true values there where the escaper has already been


	/**
	 *
	 * @param map
	 * @param WALL_CHARACTER
	 * @param INITIAL_POSITION
	 * @param coinPosition
	 */
	public Labyrinth(Field map, char WALL_CHARACTER, int[] INITIAL_POSITION, int[] coinPosition) {
		this.map = map;
		discovered = new boolean[map.getHeight()][map.getWidth()];
		System.out.println(map.getHeight());
		for (int i = 0; i < discovered.length; i++) {
			for (int j = 0; j<discovered[0].length; j++) {
				discovered[i][j] = false;
			}
		}
		discovered[INITIAL_POSITION[0]][INITIAL_POSITION[1]] =true;
		this.WALL_CHARACTER = WALL_CHARACTER;
		this.INITIAL_ESCAPER_POSITION = INITIAL_POSITION;
		this.COIN_POSITION = coinPosition;
	}


	public Field getMap() {
		return map;
	}

	public boolean isWall(int vPosition, int hPosition) {
		return map.get(vPosition, hPosition) == WALL_CHARACTER;
	}

	public char getWALL_CHARACTER() {
		return WALL_CHARACTER;
	}

	public int[] getINITIAL_ESCAPER_POSITION() {
		return INITIAL_ESCAPER_POSITION;
	}

	public boolean outOfBorders(int[] moveDestiny) {

		boolean outOfBorders = !((moveDestiny[0] >= 0 && moveDestiny[0] < map.getHeight()) &&
				(moveDestiny[1] >= 0 && moveDestiny[1] < map.getWidth()));

		return outOfBorders;
	}

	public boolean[][] getDiscovered() {
		return discovered;
	}

	public boolean isCoin(int vPosition, int hPosition) {
		return COIN_POSITION[0] == vPosition && COIN_POSITION[1] == hPosition;
	}
}
