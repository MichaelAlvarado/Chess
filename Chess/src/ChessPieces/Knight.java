package ChessPieces;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import ChessPieces.Piece.side;
import Input.Images;

public class Knight implements Piece {
	
	private int x, y;
	private int width , height;
	private ChessBoard board;
	private side color;
	private boolean selected;
	private Rectangle bound;

	public Knight(ChessBoard board, int x, int y, side color) {
		this.x = x;
		this.y = y;
		this.width = board.width/8;
		this.height = board.height/8;
		this.board = board;
		this.color = color;
		this.selected = false;
		bound = new Rectangle(x*width, y*height, this.width, this.height);
	}
	
	@Override
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
		this.bound.x = x * width;
		this.board.y = y * height;
	}
	@Override
	public int xPos() {
		return this.x;
	}
	@Override
	public int yPos() {
		return this.y;
	}
	@Override
	public void tick() {
		if(board.game.mouseManager.rectPressed(bound)) {
			select();
			System.out.println("ticking");
		}
		if(selected) {
			for(Point p: possibleMoves()) {
				Rectangle moves = new Rectangle(p.x * width, p.y * height, width, height);
				if(board.game.mouseManager.rectPressed(moves)) {
					move(p.x, p.y);
					selected = false;
				}
			}
		}
	}
	@Override
	public void render(Graphics g) {
		if(this.color == side.White) {
			g.drawImage(Images.WKnight, this.x*(board.width/8), this.y*(board.height/8), (board.width/8), (board.height/8), null);
		}
		else {
			g.drawImage(Images.BKnight, this.x*(board.width/8), this.y*(board.height/8), (board.width/8), (board.height/8), null);
		}		
	}

	@Override
	public void deselect() {
		this.selected = false;
	}
	private void select() {
		this.selected = true;
	}
	private ArrayList<Point> possibleMoves(){
		ArrayList<Point> moves = new ArrayList<Point>();
		//There are 8 possible moves
		
		return moves;
	}
	
}