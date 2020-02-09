package ChessPieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import ChessPieces.Piece.side;
import Input.Images;

public class Pawn implements Piece {

	private int x, y;
	private int width, height;
	Point coordinates;
	private ChessBoard board;
	private boolean Moved; //this is to know whether we can move the pawn twice, at their first move
	private side color;
	private Rectangle bound;
	private boolean selected;//to know if this Piece was selected to predict moves

	public Pawn(ChessBoard board, int x, int y, side color) {
		this.x = x;
		this.y = y;
		this.width = board.width/8;
		this.height = board.height/8;
		coordinates = new Point(x,y);
		this.Moved = false;
		this.board = board;
		this.color = color;
		bound = new Rectangle(x*width, y*height, this.width, this.height);
	}
	@Override
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
		this.Moved = true;
		this.bound.x = x * width;
		this.bound.y = y * height;
		this.selected = false;
		board.board[x][y] = this;
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
		if(selected) {
			g.setColor(new Color(10,100,50,140));
			for(Point p: possibleMoves()) {
				g.fillRect(p.x * width, p.y * height, width, height);
			}
		}
		if(this.color == side.White) {
			g.drawImage(Images.WPawn, this.x*(board.width/8), this.y*(board.height/8), (board.width/8), (board.height/8), null);
		}
		else {
			g.drawImage(Images.BPawn, this.x*(board.width/8), this.y*(board.height/8), (board.width/8), (board.height/8), null);
		}
	}
	private ArrayList<Point> possibleMoves(){
		Piece[][] Board = board.board;
		ArrayList<Point> moves = new ArrayList();
		int direction;
		if(this.color == side.Black){
			direction = 1;
		}
		else {
			direction = -1;
		}
		if(Board[x][y+direction] == null) {
			moves.add(new Point(x,y+direction));
			if(!this.Moved && Board[x][y+2*direction] == null) {
				moves.add(new Point(x,y+2*direction));
			}
		}
		return moves;
	}
	private void select() {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if(board.board[x][y] != null) {
					board.board[x][y].deselect();
			}
		}
	}
		this.selected = true;
}
	@Override
	public void deselect() {
		this.selected = false;
	}



}
