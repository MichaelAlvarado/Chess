package ChessPieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import Input.Images;

public class Pawn extends Piece {

	private boolean moved; //this is to know whether we can move the pawn twice, at their first move

	public Pawn(ChessBoard chess, int x, int y, sides side) {
		super(chess,x,y,side, pieces.Pawn);
		this.moved = false;
	}

	@Override
	public void move(int x, int y) {
		super.move(x, y);
		this.moved = true;
	}
	@Override
	public void render(Graphics g) {
		super.render(g);
		if(this.side == sides.White) {
			g.drawImage(Images.WPawn, this.x*(width), this.y*(height), (width), (height), null);
		}
		else {
			g.drawImage(Images.BPawn, this.x*(width), this.y*(height), (width), (height), null);
		}
	}
	@Override
	public ArrayList<Point> possibleMoves(){
		ArrayList<Point> moves = new ArrayList<Point>();
		int direction;
		if(this.side == sides.Black){
			direction = 1;
		}
		else {
			direction = -1;
		}
		//foward movement
		if(y < 7 && y > 0) {
			if(board[x][y+direction] == null) {
				moves.add(new Point(x,y+direction));
				if(!this.moved && board[x][y+2*direction] == null) {//First movement can move to positions
					moves.add(new Point(x,y+2*direction));
				}
			}
			//eating movement
			if(x < 7 && board[x+1][y+direction] != null && !board[x+1][y+direction].side.equals(this.side)){
				moves.add(new Point(x+1, y+direction));
			}
			if(x > 0 && board[x-1][y+direction] != null && !board[x-1][y+direction].side.equals(this.side)){
				moves.add(new Point(x-1, y+direction));
			}

		}
		return moves;
	}

}
