package com.imprender;

public class Labyrinth {
	private final int[] INITIAL_POSITION;
	private final char WALL_CHARACTER;
	private final int[] coinPosition;
	private Field map;

	private boolean[][] discovered;

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
		this.INITIAL_POSITION = INITIAL_POSITION;
		this.coinPosition = coinPosition;
	}


	public Field getMap() {
		return map;
	}

	public boolean isWall(int vPosition, int hPosition) {
		return map.get(vPosition, hPosition) == getWALL_CHARACTER();
	}

	public char getWALL_CHARACTER() {
		return WALL_CHARACTER;
	}

	public int[] getINITIAL_POSITION() {
		return INITIAL_POSITION;
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
		return coinPosition[0] == vPosition && coinPosition[1] == hPosition;
	}
}
