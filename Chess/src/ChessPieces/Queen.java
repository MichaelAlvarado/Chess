package ChessPieces;

import java.awt.Graphics;

import ChessPieces.Piece.side;
import Input.Images;

public class Queen implements Piece {
	
	private int x, y;
	private ChessBoard board;
	private side color;
	private boolean selected;
	
	public Queen(ChessBoard board, int x, int y, side color) {
		this.x = x;
		this.y = y;
		this.board = board;
		this.color = color;
		this.selected = false;
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

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		if(this.color == side.White) {
			g.drawImage(Images.WQueen, this.x*(board.width/8), this.y*(board.height/8), (board.width/8), (board.height/8), null);
		}
		else {
			g.drawImage(Images.BQueen, this.x*(board.width/8), this.y*(board.height/8), (board.width/8), (board.height/8), null);
		}
		
	}

	@Override
	public void deselect() {
		this.selected = false;
	}
	
}