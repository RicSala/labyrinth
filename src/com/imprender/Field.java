package com.imprender;

/**
 * Representes the playing field and have to util methods
 */
public class Field {

	private char[][] field;
	private int height;
	private int width;

	/**
	 * Creates a new game field give a
	 * @param char array with
	 */
	public Field(char[][] field) {
		this.height = field.length;
		this.width = field[0].length;
		this.field = field;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public char get(int vPosition, int hPosition) {
		return field[vPosition][hPosition];
	}


	public Field getDiscoveredMap(boolean[][] discovered) {

		Field discoveredMap = new Field(new char[height][width]);

		for (int i = 0; i <height; i++) {
			for (int j = 0; j < width; j++) {
				if (discovered[i][j]) {
					discoveredMap.field[i][j] = field[i][j];
				} else {
					discoveredMap.field[i][j] = '\u25A1';
				}
			}
		}

		return discoveredMap;
	}


	public Field markPositionInMap(int vPosition, int hPosition) {
		Field mapWithPosition = getCopyMap(new int[]{0, 0}, new int[]{height - 1, width - 1});
		mapWithPosition.field[vPosition][hPosition] = 'o';

		return mapWithPosition;
	}


	private Field getCopyMap(int[] lefttUpCorner, int[] rightDownCorner) {
		int height = rightDownCorner[0] - lefttUpCorner[0] + 1;
		int width = rightDownCorner[1] - lefttUpCorner[1] + 1;

		Field copyMap = new Field(new char[height][width]);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				copyMap.field[i][j] = field[lefttUpCorner[0] + i][lefttUpCorner[1] + j];
			}
		}
		return copyMap;
	}





	public void draw(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public void draw() {
		draw(field);
	}
}
