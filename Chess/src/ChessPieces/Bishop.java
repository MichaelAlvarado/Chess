package ChessPieces;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import Input.Images;

public class Bishop extends Piece{

	public Bishop(ChessBoard chess, int x, int y, sides side) {
		super(chess, x, y, side, pieces.Bishop);
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		if(this.side == sides.White) {
			g.drawImage(Images.WBishop, this.x*(width), this.y*(height), (width), (height), null);
		}
		else {
			g.drawImage(Images.BBishop, this.x*(width), this.y*(height), (width), (height), null);
		}
	}
	@Override
	public ArrayList<Point> possibleMoves(){
		ArrayList<Point> moves = new ArrayList<Point>();
		int x = this.x+1;
		int y = this.y+1;
		//Up Right
		while(x<8 && y<8) {
			if(board[x][y] != null) {
				if(!board[x][y].side.equals(this.side)) {
					moves.add(new Point(x,y));//can eat
				}
				break;
			}
			moves.add(new Point(x,y));
			x++;
			y++;
		}
		//Up Left
		x = this.x-1;
		y = this.y+1;
		while(x>=0 && y<8) {
			if(board[x][y] != null) {
				if(!board[x][y].side.equals(this.side)) {
					moves.add(new Point(x,y));//can eat
				}
				break;
			}
			moves.add(new Point(x,y));
			x--;
			y++;
		}
		//Down right
		x = this.x+1;
		y = this.y-1;
		while(x<8 && y>=0) {
			if(board[x][y] != null) {
				if(!board[x][y].side.equals(this.side)) {
					moves.add(new Point(x,y));//can eat
				}
				break;
			}
			moves.add(new Point(x,y));
			x++;
			y--;
		}	
		//Down Left
		x = this.x-1;
		y = this.y-1;
		while(x>=0 && y>=0) {
			if(board[x][y] != null) {
				if(!board[x][y].side.equals(this.side)) {
					moves.add(new Point(x,y));//can eat
				}
				break;
			}
			moves.add(new Point(x,y));
			x--;
			y--;
		}
		return moves;
	}

}