package com.imprender;

import java.io.IOException;
import java.nio.charset.Charset;

public class Game {

	String labyrinthString = "X XXXXX\nX XX XX\nX    XX\nXX XX X\nXX    X\nXXXXXoX";
	LabyrinthsReader reader = new LabyrinthsReader();
	Labyrinth labyrinth = reader.readLabyrinth("labyrinth.txt", Charset.defaultCharset()); //todo: Por alguna razón si lo hago así me coge un caracter más del String
//	Labyrinth labyrinth = reader.readLabyrinth(labyrinthString);
	Escaper escaper = new Escaper(labyrinth.getINITIAL_POSITION()[0], labyrinth.getINITIAL_POSITION()[1]);
	Prompter prompter = new Prompter();
	private boolean out = false;

	public Game() throws IOException {
	}

	public void run() {
		while (!out) {
			labyrinth.getMap().getDiscoveredMap(labyrinth.getDiscovered()).markPositionInMap(escaper.getVPosition(), escaper.getHPosition()).draw();
			out = turn();
		}
		prompter.winPrompt();
	}



	public boolean turn() {
		char move;
		int[] moveDestiny = new int[]{escaper.getVPosition(), escaper.getHPosition()};

		try {
			move = prompter.promptNewTurn();

			switch (move) {
				case 'u':
					moveDestiny[0]--;
					break;
				case 'd':
					moveDestiny[0]++;
					break;
				case 'r':
					moveDestiny[1]++;
					break;
				case 'l':
					moveDestiny[1]--;
					break;
			}

			if (labyrinth.outOfBorders(moveDestiny)) {
				return true;
			} else if (labyrinth.isWall(moveDestiny[0], moveDestiny[1])) {
				prompter.wallHit();
			} else {
				if (labyrinth.isCoin(moveDestiny[0], moveDestiny[1])) {
					prompter.coinFound();
				}
				switch (move) {
					case 'u':
						escaper.moveUp();
						break;
					case 'd':
						escaper.moveDown();
						break;
					case 'r':
						escaper.moveRight();
						break;
					case 'l':
						escaper.moveLeft();
						break;
				}
			}

		} catch (
				IOException e)

		{
			e.printStackTrace();
		}
		labyrinth.getDiscovered()[moveDestiny[0]][moveDestiny[1]] = true;
		return false;
	}
}
