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
	public void tick() {
		super.tick();
	}

	@Override
	public void move(int x, int y) {
		super.move(x, y);
		if(!moved) {
			int yC = !chess.WhiteTurn? 7:0;
			if(x==2 && y==yC) { 						//QueenSide Castle
				board[0][yC].move(3, yC); 				//move rook 
				chess.WhiteTurn = !chess.WhiteTurn; 	//Since it makes two moves it make sure turn wont be affected
			}
			if(x==6 && y==yC) { 						//KingSide Castle
				board[7][yC].move(5, yC); 				//move rook 
				chess.WhiteTurn = !chess.WhiteTurn; 	//Since it makes two moves it make sure turn wont be affected
			}
		}
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
		int y = chess.WhiteTurn? 7:0;
		ArrayList<Point> castle = new ArrayList<Point>();
		if(!this.moved) {
			/*
			 * Queen Side Castle
			 */
			if(chess.board[0][y] != null && chess.board[0][y] instanceof Rook) {
				if(!((Rook)chess.board[0][y]).moved) {
					if(chess.board[1][y] == null &&
							chess.board[2][y] == null &&
							chess.board[3][y] == null) {
						castle.add(new Point(2,y));
					}
				}
			}
			/*
			 * King Side Castle
			 */
			if(chess.board[7][y] != null && chess.board[7][y] instanceof Rook) {
				if(!((Rook)chess.board[7][y]).moved) {
					if(chess.board[5][y] == null &&
							chess.board[6][y] == null) {
						castle.add(new Point(6,y));
					}
				}
			}
		}
		return castle;
	}
}

