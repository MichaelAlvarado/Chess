package GameFiles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManager {
	/**
	 * Generates a txt file with the moves of the game with format of 
	 * Piece, Position1(x,y), Position2(x,y), Color
	 */
	public static void generateTextFile(ArrayList<String> moves) {
		//Write the game moves information in a single string with the printline added
		String file = "";
		for (String string : moves) {
			file += string + "\n";
		}
		//Write String moves to the File
		try {
			writeToFile("GameMoves", file);
		} catch (IOException e) {
			System.out.println("Something went wrong!");
		}	
	}
	
	/**
	 * Generate a .txt file
	 * @param fileName - name of the txt file
	 * @param message - message inside this file to save
	 * @throws IOException - it may throw the exception from the java constructors
	 */
	private static void writeToFile(String fileName, String message) throws IOException {
		FileWriter write = new FileWriter(fileName+".txt", false);
		PrintWriter printLine = new PrintWriter(write);
		printLine.printf("%s" + "%n", message);
		printLine.close();
	}
}
