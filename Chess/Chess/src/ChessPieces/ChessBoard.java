package ChessPieces;
import java.util.ArrayList;

public class ChessBoard {
	boolean WhiteTurn;
	ArrayList<Piece> Whites; //white pieces
	ArrayList<Piece> Blacks; //black pieces 
	
	String[][] board = new String[8][8]; 


	public ChessBoard() {
		Whites = new ArrayList<Piece>();
		Blacks = new ArrayList<Piece>();
		//Pawns
		for(int x = 0; x < 8; x++){
			Whites.add(new Pawn(x, 1));//add all white pawns in row 2 column from 1 to 8
			Blacks.add(new Pawn(x, 6));//add all black pawns in row 7 column from 1 to 8
		}
		//Rooks
		Whites.add(new Rook(0,0));
		Whites.add(new Rook(7,0));
		Blacks.add(new Rook(0,7));
		Blacks.add(new Rook(7,7));
		//Knights
		Whites.add(new Knight(1,0));
		Whites.add(new Knight(6,0));
		Blacks.add(new Knight(1,7));
		Blacks.add(new Knight(6,7));
		//Bishops
		Whites.add(new Bishop(2,0));
		Whites.add(new Bishop(5,0));
		Blacks.add(new Bishop(2,7));
		Blacks.add(new Bishop(5,7));
		//Queens
		Whites.add(new Queen(3,0));
		Blacks.add(new Queen(3,7));
		//Kings
		Whites.add(new King(4,0));
		Blacks.add(new King(4,7));
		
		//Initialize board to String of WhiteSpace
		for(int y=0; y<8; y++) {
			for(int x=0; x<8; x++) {
				board[x][y] = "  ";
			}
		}
		//Put Whites in board the pieces in their order
		for(Piece W: Whites) {
			if(W.getClass().equals(Pawn.class))
				board[W.xPos()][W.yPos()] = "WP";
			else if(W.getClass().equals(Rook.class))
				board[W.xPos()][W.yPos()] = "WR";
			else if(W.getClass().equals(Knight.class))
				board[W.xPos()][W.yPos()] = "WK";
			else if(W.getClass().equals(Bishop.class))
				board[W.xPos()][W.yPos()] = "WB";
			else if(W.getClass().equals(Queen.class))
				board[W.xPos()][W.yPos()] = "WQ";
			else if(W.getClass().equals(King.class))
				board[W.xPos()][W.yPos()] = "W&";
		}
		//Put Blacks in board the pieces in their order
		for(Piece B: Blacks) {
			if(B.getClass().equals(Pawn.class))
				board[B.xPos()][B.yPos()] = "BP";
			else if(B.getClass().equals(Rook.class))
				board[B.xPos()][B.yPos()] = "BR";
			else if(B.getClass().equals(Knight.class))
				board[B.xPos()][B.yPos()] = "BK";
			else if(B.getClass().equals(Bishop.class))
				board[B.xPos()][B.yPos()] = "BB";
			else if(B.getClass().equals(Queen.class))
				board[B.xPos()][B.yPos()] = "BQ";
			else if(B.getClass().equals(King.class))
				board[B.xPos()][B.yPos()] = "B&";
		}
		


		WhiteTurn = true;//Whites always start first
	}
	public void printBoard() {
			//Print in Console the board
		System.out.println("  | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8"); //Print X coordinates
		for(int y=0; y<8; y++) {
			System.out.println("_________________________________________"); //Print horizontal lines
			System.out.print(y+1); //Print y coordinates
			for(int x=0; x<8; x++) {
				System.out.print(" | " + board[x][y]); //Print Vertical Line and the Piece 
			}
			System.out.println();
		}
	}

	public void movePiece() {
		ArrayList<Piece> turnPiece;
		if(this.WhiteTurn) 
			turnPiece = Whites;
		else
			turnPiece = Blacks;
	}
	public void moveTest(int x1, int y1, int x2, int y2) {
		board[x2-1][y2-1] = board[x1-1][y1-1];
		board[x1-1][y1-1] = "  ";
	}
}
