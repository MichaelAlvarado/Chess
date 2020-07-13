package ChessPieces;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.desktop.SystemSleepEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import AI.AIPlayer;
import ChessPieces.Piece.pieces;
import ChessPieces.Piece.sides;
import GameFiles.FileManager;
import Input.KeyManager;
import Main.GameEngine;
import States.GameState.Mode;

public class ChessBoard {

	public boolean WhiteTurn; //To know which turn is to play
	public Piece[][] board; //Keep the position of the pieces on the board;
	public ArrayList<Piece> whitePieces;
	public ArrayList<Piece> blackPieces;
	public int x, y ,width, height; //Where the board is going to be at the Windows App
	private boolean rotation; //If whites are position on the buttom its true
	public ArrayList<String> gameMoves; //Save all the moves of the current game

	public ChessBoard(int x, int y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;
		board = new Piece[8][8];
		WhiteTurn = true;//Whites always start first
		rotation = false;
		gameMoves = new ArrayList<String>();
		whitePieces = new ArrayList<Piece>();
		blackPieces = new ArrayList<Piece>();
		//Initialize board with starting Chess Position (Index followed by chess 0,0 botom left)
		for(int i = 0; i < 8; i++) {
			board[i][1] = new Pawn(this, i, 1, sides.Black);
			blackPieces.add(board[i][1]);
			board[i][6] = new Pawn(this, i, 6, sides.White);
			whitePieces.add(board[i][6]);
		}
		board[0][7] = new Rook(this, 0, 7, sides.White);
		board[7][7] = new Rook(this, 7, 7, sides.White);
		whitePieces.add(board[0][7]);
		whitePieces.add(board[7][7]);

		board[0][0] = new Rook(this, 0, 0, sides.Black);
		board[7][0] = new Rook(this, 7, 0, sides.Black);
		blackPieces.add(board[0][0]);
		blackPieces.add(board[7][0]);

		board[1][7] = new Knight(this, 1, 7, sides.White);
		board[6][7] = new Knight(this, 6, 7, sides.White);
		whitePieces.add(board[0][7]);
		whitePieces.add(board[6][7]);

		board[1][0] = new Knight(this, 1, 0, sides.Black);
		board[6][0] = new Knight(this, 6, 0, sides.Black);
		blackPieces.add(board[1][0]);
		blackPieces.add(board[6][0]);

		board[2][7] = new Bishop(this, 2, 7, sides.White);
		board[5][7] = new Bishop(this, 5, 7, sides.White);
		whitePieces.add(board[2][7]);
		whitePieces.add(board[5][7]);

		board[2][0] = new Bishop(this, 2, 0, sides.Black);
		board[5][0] = new Bishop(this, 5, 0, sides.Black);
		blackPieces.add(board[2][0]);
		blackPieces.add(board[5][0]);

		board[3][7] = new Queen(this, 3, 7, sides.White);
		whitePieces.add(board[3][7]);
		board[3][0] = new Queen(this, 3, 0, sides.Black);
		blackPieces.add(board[3][0]);

		board[4][7] = new King(this, 4, 7, sides.White);
		whitePieces.add(board[4][7]);
		board[4][0] = new King(this, 4, 0, sides.Black);
		blackPieces.add(board[4][0]);
	}

	public void tick() {
		if(KeyManager.keyJustPressed(KeyEvent.VK_ESCAPE)) {
			FileManager.generateTextFile(gameMoves);
			System.out.println("Game Saved");
		}
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
		for(int x = 0; x < 8; x++) {
			String[] coor = {"A","B","C","D","E","F","G","H"};
			g.drawString(coor[x], x*(width/8), height);
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
	
	/**
	 * 
	 * @return side which is lost the game due checkmate. returns null if there is no checkmate.
	 */
	public boolean checkmate(sides side) {
		ArrayList<Piece> pieces = side.equals(sides.White)? whitePieces: blackPieces;
		for(Piece piece: pieces) {
			piece.possibleMoves = piece.checkRemoval(piece.possibleMoves());
			if(!piece.possibleMoves.isEmpty()) {
			//	System.out.println("Checkmate blacks loses");
				return false;
			}
		}
		System.out.println("Checkmate " + side.toString() + " loses");
		return true;
	}
	
	private void rotateBoard(){
		this.rotation = !this.rotation; //Not working because render does not use rotation yet
	}
	
	public void moveSave(Piece piece, Point position1, Point position2) {
		String move = piece.toString() + ", (" + position1.x + " , " + position1.y + ") , ("
				+ position2.x + " , " +  position2.y + ") , " + piece.side.toString();
		
		gameMoves.add(move);
	}
	
	
}
