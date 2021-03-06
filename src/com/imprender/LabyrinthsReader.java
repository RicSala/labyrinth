package com.imprender;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LabyrinthsReader {

	/**
	 * Converts a String into a labyrinth (recognises initial position and coin position)
	 * @param labyrinthString: String that contains proper labyrinth (rectangle and with initial position [and coin])
	 * @return a labyrinth
	 */
	public Labyrinth readLabyrinth(String labyrinthString) {
		String[] lines = labyrinthString.split("\\n");
		char[][] map = new char[lines.length][lines[0].length() - 1];//TODO: Ferran, Aquí tengo que quitarle un caracter para que no se salga. Le está dando a la línea tamaño n + 1...
		Point initialEscaperPosition = null;
		Point coinPosition = null;

		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[0].length() - 1; j++) { //TODO: Aquí tengo que quitarle un caracter para que no se salga
				if (lines[i].charAt(j) == 'o') {
					map[i][j] = ' ';
					initialEscaperPosition = new Point(j, i);
				} else if (lines[i].charAt(j) == '0') {
					map[i][j] = ' ';
					coinPosition = new Point(j, i);
				} else {
					map[i][j] = lines[i].charAt(j);
				}
			}
		}
		return new Labyrinth(map, 'X', initialEscaperPosition, coinPosition);
	}


	/**
	 * Overloads the method readLabyrinth to read it from a File instead of from a String
	 * @param path of the file to read
	 * @param encoding ??
	 * @return String containing the labyrinth
	 * @throws IOException
	 */
	public Labyrinth readLabyrinth(String path, Charset encoding) throws IOException {
		String labyrinthString = readFile(path, Charset.defaultCharset());
		return readLabyrinth(labyrinthString);

	}

	/**
	 * Overloads the method readLabyrinth to read it from a series of String (String[])
	 * @param labyrinthStrings: lines of the labyrinth
	 * @return String containing the labyrinth
	 */
	public Labyrinth readLabyrinth(String[] labyrinthStrings) {
		String labyrinthString = "";
		for (String string : labyrinthStrings) {
			labyrinthString += string + "\n";
		}
		return readLabyrinth(labyrinthString);
	}


	/**
	 * Read a text file and return the String of the file
	 * @param path where the file is
	 * @param encoding todo: Check Ferran
	 * @return String with the file content
	 * @throws IOException
	 */
	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] enconded = Files.readAllBytes(Paths.get(path));
		return new String(enconded, encoding);
	}


}

