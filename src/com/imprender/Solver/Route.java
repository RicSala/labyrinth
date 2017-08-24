package com.imprender.Solver;

public class Route {
	private int[] head;
	private String directions;


	public Route(int[] initialPosition, String directions) {
		head = initialPosition;
		this.directions = directions;
	}

	public String getDirections() {
		return directions;
	}

	/**
	 * Explore the adyacent coordinates to the head of the route in a given
	 *
	 * @param map (char[][])
	 * @return options, being: U --> Up; R --> Right; D --> Down; L --> Left
	 */
	public String explore(char[][] map) {

		String options = "";
		if (map[head[0] - 1][head[1]] == ' ') {
			options += 'U';
		}
		if (map[head[0]][head[1] + 1] == ' ') {
			options += 'R';
		}
		if (map[head[0] + 1][head[1]] == ' ') {
			options += 'D';
		}
		if (map[head[0]][head[1] - 1] == ' ') {
			options += 'L';
		}
		return options;
	}


	public int[] getHead() {
		return head;
	}

	/**
	 * The route head advance in every possible direction and adds the direction (U, R, D or L) to "directions"
	 *
	 * @param options (String) of directions
	 */
	public void advance(String options) {

		if (options.contains("U")) {
			head[0]--;
			directions += "U";
		}
		if (options.contains("R")) {
			head[1]++;
			directions += "R";
		}
		if (options.contains("D")) {
			head[0]++;
			directions += "D";
		}
		if (options.contains("L")) {
			head[1]--;
			directions += "L";
		}
	}
}
