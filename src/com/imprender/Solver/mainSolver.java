package com.imprender.Solver;

import com.imprender.Labyrinth;
import com.imprender.LabyrinthsReader;

import java.io.IOException;
import java.nio.charset.Charset;

public class mainSolver {
	public static void main(String[] args) throws IOException, InterruptedException {
		LabyrinthsReader reader = new LabyrinthsReader();
		Labyrinth labyrinth = reader.readLabyrinth("labyrinth2.txt", Charset.defaultCharset());

		Solver solver = new Solver(labyrinth);

		solver.solve();
	}

}
