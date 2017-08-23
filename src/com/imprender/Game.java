package com.imprender;

import java.io.IOException;
import java.nio.charset.Charset;

public class Game {
	private Labyrinth labyrinth;
	private Escaper escaper;
	private Prompter prompter;
	private boolean out = false;

	/**
	 * Inizializes the game: reads the file and creates the labyrinth, instantiates the escaper (player)
	 * and the prompter.
	 *
	 * @throws IOException
	 */
	public Game() throws IOException {
		String labyrinthString = "X XXXXX\nX XX XX\nX    XX\nXX XX X\nXX    X\nXXXXXoX";
		LabyrinthsReader reader = new LabyrinthsReader();
		labyrinth = reader.readLabyrinth("labyrinth.txt", Charset.defaultCharset()); //todo: Por alguna razón si lo hago así me coge un caracter más del String
		escaper = new Escaper(labyrinth.getINITIAL_ESCAPER_POSITION()[0], labyrinth.getINITIAL_ESCAPER_POSITION()[1]);
		prompter = new Prompter();
		//	Labyrinth labyrinth = reader.readLabyrinth(labyrinthString);

	}


	/**
	 * Draw the discovered map and keep the game running until the player wins.
	 */
	public void run() throws IllegalMoveException {
		while (!out) {
			char[][] map = GameMapUtils.getDiscoveredMap(labyrinth.getDiscovered(), labyrinth.getMap());
			map = GameMapUtils.markPositionInMap(map, new int[]{escaper.getVPosition(), escaper.getHPosition()}, 'o');
			GameMapUtils.draw(map);
			turn();
		}
		prompter.winPrompt();
	}


	/**
	 * Get the user chosen destination and performs the movement if appropiate
	 *
	 * @return true if the escaper gets out of the labyrinth
	 */
	public void turn() throws IllegalMoveException {
		//destination holds the choosen destiny to check if its available/wall/outOfRage (so to avoid nullPointException)
		// Before move, destination is inizialized as the current position

		int[] destination = getDestination();
		if (validateDestination(destination)) {
			escaper.set(destination);
		}
	}

	/**
	 * Check the destination chosen and perform the appropiate action if: escaped / wall / valid move [coin]
	 * @param destination to check
	 * @return boolen true if the escaper can move, false otherwise (wall or out)
	 */
	private boolean validateDestination(int[] destination) {
		//Once the destination is updated, we check what kind of destination has been chosen:
		// 1) Out of the map --> Win
		if (labyrinth.outOfBorders(destination)) {
			out = true;
			return false;
		}
		// 2) Wall --> The escaper position does not change
		else if (labyrinth.isWall(destination[0], destination[1])) {
			prompter.wallHit();
			labyrinth.getDiscovered()[destination[0]][destination[1]] = true;
			return false;
		} else {

			// 4) Coin --> The escaper collects the coin and...
			if (labyrinth.isCoin(destination[0], destination[1])) {
				prompter.coinFound();
			}
			labyrinth.getDiscovered()[destination[0]][destination[1]] = true;
			return true;
		}
	}



	/**
	 * Prompt the user for a movement and
	 * @return the choosen destination (without validating that is appropiate)
	 */
	private int[] getDestination() {
		char move;
		int[] destination = new int[]{escaper.getVPosition(), escaper.getHPosition()};
		try {
			move = prompter.promptNewTurn();
			//change destination acordingly to the chosen move
			switch (move) {
				case 'u':
					destination[0]--;
					break;
				case 'd':
					destination[0]++;
					break;
				case 'r':
					destination[1]++;
					break;
				case 'l':
					destination[1]--;
					break;
			}

		} catch (
				IOException e) {
			e.printStackTrace();
		}
		return destination;
	}
}