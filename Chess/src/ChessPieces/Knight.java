package ChessPieces;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import Input.Images;

public class Knight extends Piece {
	
	public Knight(ChessBoard chess, int x, int y, sides side) {
		super(chess, x, y, side, pieces.Khight);
	}
	

	@Override
	public void render(Graphics g) {
		super.render(g);
		if(this.side == sides.White) {
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
				//Up2 right
				int x = this.x+1;
				int y = this.y+2;
				if(x<8 && y<8 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
					moves.add(new Point(x,y));
				
				//Up2 left
				x = this.x-1;
				y = this.y+2;
				if(x>=0 && y<8 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side)))) 
					moves.add(new Point(x,y));
				
				//Right2 Up
				x = this.x+2;
				y = this.y+1;
				if(x<8 && y<8 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
					moves.add(new Point(x,y));
				
				//Right2 down
				x = this.x+2;
				y = this.y-1;
				if(x<8 && y>=0 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
					moves.add(new Point(x,y));
				
				//Left2 Up
				x = this.x-2;
				y = this.y+1;
				if(x>=0 && y<8 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
					moves.add(new Point(x,y));
				
				//Left2 Down 
				x = this.x-2;
				y = this.y-1;
				if(x>=0 && y>=0 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
					moves.add(new Point(x,y));
				
				//Down2 Right
				x = this.x+1;
				y = this.y-2;
				if(x<8 && y>=0 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
					moves.add(new Point(x,y));
				
				//Down2 Left
				x = this.x-1;
				y = this.y-2;
				if(x>=0 && y>=0 && (board[x][y] == null || (board[x][y] != null && !board[x][y].side.equals(this.side))))
					moves.add(new Point(x,y));
				
		return moves;
	}
	
}