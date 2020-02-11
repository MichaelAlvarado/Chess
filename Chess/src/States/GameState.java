package States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ChessPieces.ChessBoard;
import Input.Images;
import Main.GameEngine;

public class GameState implements State{
	
	private GameEngine game;
	private ChessBoard chess; //this is the game of chessboard with all pieces
	private Color background; 
	
	public GameState(GameEngine game) {
		this.game = game;
		chess = new ChessBoard(game,0,0,game.width, game.height);
		this.background = Color.lightGray;
	}

	@Override
	public void tick() {
		chess.tick();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(background);
		g.fillRect(0, 0, game.width, game.height);
		chess.render(g);		
	}

}
