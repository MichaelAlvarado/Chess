package ChessPieces;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import Input.Images;

public class Queen extends Piece {

	public Queen(ChessBoard chess, int x, int y, sides side) {
		super(chess, x, y, side, pieces.Queen);
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		if(this.side == sides.White) {
			g.drawImage(Images.WQueen, this.x*(width), this.y*(height), (width), (height), null);
		}
		else {
			g.drawImage(Images.BQueen, this.x*(width), this.y*(height), (width), (height), null);
		}
	}
	@Override
	public ArrayList<Point> possibleMoves(){
		ArrayList<Point> moves = new ArrayList<Point>();
		//Bishops movement
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
		//Rook Movement
		for(x = this.x+1; x < 8; x++) {
			if(board[x][this.y] != null) {
				if(!board[x][this.y].side.equals(this.side)) {
					moves.add(new Point(x, this.y));//can eat piece
				}
				break;
			}
			moves.add(new Point(x, this.y));
		}
		//left movement
		for(x = this.x-1; x >= 0; x--) {
			if(board[x][this.y] != null) {
				if(!board[x][this.y].side.equals(this.side)) {
					moves.add(new Point(x, this.y));//can eat piece
				}
				break;
			}
			moves.add(new Point(x, this.y));
		}
		//up movement
		for(y = this.y+1; y < 8; y++) {
			if(board[this.x][y] != null) {
				if(!board[this.x][y].side.equals(this.side)) {
					moves.add(new Point(this.x, y));//can eat piece
				}
				break;
			}
			moves.add(new Point(this.x, y));
		}
		//down movement
		for(y = this.y-1; y >0; y--) {
			if(board[this.x][y] != null) {
				if(!board[this.x][y].side.equals(this.side)) {
					moves.add(new Point(this.x, y));//can eat piece
				}
				break;
			}
			moves.add(new Point(this.x, y));
		}
		return moves;
	}

}