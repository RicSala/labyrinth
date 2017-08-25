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
		LabyrinthsReader reader = new LabyrinthsReader();
		labyrinth = reader.readLabyrinth("labyrinth.txt", Charset.defaultCharset());
		escaper = new Escaper(labyrinth.getInitialEscaperPosition());
		prompter = new Prompter();
		//	Labyrinth labyrinth = reader.readLabyrinth(labyrinthString);

	}


	/**
	 * Draw the discovered map and keep the game running until the player wins.
	 */
	public void run() throws IllegalMoveException {
		while (!out) {
			char[][] map = GameMapUtils.getDiscoveredMap(labyrinth.getDiscovered(), labyrinth.getMap());
			map = GameMapUtils.markPositionInMap(map, escaper.getCoordinates(), 'o');
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

		Point destination = getDestination();
		if (validateDestination(destination)) {
			escaper.set(destination);
		}
	}

	/**
	 * Check the destination chosen and perform the appropiate action if: escaped / wall / valid move [coin]
	 *
	 * @param destination to check
	 * @return boolen true if the escaper can move, false otherwise (wall or out)
	 */
	private boolean validateDestination(Point destination) {
		//Once the destination is updated, we check what kind of destination has been chosen:
		// 1) Out of the map --> Win
		if (labyrinth.outOfBorders(destination)) {
			out = true;
			return false;
		}
		// 2) Wall --> destional is a wall, method returns null
		else if (labyrinth.isWall(destination)) {
			prompter.wallHit();
			labyrinth.getDiscovered()[destination.getY()][destination.getX()] = true;  //TODO: por qué aquí puedo modificar con un get?
			return false;
		} else {

			// 3) Coin --> The escaper collects the coin and...
			if (labyrinth.isCoin(destination)) {
				prompter.coinFound();
			}
			labyrinth.getDiscovered()[destination.getY()][destination.getX()] = true;
			return true;
		}
	}

	//Todo: Por cierto, te recomiendo que Labyrinth sea una clase que contiene el mapa y la posición
	// actual del jugador (de hecho casi ya lo tienes así). Así Game le puede decir a Labyrinth hacia dónde quiere moverse,
	// y será Labyrinth quien decida si ese movimiento es posible (si no, por ejemplo simplemente dejamos el jugador en la misma posición).
	// Game no tiene por qué saber los detalles del Labyrinth (me refiero a la función `validateDestination` por ejemplo)
	//Todo: @ric Cuanto más simples sean las clases mejor

	/**
	 * Prompt the user for a movement and
	 *
	 * @return the choosen destination (without validating that is appropiate)
	 */
	private Point getDestination() {
		char move;
		Point destination = new Point(escaper.getX(), escaper.getY());
		try {
			move = prompter.promptNewTurn();
			//change destination acordingly to the chosen move
			switch (move) {
				case 'u':
					destination.setY(destination.getY() - 1);
					break;
				case 'd':
					destination.setY(destination.getY() + 1);
					break;
				case 'r':
					destination.setX(destination.getX() + 1);
					break;
				case 'l':
					destination.setX(destination.getX() - 1);
					break;
			}

		} catch (
				IOException e) {
			e.printStackTrace();
		}
		return destination;
	}
}