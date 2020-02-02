package main;

import ChessPieces.ChessBoard;

public class launch {

	public static void main(String[] args) {
		//For Testing
		ChessBoard chess = new ChessBoard();
		chess.printBoard();
		chess.moveTest(3, 2, 3, 4);
		System.out.println();
		chess.printBoard();
	}

}
