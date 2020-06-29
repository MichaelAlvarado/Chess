package ChessPieces;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import Input.Images;

public class Rook extends Piece {
	
	public boolean moved; //this is to know whether it can castle or not
	
	public Rook(ChessBoard chess, int x, int y, sides side) {
		super(chess, x, y, side, pieces.Rook);
		moved = false;
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		if(this.side == sides.White) {
			g.drawImage(Images.WRook, this.x*(width), this.y*(height), (width), (height), null);
		}
		else {
			g.drawImage(Images.BRook, this.x*(width), this.y*(height), (width), (height), null);
		}
	}
	
	@Override
	public void move(int x, int y) {
		super.move(x, y);
		moved = true; 
	}
	
	@Override
	public ArrayList<Point> possibleMoves(){
		ArrayList<Point> moves = new ArrayList<Point>();
	    //right movement
		for(int x = this.x+1; x < 8; x++) {
			if(board[x][this.y] != null) {
				if(!board[x][this.y].side.equals(this.side)) {
					moves.add(new Point(x, this.y));//can eat piece
				}
				break;
			}
			moves.add(new Point(x, this.y));
		}
		//left movement
		for(int x = this.x-1; x >= 0; x--) {
			if(board[x][this.y] != null) {
				if(!board[x][this.y].side.equals(this.side)) {
					moves.add(new Point(x, this.y));//can eat piece
				}
				break;
			}
			moves.add(new Point(x, this.y));
		}
		//up movement
		for(int y = this.y+1; y < 8; y++) {
			if(board[this.x][y] != null) {
				if(!board[this.x][y].side.equals(this.side)) {
					moves.add(new Point(this.x, y));//can eat piece
				}
				break;
			}
			moves.add(new Point(this.x, y));
		}
		//down movement
		for(int y = this.y-1; y >0; y--) {
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