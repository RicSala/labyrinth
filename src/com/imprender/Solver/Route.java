package com.imprender.Solver;

import com.imprender.Labyrinth;

public class Route {
	private int[] head;


	public Route(int[] initialPosition) {
		head = initialPosition;
	}

	public String explore(Labyrinth labyrinth) {
		String options = "";
		if (labyrinth.getMap().get(head[0] - 1, head[1]) == ' ') {
			options += 'U';
		}
		if (labyrinth.getMap().get(head[0], head[1] + 1) == ' ') {
			options += 'R';
		}
		if (labyrinth.getMap().get(head[0] + 1, head[1]) == ' ') {
			options += 'D';
		}
		if (labyrinth.getMap().get(head[0], head[1] - 1) == ' ') {
			options += 'L';
		}
		return options;
	}

	public int[] getHead() {
		return head;
	}

	public void moveUp() {
		head[0]++;
	}

	public void advance(String options) {

		if (options.contains("U")) {
			head[0]--;
		}
		if (options.contains("R")) {
			head[1]++;
		}
		if (options.contains("D")) {
			head[0]++;
		}
		if (options.contains("L")) {
			head[1]--;
		}
	}
}
