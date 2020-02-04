package ChessPieces;

public class King implements Piece {
	
	private int x, y;
	private ChessBoard board;

	public King(ChessBoard board, int x, int y) {
		this.x = x;
		this.y = y;
		this.board = board;
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
