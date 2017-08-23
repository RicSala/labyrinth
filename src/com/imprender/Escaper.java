package com.imprender;

/**
 * This class represents the player (the "escaper")
 */
public class Escaper {
	private int vPosition;
	private int hPosition;


	public Escaper(int vPosition, int hPosition) {
		this.vPosition = vPosition;
		this.hPosition = hPosition;
	}

	public int getVPosition() {
		return vPosition;
	}

	public int getHPosition() {
		return hPosition;
	}


	//	------ ESCAPER MOVES ------
	public void moveUp() {
		this.vPosition--;
	}

	public void moveDown() {
		this.vPosition++;
	}

	public void moveRight() {
		this.hPosition++;
	}

	public void moveLeft() {
		this.hPosition--;
	}
	//	------ END OF ESCAPER MOVES ------


}
