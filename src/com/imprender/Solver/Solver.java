package com.imprender.Solver;

import com.imprender.GameMapUtils;
import com.imprender.Labyrinth;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Solver {
	private Labyrinth labyrinth;
	private char[][] map;
	private int[] initialPosition = new int[2];
	private List<Route> routes = new ArrayList<>();


	public Solver(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
		this.map = labyrinth.getMap().clone();
		initialPosition[0] = labyrinth.getINITIAL_ESCAPER_POSITION()[0];
		initialPosition[1] = labyrinth.getINITIAL_ESCAPER_POSITION()[1];
	}

	//TODO: me salta una concurrentmodificationexception... ¿Cómo puedo modificar una list MIENTRAS la recorro? ¿Cómo evito la excepción? Recurrencia?

	/**
	 * While the labyrinth is not solve, the algorithm keeps creating new routes in any bifurcation and advance all
	 * routes in each loop, once per loop. The routes "close" the path crawled, so no other routes can crawl that path
	 * after it.
	 * When a route hits a dead-end, it dies.
	 * When a route gets out ot the labyrinth, the best solution is reached and print in the screen.
	 * This algorythm returns the optimal solution to a labyrinth.
	 * Loops and more than one exit are allowed.
	 * @return (String) with the solution's directions
	 */
	public Route solve() {
		Route winningRoute = null;
		boolean solved = false;

		//Add a first route whose head is in the initial position of the labyrinth
		routes.add(new Route(initialPosition, ""));
		GameMapUtils.markPositionInMap(map, new int[]{initialPosition[0], initialPosition[1]}, 'o');
		GameMapUtils.draw(map);
		System.out.println();

		while (!solved) {
			for (ListIterator<Route> iter = routes.listIterator(); iter.hasNext(); ) {
				Route route = iter.next();
				if (isAtBorder(route)) {
					solved = true;
					winningRoute = route;
					break;
				} else {


					String options = route.explore(labyrinth.getMap());
					if (Objects.equals(options, "")) {
						map[route.getHead()[0]][route.getHead()[1]] = 'X';
						iter.remove();
					} else if (options.length() == 1) {
						map[route.getHead()[0]][route.getHead()[1]] = 'X';
						route.advance(options);
						GameMapUtils.markPositionInMap(map, new int[]{route.getHead()[0], route.getHead()[1]}, 'o');
					} else {
						procreate(iter, route, options);
					}
				}
				drawHeads(map, routes);
			}
		}
		System.out.println("The solution to this labyrinth is: " + winningRoute.getDirections());
		return winningRoute;
	}

	/**
	 * Given a ListIterator<Route>, a route, and a set of available directions, the method creates new routes in each
	 * available direction, setting the head of them to the available adyacent coordinates.
	 * After procreation, the "parent" route dies.
	 * Child routes heritage the parent's directions and get added the new movement (the one that makes them go to the
	 * coordinate where they procreate).
	 * @param iter
	 * @param route (Route) to proceate (parent rout).
	 * @param options (String) available directions
	 */
	private void procreate(ListIterator<Route> iter, Route route, String options) {
		map[route.getHead()[0]][route.getHead()[1]] = 'X';
		iter.remove();
		if (options.contains("U")) {
			iter.add(new Route(new int[]{route.getHead()[0] - 1, route.getHead()[1]}, route.getDirections() + "U"));
		}
		if (options.contains("R")) {
			iter.add(new Route(new int[]{route.getHead()[0], route.getHead()[1] + 1}, route.getDirections() + "R"));
		}
		if (options.contains("D")) {
			iter.add(new Route(new int[]{route.getHead()[0] + 1, route.getHead()[1]}, route.getDirections() + "D"));
		}
		if (options.contains("L")) {
			iter.add(new Route(new int[]{route.getHead()[0], route.getHead()[1] - 1}, route.getDirections() + "L"));
		}
	}

	private void drawHeads(char[][] map, List<Route> routes) {
		for (Route route : routes) {
			GameMapUtils.markPositionInMap(map, new int[]{route.getHead()[0], route.getHead()[1]},'o');
		}
	}

	/**
	 * Given a
	 *
	 * @param route (Route)
	 * @return true of the route's head is in any of the borders of the map.
	 */
	public boolean isAtBorder(Route route) {
		return (GameMapUtils.outOfBorders(map, new int[]{route.getHead()[0] + 1, route.getHead()[1]}) ||
				GameMapUtils.outOfBorders(map, new int[]{route.getHead()[0] - 1, route.getHead()[1]}) ||
				GameMapUtils.outOfBorders(map, new int[]{route.getHead()[0], route.getHead()[1] + 1}) ||
				GameMapUtils.outOfBorders(map, new int[]{route.getHead()[0], route.getHead()[1] - 1}));
	}

}
