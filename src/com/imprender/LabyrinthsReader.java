package com.imprender;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LabyrinthsReader {

	public Labyrinth readLabyrinth(String labyrinthString) {
		String[] lines = labyrinthString.split("\\n");
		char[][] map = new char[lines.length][lines[0].length() - 1];//TODO: Aquí tengo que quitarle un caracter para que no se salga
		int[] initialPosition = new int[2];
		int[] coinPosition = new int[2];

		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[0].length() - 1; j++) { //TODO: Aquí tengo que quitarle un caracter para que no se salga
				if (lines[i].charAt(j) == 'o') {
					map[i][j] = ' ';
					initialPosition[0] = i;
					initialPosition[1] = j;
				} else if (lines[i].charAt(j) == '0') {
					map[i][j] = ' ';
					coinPosition[0] = i;
					coinPosition[1] = j;
				} else {
					map[i][j] = lines[i].charAt(j);
				}
			}
		}
		return new Labyrinth(new Field(map), 'X', initialPosition, coinPosition);
	}


	public Labyrinth readLabyrinth(String Path, Charset encoding) throws IOException {
		String labyrinthString = readFile("labyrinth.txt", Charset.defaultCharset());
		return readLabyrinth(labyrinthString);

	}

	public Labyrinth readLabyrinth(String[] labyrinthStrings) {
		String labyrinthString = "";
		for (String string : labyrinthStrings) {
			labyrinthString += string + "\n";
		}
		return readLabyrinth(labyrinthString);
	}


	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] enconded = Files.readAllBytes(Paths.get(path));
		return new String(enconded, encoding);
	}


	//Todo: read from file, read from a list of Strings (String[])


}

