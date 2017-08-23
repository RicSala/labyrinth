package com.imprender.Solver;

import com.imprender.Labyrinth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Solver {
	Labyrinth labyrinth;
	String[][] map;
	int[] initialPosition = new int[2];
	List<Route> routes = new ArrayList<>();


	public Solver(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
		this.map = new String[labyrinth.getMap().getHeight()][labyrinth.getMap().getWidth()];
		initialPosition[0] = labyrinth.getINITIAL_ESCAPER_POSITION()[0];
		initialPosition[1] = labyrinth.getINITIAL_ESCAPER_POSITION()[1];
	}

	//TODO: me salta una concurrentmodificationexception...
	public Route solve() throws InterruptedException {
		Route winningRoute = null;
		boolean solved = false;
		routes.add(new Route(initialPosition));
		while (!solved) {
			for (Route route : routes) {
				String options = route.explore(labyrinth);
				if (options == "") {
					routes.remove(route);
				} else if (options.length()==1) {
					route.advance(options);
				} else {
				if (options.contains("U")) {
					routes.add(new Route(new int[]{route.getHead()[0] - 1, route.getHead()[1]}));
				}
				if (options.contains("R")) {
					routes.add(new Route(new int[]{route.getHead()[0], route.getHead()[1] + 1}));
				}
				if (options.contains("D")) {
					routes.add(new Route(new int[]{route.getHead()[0] + 1, route.getHead()[1]}));
				}
				if (options.contains("L")) {
					routes.add(new Route(new int[]{route.getHead()[0], route.getHead()[1] - 1}));
				}
				TimeUnit.SECONDS.sleep(1);
			}}
		}
		System.out.println("I am doing thins...");

		return winningRoute;
	}


}
