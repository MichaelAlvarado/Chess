package main;

import ChessPieces.ChessBoard;
import Display.DisplayScreen;

public class launch {

	public static void main(String[] args) {
		GameEngine game = new GameEngine("Chess", 1000, 1000);
		game.start();
	}

}
