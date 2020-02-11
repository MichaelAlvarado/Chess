package ChessPieces;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import Input.Images;

public class King extends Piece {

	private boolean moved; //this is to know whether it can castle or not
	
	public King(ChessBoard chess, int x, int y, sides side) {
		super(chess, x, y, side, pieces.King);
		moved = false;
	}
	
	@Override
	public void move(int x, int y) {
		super.move(x, y);
		moved = true; 
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		if(this.side == sides.White) {
			g.drawImage(Images.WKing, this.x*(width), this.y*(height), (width), (height), null);
		}
		else {
			g.drawImage(Images.BKing, this.x*(width), this.y*(height), (width), (height), null);
		}		
	}
	@Override
	public ArrayList<Point> possibleMoves(){
		//Still missing the castle movement and to eat
		ArrayList<Point> moves = new ArrayList<Point>();
		//Up
		int x = this.x;
		int y = this.y+1;
		if(y<8 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
			moves.add(new Point(x,y));
		
		//Down
		x = this.x;
		y = this.y-1;
		if(y>=0 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side)))) 
			moves.add(new Point(x,y));
		
		//Right
		x = this.x+1;
		y = this.y;
		if(x<8 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
			moves.add(new Point(x,y));
		
		//Left
		x = this.x-1;
		y = this.y;
		if(x>=0 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
			moves.add(new Point(x,y));
		
		//Up Right
		x = this.x+1;
		y = this.y+1;
		if(x<8 && y<8 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
			moves.add(new Point(x,y));
		
		//Up Left
		x = this.x-1;
		y = this.y+1;
		if(x>=0 && y<8 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
			moves.add(new Point(x,y));
		
		//Down Left
		x = this.x - 1;
		y = this.y - 1;
		if(x>=0 && y>=0 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
			moves.add(new Point(x,y));
		
		//Down Right
		x = this.x+1;
		y = this.y-1;
		if(x<8 && y>=0 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
			moves.add(new Point(x,y));
		if(!this.moved)
		moves.addAll(castle());
		return moves;
	}
	private ArrayList<Point> castle() {
		{
			return new ArrayList<Point>();
		}
	}
}

