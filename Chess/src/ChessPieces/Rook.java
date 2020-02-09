package ChessPieces;

import java.awt.Graphics;
import Input.Images;

public class Rook extends Piece {
	
	public Rook(ChessBoard chess, int x, int y, sides side) {
		super(chess, x, y, side);
	}

	@Override
	public void render(Graphics g) {
		if(this.side == side.White) {
			g.drawImage(Images.WRook, this.x*(width), this.y*(height), (width), (height), null);
		}
		else {
			g.drawImage(Images.BRook, this.x*(width), this.y*(height), (width), (height), null);
		}
	}
	
}