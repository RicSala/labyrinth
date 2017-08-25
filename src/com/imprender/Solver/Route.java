package com.imprender.Solver;

import com.imprender.Point;

public class Route {
	private Point head;
	private String directions;


	public Route(Point initialPosition, String directions) {
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
	 * @return options available to advance, being: U --> Up; R --> Right; D --> Down; L --> Left
	 */
	public String explore(char[][] map) {

		String options = "";
		if (map[head.getY() - 1][head.getX()] == ' ') {
			options += 'U';
		}
		if (map[head.getY()][head.getX() + 1] == ' ') {
			options += 'R';
		}
		if (map[head.getY() + 1][head.getX()] == ' ') {
			options += 'D';
		}
		if (map[head.getY()][head.getX() - 1] == ' ') {
			options += 'L';
		}
		return options;
	}


	public Point getHead() {
		return head;
	}

	/**
	 * The route head advance in every possible direction and adds the directions (U, R, D or L) to "directions"
	 *
	 * @param options (String) of directions
	 */
	public void advance(String options) {

		if (options.contains("U")) {
			head.setY(head.getY() - 1);
			directions += "U";
		}
		if (options.contains("R")) {
			head.setX(head.getX() + 1);
			directions += "R";
		}
		if (options.contains("D")) {
			head.setY(head.getY() + 1);
			directions += "D";
		}
		if (options.contains("L")) {
			head.setX(head.getX() - 1);
			directions += "L";
		}
	}
}
