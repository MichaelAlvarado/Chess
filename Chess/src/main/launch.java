package main;

import ChessPieces.ChessBoard;
import Display.DisplayScreen;

public class launch {

	public static void main(String[] args) {
//		//For Testing
//		ChessBoard chess = new ChessBoard();
//		chess.printBoard();
//		chess.moveTest(3, 2, 3, 4);
//		System.out.println();
//		chess.printBoard();
//		DisplayScreen display = new DisplayScreen("Chess", 1000, 1000);
		GameEngine game = new GameEngine("Chess");
		game.start();
	}

}
