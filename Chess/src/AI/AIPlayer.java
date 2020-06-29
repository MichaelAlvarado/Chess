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
import Graph.Graph;

/**
 * Michael J. Alvarado
 * Date - Jun/18/2020
 */
public class AIPlayer {
	
	protected static int valuePawn = 1;
	protected static int valueKnight = 3;
	protected static int valueBishop = 3;
	protected static int valueRook = 5;
	protected static int valueQueen = 9;
	protected static int valueKing = 100000; //Infinity


	/**
	 * Uses Minimax 
	 * @param board - current board State for the AI to play 
	 * *Note: It will play the corresponding side of the current board
	 */
	public static void move(ChessBoard board) {
		long startTimer = System.currentTimeMillis();
		ArrayList<Piece> pieces = board.WhiteTurn? board.whitePieces : board.blackPieces;
		Piece piece_move = pieces.get(0);
		Point move_move = new Point();
		double bestMove = -10; //Testing this should not be searching just the best move
		for(Piece piece : pieces) {
			piece.possibleMoves = piece.possibleMoves();
			piece.possibleMoves = piece.checkRemoval(piece.possibleMoves); // TO BE IMPROVE (Optimize)
			for(Point move : piece.possibleMoves) {
				double value = value(board, move) + boardValue(move) + riskValue(board, piece, move);
//				System.out.println(piece.toString() + " " + value);
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
			}
		}
		piece_move.move(move_move.x, move_move.y);
		System.out.println("AI Timer of Think: " + (System.currentTimeMillis()-startTimer) + " ms.  The value of the move was: " + bestMove);
	}

	/**
	 * Gets the values of a chess piece and return its best move with that piece.
	 * Note: this will not take into account that what seems to be a bad moves might be a good one in future moves (Predictibility) 
	 */
	private static int value(ChessBoard board, Point move) {	
		Piece piece = board.board[move.x][move.y];
		if(piece != null) {
			if(piece instanceof Pawn) {
				return valuePawn;
			}
			else if(piece instanceof Knight) {
				return valueKnight;
			}
			else if(piece instanceof Bishop) {
				return valueBishop;
			}
			else if(piece instanceof Rook) {
				return valueRook;
			}
			else if(piece instanceof Queen) {
				return valueQueen;
			}
			else if(piece instanceof King) {
				return valueKing;
			}
			else {
				System.out.println("AI.Error Unkown Piece");
			}
		}
		return 0;
	}
	
	/**
	 * gives value to the center positions (Using the idea of dominating the center of the board)
	 */
	private static double boardValue(Point move) {
		if((move.x == 3 || move.x == 4) && (move.y == 3 || move.y == 4)) {
			return 0.5;
		}
		return 0;
	}
	
	/**
	 * Virtually move the piece to the move position and verified whats at risk to return its corresponding value
	 * Check the risk of having a piece eaten
	 */
	private static int riskValue(ChessBoard board, Piece piece, Point move) {
		Piece temp = board.board[move.x][move.y]; 
		int x = piece.x;
		int y = piece.y;
		piece.move(move.x, move.y);
		ArrayList<Piece> pieces = !board.WhiteTurn? board.blackPieces : board.whitePieces;
		int risk = 0;
		for(Piece p: pieces) {
			p.possibleMoves = p.possibleMoves();
			p.possibleMoves = p.checkRemoval(p.possibleMoves); // TO BE IMPROVE (Optimize)
			for(Point m: p.possibleMoves) {
				risk += value(board, m);
			}
		}
		piece.move(x, y); //return piece to its position
		board.board[move.x][move.y] = temp; //make sure the piece in move is as it was
		return -risk;
	}
	
//	/**
//	 * return a new chessBoard with the piece moved
//	 */
//	private static ChessBoard virtualMove(ChessBoard board, Piece piece, Point move) {
//		piece.move(move.x, move.y);
//	}

}
