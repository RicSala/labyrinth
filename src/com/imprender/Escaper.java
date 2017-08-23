package com.imprender;

/**
 * This class represents the player (the "escaper")
 * vCoordinate: Vertical coordinate in the labyrinth
 * hCoordinate: Horizontal " " " "
 */
public class Escaper {
	private int vCoordinate;
	private int hCoordinate;


	public Escaper(int vPosition, int hPosition) {
		this.vCoordinate = vPosition;
		this.hCoordinate = hPosition;
	}

	public int getVPosition() {
		return vCoordinate;
	}

	public int getHPosition() {
		return hCoordinate;
	}



	//	------ ESCAPER MOVES ------
	public void moveUp() {
		this.vCoordinate--;
	}

	public void moveDown() {
		this.vCoordinate++;
	}

	public void moveRight() {
		this.hCoordinate++;
	}

	public void moveLeft() {
		this.hCoordinate--;
	}

	/**
	 * Given a current position of the escaper, this methods moves it if the destination is adyacent
	 * @param destination to move
	 * @throws IllegalMoveException in case destionation is not adyacent
	 */
	public void set(int[] destination) throws IllegalMoveException {

		int moveDistance = Math.abs((destination[0] - vCoordinate)) +
				Math.abs(destination[1] - hCoordinate);

		if (moveDistance > 1) {
			throw new IllegalMoveException();
		} else {
			vCoordinate = destination[0];
			hCoordinate = destination[1];
		}
	}
	//	------ END OF ESCAPER MOVES ------


}
