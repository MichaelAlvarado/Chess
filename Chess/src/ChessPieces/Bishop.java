package ChessPieces;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import Input.Images;

public class Bishop extends Piece{

	public Bishop(ChessBoard chess, int x, int y, sides side) {
		super(chess, x, y, side);
	}

	@Override
	public void render(Graphics g) {
		if(this.side == sides.White) {
			g.drawImage(Images.WBishop, this.x*(width), this.y*(height), (width), (height), null);
		}
		else {
			g.drawImage(Images.BBishop, this.x*(width), this.y*(height), (width), (height), null);
		}
	}
	@Override
	public ArrayList<Point> possibleMoves(){
		return new ArrayList<Point>(); //Gotta be worked
	}

}