package Main;

import ChessPieces.ChessBoard;
import Display.DisplayScreen;
import States.GameState.Mode;

public class launch {

	public static void main(String[] args) {
		GameEngine game = new GameEngine("Chess", 1000, 1000, Mode.AIvsAI);
		game.start();
	}

}
