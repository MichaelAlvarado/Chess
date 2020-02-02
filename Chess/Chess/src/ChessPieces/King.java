package ChessPieces;

public class King implements Piece {
	
	private int x, y;
	
	public King(int x, int y) {
		this.x = x;
		this.y = y;
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
