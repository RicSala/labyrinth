package com.imprender;

/**
 * This class represents the player (the "escaper")
 * vCoordinate: Vertical coordinate in the labyrinth
 * hCoordinate: Horizontal " " " "
 */
public class Escaper {
	private Point coordinates;


	public Escaper(Point coordinates) {
		this.coordinates = coordinates;
	}

	public int getY() {
		return coordinates.getY();
	}

	public int getX() {
		return coordinates.getX();
	}


	//	------ ESCAPER MOVES ------
	public void moveUp() {
		coordinates.moveUp();
	}

	public void moveDown() {
		coordinates.moveDown();
	}

	public void moveRight() {
		coordinates.moveRight();
	}

	public void moveLeft() {
		coordinates.moveLeft();
	}

	/**
	 * Given a current position of the escaper, this methods moves it if the destination is adyacent
	 *
	 * @param destination to move
	 * @throws IllegalMoveException in case destionation is not adyacent
	 */
	public void set(Point destination) throws IllegalMoveException {

		int moveDistance = Math.abs((destination.getY() - coordinates.getY())) +
				Math.abs(destination.getX() - coordinates.getX());

		if (moveDistance > 1) {
			throw new IllegalMoveException();
		} else {
			coordinates = destination;
		}
	}

	public Point getCoordinates() {
		return coordinates;
	}
	//	------ END OF ESCAPER MOVES ------
}
