package ChessPieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import ChessPieces.Piece.pieces;

public class Piece {

	public enum sides {White, Black};
	public enum pieces {Pawn, Bishop, Rook, Queen, King, Khight};

	public sides side;
	public pieces piece;
	public int x, y, width, height; 
	public ChessBoard chess;
	public Piece[][] board; 
	public Rectangle bound;//to use for clicking 
	public boolean selected; //to predict movements
	public boolean check; 
	public ArrayList<Point> possibleMoves;

	public Piece(ChessBoard chess, int x, int y, sides side, pieces piece) {
		this.x = x;
		this.y = y;
		this.width = chess.width/8;
		this.height = chess.height/8;
		this.chess = chess;
		this.board = chess.board;
		this.side = side;
		this.piece = piece;
		bound = new Rectangle(x*width, y*height, width, height);
		selected = false;
		check = false;
	}
	public void move(int x, int y)
	{
		//make past position null
		board[this.x][this.y] = null;
		//move x and y coordinates
		this.x = x;
		this.y = y;
		//to move graphical
		this.bound.x = x * width;
		this.bound.y = y * height;
		this.selected = false;
		chess.WhiteTurn = !chess.WhiteTurn;

		//move or eat in the board
		if(board[x][y] != null) {
			board[x][y] = null; //eat a piece
		}
		board[x][y] = this;

		//Verify check almost works
		Piece check = checkTest();
		if(check != null) {
			check.check=true;
			if((check.side.equals(sides.White) && chess.WhiteTurn )|| (check.side.equals(sides.Black) && !chess.WhiteTurn)) {
				System.out.println("Cannot make this move");
			}
		}

	}
	public int xPos()
	{
		return this.x;
	}
	public int yPos() {
		return this.y;
	}
	public void tick() {
		if((chess.WhiteTurn && side == sides.White) || (!chess.WhiteTurn && side == sides.Black)) {
			if(chess.game.mouseManager.rectPressed(bound)) {
				select();
				possibleMoves = possibleMoves();
				possibleMoves = checkRemoval(possibleMoves); 
				System.out.println("ticking");
			}
			if(selected) {
				for(Point p: possibleMoves) { //Could be more efficient saving possibleMoves
					Rectangle moves = new Rectangle(p.x * width, p.y * height, width, height);
					if(chess.game.mouseManager.rectPressed(moves)) {
						move(p.x, p.y);
						selected = false;
					}
				}
			}
		}
	}
	public void render(Graphics g) {
		if(selected) {
			g.setColor(new Color(10,100,50,140));
			for(Point p: possibleMoves) {
				g.fillRect(p.x * width, p.y * height, width, height);
			}
		}
		if(check) {
			g.setColor(new Color(200,0,0,140));
			g.fillRect(x*width, y*height, width, height);
		}
	}
	private void select() {
		//deselect all Pieces and then select this one
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if(board[x][y] != null) {
					board[x][y].deselect();
				}
			}
		}
		this.selected = true;
	}
	public void deselect() {
		this.selected = false;
	}
	public ArrayList<Point> possibleMoves(){
		return new ArrayList<Point>(); //return empty list
	}
	private Piece checkTest() {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if(board[x][y] != null) {
					for(Point p: board[x][y].possibleMoves()) {
						if(board[p.x][p.y] != null && board[p.x][p.y].piece.equals(pieces.King)) {
							return board[x][y];
						}
					}
				}
			}
		}
		return null;
	}
	public ArrayList<Point> checkRemoval(ArrayList<Point> moves){
		//this method remove all moves that result in a check on their side
		ArrayList<Point> newmoves= (ArrayList<Point>) moves.clone(); //this produce lots of trash in memory
		//it will virtually move to all possible position 
		for(Point p: moves) {
			Piece temp = board[p.x][p.y]; //safe Piece in the position that will be move
			board[this.x][this.y] = null;
			board[p.x][p.y] = this;
			Piece check = checkTest(); //check if there is any check by doing this move
			if(check != null && !check.side.equals(this.side)) {
				newmoves.remove(p);//remove this position if it produce self check
			}	
			//return board how it was
			board[this.x][this.y] = this;
			board[p.x][p.y] = temp;
		}
		return newmoves;
	}

}
