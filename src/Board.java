import java.util.HashSet;
import java.util.Set;

public class Board {
	public Piece board[][];
	public Set<Coordinate> white;
	public Set<Coordinate> black;
	public Coordinate kingPos[];
	
	public Board() {
		board = new Piece[8][8];
		white = new HashSet<Coordinate>();
		black = new HashSet<Coordinate>();
		
		// Black Pieces
		board[0][0] = new Rook(0, 0, 'b');
		board[0][1] = new Knight(0, 1, 'b');
		board[0][2] = new Bishop(0, 2, 'b');
		board[0][3] = new Queen(0, 3, 'b');
		board[0][4] = new King(0, 4, 'b');
		board[0][5] = new Bishop(0, 5, 'b');
		board[0][6] = new Knight(0, 6, 'b');
		board[0][7] = new Rook(0, 7, 'b');
		
		for(int i = 0; i < 8; i++)
			board[1][i] = new Pawn(1, i, 'b');
		
		// Adding pieces to the black positions array
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 8; j++)
				black.add(new Coordinate(i, j));		
		}
		
		// White Pieces
		board[7][0] = new Rook(7, 0, 'w');
		board[7][1] = new Knight(7, 1, 'w');
		board[7][2] = new Bishop(7, 2, 'w');
		board[7][3] = new Queen(7, 3, 'w');
		board[7][4] = new King(7, 4, 'w');
		board[7][5] = new Bishop(7, 5, 'w');
		board[7][6] = new Knight(7, 6, 'w');
		board[7][7] = new Rook(7, 7, 'w');
		
		for(int i = 0; i < 8; i++)
			board[6][i] = new Pawn(6, i, 'w');
		
		// Adding pieces to the white positions array
		for(int i = 6; i < 8; i++) {
			for(int j = 0; j < 8; j++)
				white.add(new Coordinate(i, j));		
		}
		
		// Empty Pieces
		for(int i = 2; i < 6; i++) {
			for(int j = 0; j < 8; j++)
				board[i][j] = null;
		}
		
		kingPos = new Coordinate[2];
		kingPos[0] = new Coordinate(0, 4); // Black King
		kingPos[1] = new Coordinate(7, 4); // White King
		
		// Testing
//		board[3][2] = new King(3, 2, 'b');
//		board[4][7] = new King(4, 7, 'w');
//		board[4][0] = new Bishop(4, 0, 'w');
//		white.add(new Coordinate(4, 0));
//		board[5][1] = new Rook(5, 1, 'w');
//		white.add(new Coordinate(5, 1));
//		board[3][6] = new Rook(3, 6, 'b');
//		black.add(new Coordinate(3, 6));

	}
	
	public Board(Object o) {
		Board old = (Board) o;
		this.black = new HashSet<Coordinate>(old.black);
		this.white = new HashSet<Coordinate>(old.white);
		kingPos = new Coordinate[2];
		this.kingPos[0] = old.kingPos[0];
		this.kingPos[1] = old.kingPos[1];
		board = new Piece[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++)
				this.board[i][j] = old.board[i][j];
		}
	}
	
	public String toString() {
		String retval = "\n    0    1    2    3    4    5    6    7\n\n";
		for(int i = 0; i < 8; i++) {
			retval += i + "  ";
			for(int j = 0; j < 8; j++) {
				if(board[i][j] == null)
					retval += " ..  ";
				else
					retval += Character.toString(board[i][j].getType()) + "(" + board[i][j].race + ") ";
			}
			retval += "\n\n";
		}
		
		return retval;
	}
}

/*
 *    Board Positions:
 *    
 *     0 1 2 3 4 5 6 7
 *   0 R H B Q K B H R     Black Pieces
 *   1 P P P P P P P P
 *   2 . . . . . . . .
 *   3 B . R . . . . .
 *   4 . . . . . . . .
 *   5 . . . . . . . .
 *   6 P P P . . . P P     White Pieces
 *   7 R H B Q K B H R
 */