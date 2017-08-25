package com.imprender;

/**
 * Representes the playing field and have to util methods
 */
public class GameMapUtils {


	/**
	 * returns the map that the escaper has already discovered
	 * @param discovered (char[][]): Field containing booleans (true if coordinate is discovered)
	 * @param map (char[][]): game Field (complete and naked!)
	 * @return char[][] showing the content of the coordinates already discoverd (those with true in "discovered")
	 */
	public static char[][] getDiscoveredMap(boolean[][] discovered, char[][] map) {
		int height = map.length;
		int width = map[0].length;

		char[][] discoveredMap = new char[map.length][map[0].length];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (discovered[i][j]) {
					discoveredMap[i][j] = map[i][j];
				} else {
					discoveredMap[i][j] = '\u25A1';
				}
			}
		}
		return discoveredMap;
	}


	/**
	 * Given
	 * @param map (char[][])
	 * @param coordinate (int[])
	 * @param mark char
	 * @return mark the map in the coordinate with the mark
	 */
	public static char[][] markPositionInMap(char[][] map, Point coordinate, char mark) {
		map[coordinate.getY()][coordinate.getX()] = mark;
		return map;
	}


	/**
	 * Given
	 * @param map (char[][])
	 * @param lefttUpCorner (int[2]) --> first coordinate to delimitate the size of the copy
	 * @param rightDownCorner (int[2]) --> second coordinate to delimitate the size of the copy
	 * @return (char[][]) a copy of map
	 */
	public static char[][] getCopyMap(char[][] map, Point lefttUpCorner, Point rightDownCorner) {
		int height = rightDownCorner.getY() - lefttUpCorner.getY() + 1;
		int width = rightDownCorner.getX() - lefttUpCorner.getX() + 1;

		char[][] copyMap = new char[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				copyMap[i][j] = map[lefttUpCorner.getY() + i][lefttUpCorner.getX() + j];
			}
		}
		return copyMap;
	}


	/**
	 * Draws the given
	 * @param map
	 */
	public static void draw(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Given a target coordinate and a map
	 * @param coordinates (int[])
	 * @param map (char[][])
	 * @return true if the target coordinate is out of the labyrinth (used to avoid get an exception)
	 */
	public static boolean outOfBorders(char[][] map, Point coordinates) {

		boolean outOfBorders = !((coordinates.getY() >= 0 && coordinates.getY() < map.length) &&
				(coordinates.getX() >= 0 && coordinates.getX() < map[0].length));

		return outOfBorders;
	}
}
