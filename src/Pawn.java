import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Pawn extends Piece{
	
	public Pawn() {
		super();
	}
	
	public Pawn(int x, int y, char race) {
		super(x, y, race, 'P'); // x and y coordinates, the race and the type
	}
	
	// TODO: We need to check the edgecases
	// TODO: (Low priority) Pawn to Queen conversion
	public Set<Coordinate> displayMoves(Board b) {
		Set<Coordinate> possible = new HashSet<Coordinate>();
		int sign = race == 'b' ? 1 : -1;
		
		// This is to check for KILLABLE MOVES
		if((x+sign >= 0) && (x+sign < 8) && (y-1 >= 0) && (y-1 < 8)) {
			if(b.board[x + sign][y-1] != null) {
				if(b.board[x + sign][y-1].race != race)
					possible.add(new Coordinate(x + sign, y - 1));
			}
		}
		
		if((x+sign >= 0) && (x+sign < 8) && (y+1 >= 0) && (y+1 < 8)) {
			if(b.board[x + sign][y+1] != null) {
				if(b.board[x + sign][y+1].race != race)
					possible.add(new Coordinate(x + sign, y + 1));
			}
		}
		
		// This is to check for MOVABLE MOVES
		
		if((x+sign >= 0) && (x+sign < 8)) {
			if(b.board[x + sign][y] == null) {
				possible.add(new Coordinate(x + sign, y));
			}
			else // This means there is a piece in front
				return possible;
		}
		
		// If first move
		if(move == 0) {
			if(b.board[x + 2*sign][y] == null)
				possible.add(new Coordinate(x + 2*sign, y));
			// TODO: Remove the next line and add when we move the piece
			//move++;
		}
		
		
		return possible;
	}
	
	public Set<Coordinate> killableMoves(Board b){
		Set<Coordinate> possible = new HashSet<Coordinate>();
		int sign = race == 'b' ? 1 : -1;
		
		if((x+sign >= 0) && (x+sign < 8) && (y-1 >= 0) && (y-1 < 8)) {
			possible.add(new Coordinate(x + sign, y - 1));
		}
		
		if((x+sign >= 0) && (x+sign < 8) && (y+1 >= 0) && (y+1 < 8)) {
			possible.add(new Coordinate(x + sign, y + 1));
		}
		
		return possible;
	}
	
	public boolean checkConversion() {
		switch(race) {
		case 'b': if(x == 7)
			return true;
		case 'w': if(x == 0)
			return true;
		}
		return false;
	}
	
	public void convertIfPossible(Board b) {
		if(checkConversion()) {
			int  q = 0;
			System.out.println("Conversion is possible for your shitty pawn. Do you want to give his life purpose? Yes you do. There's no point if you don't");
			while(q == 0){
				System.out.println("Enter one of the following to change to: (1)Rook (2)Bishop (3)Knight (4)Queen.");
				int choice;
				Scanner sc = new Scanner(System.in);
				choice = sc.nextInt();
				q++;
				switch(choice) {
				case 1: b.board[x][y] = new Rook(x, y, race);
				b.board[x][y].move = 3;
				break;
				case 2: b.board[x][y] = new Bishop(x, y, race);
				break;
				case 3: b.board[x][y] = new Knight(x, y, race);
				break;
				case 4: b.board[x][y] = new Queen(x, y, race);
				break;
				default: System.out.println("C'mon man, you had 4 choices. Not like you have even one irl.\n");
				q=0;
				}
			}
		}
	}
}

// Pawn movements:
// If first move - Can move one or two spaces
// For killing - Only one step diagonal allowed
// Non first moves - Only one space ahead
// Can't move if space directly ahead is occupied.
// CAN'T GO BACKWARDS 