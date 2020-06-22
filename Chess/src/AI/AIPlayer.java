package AI;

import java.awt.Point;
import java.util.ArrayList;

import ChessPieces.Bishop;
import ChessPieces.ChessBoard;
import ChessPieces.King;
import ChessPieces.Knight;
import ChessPieces.Pawn;
import ChessPieces.Piece;
import ChessPieces.Queen;
import ChessPieces.Rook;

/**
 * Michael J. Alvarado
 * Date - Jun/18/2020
 */
public class AIPlayer {

	/**
	 * Uses Minimax 
	 * @param board - current board State for the AI to play 
	 * *Note: It will play the corresponding side of the current board
	 */
	public static void move(ChessBoard board) {
		ArrayList<Piece> pieces = board.WhiteTurn? board.whitePieces : board.blackPieces;
		Piece piece_move = pieces.get(0);
		Point move_move = new Point();
		int bestMove = 0; //Testing this should not be searching just the best move
		for(Piece piece : pieces) {
			piece.possibleMoves = piece.possibleMoves();
			piece.possibleMoves = piece.checkRemoval(piece.possibleMoves); 
			int i = 0;
			for(Point move : piece.possibleMoves) {
				int value = value(board, move);
				System.out.println(piece.toString() + " " + value);
				if(value == bestMove && Math.random() < 0.1) {
					piece_move = piece;
					move_move = move;
					bestMove = value;
				}
				else if(value > bestMove) {
					piece_move = piece;
					move_move = move;
					bestMove = value;
				}
				i++;
			}
		}
		piece_move.move(move_move.x, move_move.y);
	}

	/**
	 * Gets the values of a chess piece and return its best move with that piece.
	 * Note: this will not take into account that what seems to be a bad moves might be a good one in future moves (Predictibility) 
	 * @return
	 */
	private static int value(ChessBoard board, Point move) {	
		Piece piece = board.board[move.x][move.y];
		if(piece != null) {
			if(piece instanceof Pawn) {
				return 1;
			}
			else if(piece instanceof Knight) {
				return 3;
			}
			else if(piece instanceof Bishop) {
				return 3;
			}
			else if(piece instanceof Rook) {
				return 5;
			}
			else if(piece instanceof Queen) {
				return 9;
			}
			else if(piece instanceof King) {
				return 100000; //Very high value to represent infinity
			}
			else {
				System.out.println("AI.Error Unkown Piece 67ln");
			}
		}
		return 0;
	}


}
