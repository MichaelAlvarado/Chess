package ChessPieces;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import Input.Images;

public class Knight extends Piece {
	
	public Knight(ChessBoard chess, int x, int y, sides side) {
		super(chess, x, y, side);
	}
	

	@Override
	public void render(Graphics g) {
		if(this.side == side.White) {
			g.drawImage(Images.WKnight, this.x*(width), this.y*(height), (width), (height), null);
		}
		else {
			g.drawImage(Images.BKnight, this.x*(width), this.y*(height), (width), (height), null);
		}		
	}
	@Override
	public ArrayList<Point> possibleMoves(){
		ArrayList<Point> moves = new ArrayList<Point>();
		//There are 8 possible moves
		return moves;
	}
	
}