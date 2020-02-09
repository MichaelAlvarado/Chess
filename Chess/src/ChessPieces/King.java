package ChessPieces;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import Input.Images;

public class King extends Piece {

	public King(ChessBoard chess, int x, int y, sides side) {
		super(chess, x, y, side);
	}

	@Override
	public void render(Graphics g) {
		if(this.side == side.White) {
			g.drawImage(Images.WKing, this.x*(width), this.y*(height), (width), (height), null);
		}
		else {
			g.drawImage(Images.BKing, this.x*(width), this.y*(height), (width), (height), null);
		}		
	}
	@Override
	public ArrayList<Point> possibleMoves(){
		return new ArrayList<Point>();
	}
}

