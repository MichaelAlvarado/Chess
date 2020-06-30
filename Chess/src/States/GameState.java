package States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import AI.AIPlayer;
import ChessPieces.ChessBoard;
import Input.Images;
import Main.GameEngine;

public class GameState implements State{

	public enum Mode{AIvsP, PvsAI, AIvsAI, PvsP}; //(White first) AIvsPlayer AI uses Whites
	public ChessBoard chess; //this is the game of chessboard with all pieces

	private GameEngine game;
	private Color background; 

	public GameState(GameEngine game, Mode mode) {
		this.game = game;
		this.background = Color.lightGray;
		chess = initializeChessboard(mode);
	}

	private ChessBoard initializeChessboard(Mode mode) {
		chess = new ChessBoard(0,0,game.width, game.height) {
			@Override
			public void tick() {
				switch (mode) {
				
				case AIvsP:
					if(WhiteTurn) {
						AIPlayer.move(this);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					break;
					
				case PvsAI:
					if(!WhiteTurn) {
						AIPlayer.move(this);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					break;
					
				case AIvsAI:
					AIPlayer.move(this);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
					
				case PvsP:
					break;
				}
				super.tick();
			}
		};
		return chess;
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
