package ChessPieces;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Piece {

	public enum sides {White, Black};

	public sides side;
	public int x, y, width, height;
	public ChessBoard chess;
	public Piece[][] board; 
	public Rectangle bound;//to use for clicking 
	public boolean selected; //to predict movements

	public Piece(ChessBoard chess, int x, int y, sides side) {
		this.x = x;
		this.y = y;
		this.width = chess.width/8;
		this.height = chess.height/8;
		this.chess = chess;
		this.board = chess.board;
		this.side = side;
		bound = new Rectangle(x*width, y*height, width, height);
		selected = false;
	}
	public void move(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.bound.x = x * width;
		this.bound.y = y * height;
		this.selected = false;
		board[x][y] = this;
	}
	public int xPos()
	{
		return this.x;
	}
	public int yPos() {
		return this.y;
	}
	public void tick() {
		if(chess.game.mouseManager.rectPressed(bound)) {
			select();
			System.out.println("ticking");
		}
		if(selected) {
			for(Point p: possibleMoves()) {
				Rectangle moves = new Rectangle(p.x * width, p.y * height, width, height);
				if(chess.game.mouseManager.rectPressed(moves)) {
					move(p.x, p.y);
					selected = false;
				}
			}
		}
	}
	public void render(Graphics g) {
		
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

}
