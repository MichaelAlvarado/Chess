package ChessPieces;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ChessPieces.Piece.pieces;
import ChessPieces.Piece.sides;
import Main.GameEngine;

public class ChessBoard {
	
	public boolean WhiteTurn; //To know which turn is to play
	public GameEngine game;
	public Piece[][] board; //Keep the position of the pieces on the board;
	public int x, y ,width, height; //Where the board is going to be at the Windows App
	private boolean rotation; //If whites are position on the buttom its true

	public ChessBoard(GameEngine game, int x, int y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;
		this.game = game;
		board = new Piece[8][8];
		WhiteTurn = true;//Whites always start first
		this.rotation = false;
		//Initialize board with starting Chess Position (Index followed by chess 0,0 botom left)
		for(int i = 0; i < 8; i++) {
			board[i][1] = new Pawn(this, i, 1, sides.Black);
			board[i][6] = new Pawn(this, i, 6, sides.White);
		}
		board[0][7] = new Rook(this, 0, 7, sides.White);
		board[7][7] = new Rook(this, 7, 7, sides.White);
		board[0][0] = new Rook(this, 0, 0, sides.Black);
		board[7][0] = new Rook(this, 7, 0, sides.Black);

		board[1][7] = new Knight(this, 1, 7, sides.White);
		board[6][7] = new Knight(this, 6, 7, sides.White);
		board[1][0] = new Knight(this, 1, 0, sides.Black);
		board[6][0] = new Knight(this, 6, 0, sides.Black);

		board[2][7] = new Bishop(this, 2, 7, sides.White);
		board[5][7] = new Bishop(this, 5, 7, sides.White);
		board[2][0] = new Bishop(this, 2, 0, sides.Black);
		board[5][0] = new Bishop(this, 5, 0, sides.Black);

		board[3][7] = new Queen(this, 3, 7, sides.White);
		board[3][0] = new Queen(this, 3, 0, sides.Black);

		board[4][7] = new King(this, 4, 7, sides.White);
		board[4][0] = new King(this, 4, 0, sides.Black);
		
	}

	public void tick() {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if(board[x][y] != null) {
					board[x][y].tick();
				}
			}
		}
	}

	public void render(Graphics g) {
		renderBoard(g);
		renderPieces(g);
	} 

	private void renderBoard (Graphics g) {
		//Render Tiles
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if((x+y) % 2 != 0)
					g.setColor(Color.DARK_GRAY);
				else 
					g.setColor(Color.WHITE);

				g.fillRect(x*(width/8), y*(height/8), width/8, height/8);
			}
		}
		//Render Coordinates
		int fontSize = 20;
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		g.setColor(Color.BLACK);
		for(int y = 0; y < 8; y++) {
			g.drawString(String.valueOf(8-y), 0, y*(height/8)+fontSize);
		}
		for(int x = 1; x < 8; x++) {
			String[] coor = {"B","C","D","E","F","G","H"};
			g.drawString(coor[x-1], x*(width/8), (7*height/8)+fontSize);
		}
	}
	private void renderPieces(Graphics g) {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if(board[x][y] != null) {
					board[x][y].render(g);
				}
			}
		}
	}
	private void rotateBoard(){
		this.rotation = !this.rotation; //Not working because render does not use rotation yet
	}
}
