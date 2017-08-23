package com.imprender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prompter {
	BufferedReader reader;

	public void wallHit() {
		System.out.println("Oooops! You just hit the wall! Try other directions!\n");
	}

	public char promptNewTurn() throws IOException {
		char choice;
		System.out.println("Choose your next move: ");
		System.out.println("'U' --> Move up");
		System.out.println("'D' --> Move down");
		System.out.println("'L' --> Move left");
		System.out.println("'R' --> Move right");

		reader = new BufferedReader(new InputStreamReader(System.in));
		choice = reader.readLine().toLowerCase().charAt(0);
		return choice;
	}

	public void winPrompt() {
		System.out.println("*** CONGRATS ***");
		System.out.println("You are out now!");
		System.out.println("Enjoy your freedom!");
		System.out.println("\n\n (Until next game...)");
	}

	public void coinFound() {
		System.out.println("YOU JUST FOUND THE COIN! YEEEEEEEHA!!!!!");
	}
}
