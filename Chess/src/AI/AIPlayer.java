package AI;

import java.awt.Point;
import java.util.ArrayList;

import ChessPieces.Bishop;
import ChessPieces.ChessBoard;
import ChessPieces.King;
import ChessPieces.Knight;
import ChessPieces.Pawn;
import ChessPieces.Piece;
import ChessPieces.Piece.sides;
import ChessPieces.Queen;
import ChessPieces.Rook;
import GameFiles.FileManager;
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
	protected static int valueKing = 1000; //Infinity

	private static boolean gameEnded = false;
	/**
	 * Uses Minimax 
	 * @param board - current board State for the AI to play 
	 * *Note: It will play the corresponding side of the current board
	 */
	public static void move(ChessBoard board) {
		if(board.checkmate(sides.Black)||board.checkmate(sides.White)) {
			if(!gameEnded) {
			System.out.println("Checkmate");
			FileManager.generateTextFile(board.gameMoves);
			System.out.println("Game Saved");
			gameEnded = true;
			}
		}
		else {
			long startTimer = System.currentTimeMillis();
			ArrayList<Piece> pieces = board.WhiteTurn? board.whitePieces : board.blackPieces;
			Piece best_piece = pieces.get(0);
			best_piece.possibleMoves = best_piece.possibleMoves();
			best_piece.possibleMoves = best_piece.checkRemoval(best_piece.possibleMoves); // TO BE IMPROVE (Optimize
			int i = 0;
			do {
				best_piece = pieces.get(i);
				best_piece.possibleMoves = best_piece.possibleMoves();
				best_piece.possibleMoves = best_piece.checkRemoval(best_piece.possibleMoves); // TO BE IMPROVE (Optimize
				i++;
			}
			while(best_piece.possibleMoves.isEmpty());

			Point best_move = best_piece.possibleMoves.get(0);
			double best_value = -10; //Testing this should not be searching just the best move
			for(Piece piece : pieces) {
				piece.possibleMoves = piece.possibleMoves();
				piece.possibleMoves = piece.checkRemoval(piece.possibleMoves); // TO BE IMPROVE (Optimize)
				for(Point move : piece.possibleMoves) {
					double value = value(board, move) + boardValue(move) + riskValue(board, piece, move) + checkmateValue(board, piece, move);
					//				System.out.println(piece.toString() + " " + value);
					if(value == best_value && Math.random() < 0.25) {
						best_piece = piece;
						best_move = move;
						best_value = value;
					}
					else if(value > best_value) {
						best_piece = piece;
						best_move = move;
						best_value = value;
					}
				}
			}
			best_piece.move(best_move.x, best_move.y);
			System.out.println("AI Timer of Think: " + (System.currentTimeMillis()-startTimer) + " ms.  The value of the move was: " + best_value);
		}
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
		piece.virtualMove(move.x, move.y);
		ArrayList<Piece> pieces = board.WhiteTurn? board.blackPieces : board.whitePieces; //enemy pieces
		int risk = 0;
		for(Piece p: pieces) {
			p.possibleMoves = p.possibleMoves();
			p.possibleMoves = p.checkRemoval(p.possibleMoves); // TO BE IMPROVE (Optimize)
			for(Point m: p.possibleMoves) {
				risk += value(board, m);
			}
		}
		if(piece instanceof King) {
			risk += 1; //Not good to move the king around
		}
		piece.virtualMove(x, y); //return piece to its position
		board.board[move.x][move.y] = temp; //make sure the piece in move is as it was
		return -risk;
	}

	private static int checkmateValue(ChessBoard board, Piece piece, Point move) {
		Piece temp = board.board[move.x][move.y]; 
		int x = piece.x;
		int y = piece.y;
		int value = 0;
		piece.virtualMove(move.x, move.y);
		if(board.checkmate(piece.side)) {
			value = -2*valueKing; //losing game
		}
		else if(board.checkmate(piece.side.equals(sides.White)? sides.Black: sides.White)){
			value = 2*valueKing; //winning game (AI's Goal)
		}

		piece.virtualMove(x, y); //return piece to its position
		board.board[move.x][move.y] = temp; //make sure the piece in move is as it was
		return value;
	}

	//	/**
	//	 * return a new chessBoard with the piece moved
	//	 */
	//	private static ChessBoard virtualMove(ChessBoard board, Piece piece, Point move) {
	//		piece.move(move.x, move.y);
	//	}

}
