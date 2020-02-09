package ChessPieces;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import ChessPieces.Piece.side;

public class Pieces {

	enum side {White, Black};

	private side side;
	private int x, y, width, height;
	private ChessBoard chess;
	private Piece[][] board;
	private Rectangle bound;//to use for clicking 
	private boolean selected;

	public Pieces(ChessBoard chess, int x, int y, side side ) {
		this.x = x;
		this.y = y;
		this.width = chess.width;
		this.height = chess.height;
		this.chess = chess;
		this.board = chess.board;
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
	private ArrayList<Point> possibleMoves(){
		return new ArrayList<Point>(); //return empty list
	}

}
