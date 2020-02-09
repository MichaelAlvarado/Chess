package ChessPieces;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import Input.Images;

public class Queen extends Piece {
	
	public Queen(ChessBoard chess, int x, int y, sides side) {
		super(chess, x, y, side);
	}
	
	@Override
	public void render(Graphics g) {
		if(this.side == side.White) {
			g.drawImage(Images.WQueen, this.x*(width), this.y*(height), (width), (height), null);
		}
		else {
			g.drawImage(Images.BQueen, this.x*(width), this.y*(height), (width), (height), null);
		}
	}
	@Override
	public ArrayList<Point> possibleMoves(){
		return new ArrayList<Point>();
	}

}