package ChessPieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import Input.Images;

public class Pawn extends Piece {

	private boolean Moved; //this is to know whether we can move the pawn twice, at their first move

	public Pawn(ChessBoard board, int x, int y, sides side) {
		super(board,x,y,side);
		this.Moved = false;
	}

	@Override
	public void move(int x, int y) {
		super.move(x, y);;
		this.Moved = true;
	}
	@Override
	public void render(Graphics g) {
		super.render(g);
		if(selected) {
			g.setColor(new Color(10,100,50,140));
			for(Point p: possibleMoves()) {
				g.fillRect(p.x * width, p.y * height, width, height);
			}
		}
		if(this.side == side.White) {
			g.drawImage(Images.WPawn, this.x*(width), this.y*(height), (width), (height), null);
		}
		else {
			g.drawImage(Images.BPawn, this.x*(width), this.y*(height), (width), (height), null);
		}
	}
	@Override
	public ArrayList<Point> possibleMoves(){
		Piece[][] Board = board;
		ArrayList<Point> moves = new ArrayList();
		int direction;
		if(this.side == side.Black){
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

}
