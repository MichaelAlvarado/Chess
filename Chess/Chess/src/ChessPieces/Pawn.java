package ChessPieces;

import java.util.ArrayList;

public class Pawn implements Piece {
	
	private int x, y;
	private boolean Moved; //this is to know whether we can move the pawn twice, at their first move
	
	public Pawn(int x, int y) {
		this.x = x;
		this.y = y;
		this.Moved = false;
	}
	@Override
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int xPos() {
		return this.x;
	}
	@Override
	public int yPos() {
		return this.y;
	}
	
	
}
