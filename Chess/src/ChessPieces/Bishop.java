package ChessPieces;

import java.awt.Graphics;
import java.awt.Rectangle;

import ChessPieces.Piece.side;
import Input.Images;

public class Bishop implements Piece{
	
	private int x, y;//Positions to print
	private ChessBoard board;
	private side color;
	private boolean selected;

	public Bishop(ChessBoard board, int x, int y, side color) {
		this.x = x;
		this.y = y;
		this.board = board;
		this.color = color;
		selected = false;
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
			g.drawImage(Images.WBishop, this.x*(board.width/8), this.y*(board.height/8), (board.width/8), (board.height/8), null);
		}
		else {
			g.drawImage(Images.BBishop, this.x*(board.width/8), this.y*(board.height/8), (board.width/8), (board.height/8), null);
		}
	}
	private void onClick() {
		//if click tells posible position to move it and make it posible to move it to another position
	}

	@Override
	public void deselect() {
		this.selected = false;
	}
	
}