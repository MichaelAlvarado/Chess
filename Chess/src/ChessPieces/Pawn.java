package ChessPieces;

import java.awt.Graphics;
import java.util.ArrayList;

import ChessPieces.Piece.side;
import Input.Images;

public class Pawn implements Piece {
	
	private int x, y;
	private ChessBoard board;
	private boolean Moved; //this is to know whether we can move the pawn twice, at their first move
	private side color;
	
	public Pawn(ChessBoard board, int x, int y, side color) {
		this.x = x;
		this.y = y;
		this.Moved = false;
		this.board = board;
		this.color = color;
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
			g.drawImage(Images.WPawn, this.x*(board.width/8), this.y*(board.height/8), (board.width/8), (board.height/8), null);
		}
		else {
			g.drawImage(Images.BPawn, this.x*(board.width/8), this.y*(board.height/8), (board.width/8), (board.height/8), null);
		}
	}
	
	
}
