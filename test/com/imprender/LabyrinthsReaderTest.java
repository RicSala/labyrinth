package com.imprender;

import org.junit.Test;

import static org.junit.Assert.*;

public class LabyrinthsReaderTest {

	@Test
	public void readMethodCreatesTheProperLabyrinth() {

		String labyrinthString = "X XXXXX\nX XX XX\nX    XX\nXX XX X\nXX  o X\nXXXXXXX";

		char[][] labyrinth = new char[6][7];
		LabyrinthsReader reader = new LabyrinthsReader();
		Labyrinth labyrinthByReader = reader.readLabyrinth(labyrinthString);

		assertEquals(labyrinth, labyrinthByReader.getMap());


	}

}