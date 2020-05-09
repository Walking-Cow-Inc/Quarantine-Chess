import java.util.HashSet;
import java.util.Set;

public class King extends Piece{ // We are this
	
	public boolean checkCheck = false;
	
	public King() {
		super();
	}
	
	public King(int x, int y, char race) {
		super(x, y, race, 'K');
	}
	
	public Set<Coordinate> displayMoves(Board b){
		Set<Coordinate> possible = new HashSet<Coordinate>();
		Set<Coordinate> opponentPieces = (race == 'b') ? b.white : b.black;
		Set<Coordinate> opponentMoves = new HashSet<Coordinate>();
		
		for(Coordinate each : opponentPieces) {
			if(b.board[each.x][each.y] instanceof King)
				continue;
			if(b.board[each.x][each.y] instanceof Pawn)
				opponentMoves.addAll(((Pawn)(b.board[each.x][each.y])).killableMoves(b));
			else
				opponentMoves.addAll(b.board[each.x][each.y].displayMoves(b));
		}
			
		
		// x remains the same
		if(y + 1 < 8) {
			if(b.board[x][y+1] == null || b.board[x][y+1].race != race)
				possible.add(new Coordinate(x, y+1));
		}
		
		if(y - 1 < 8) {
			if(b.board[x][y-1] == null || b.board[x][y-1].race != race)
				possible.add(new Coordinate(x, y-1));
		}
		
		// x increases by one
		if(x + 1 < 8 && y + 1 < 8) {
			if(b.board[x+1][y+1] == null || b.board[x+1][y+1].race != race)
				possible.add(new Coordinate(x+1, y+1));
		}
		
		if(x + 1 < 8 && y - 1 >= 0) {
			if(b.board[x+1][y-1] == null || b.board[x+1][y-1].race != race)
				possible.add(new Coordinate(x+1, y-1));
		}
		
		if(x + 1 < 8) {
			if(b.board[x+1][y] == null || b.board[x+1][y].race != race)
				possible.add(new Coordinate(x+1, y));
		}
		
		// x decreases by one
		if(x - 1 >= 0 && y + 1 < 8) {
			if(b.board[x-1][y+1] == null || b.board[x-1][y+1].race != race)
				possible.add(new Coordinate(x-1, y+1));
		}
		
		if(x - 1 >= 0 && y - 1 >= 0) {
			if(b.board[x-1][y-1] == null || b.board[x-1][y-1].race != race)
				possible.add(new Coordinate(x-1, y-1));
		}
		
		if(x - 1 >= 0) {
			if(b.board[x-1][y] == null || b.board[x-1][y].race != race)
				possible.add(new Coordinate(x-1, y));
		}
		
		possible.removeAll(opponentMoves);
		
		// TODO: Coolify
		switch(checkCastle(b, race)) {
		
		// Black Castling
		case 1: possible.add(new Coordinate(0, 2));
		break;
		case 10: possible.add(new Coordinate(0, 6));
		break;
		case 11: possible.add(new Coordinate(0, 2));
				 possible.add(new Coordinate(0, 6));
		break;
		
		// White Castling
		case 21: possible.add(new Coordinate(7, 2));
		break;
		case 30: possible.add(new Coordinate(7, 6));
		break;
		case 31: possible.add(new Coordinate(7, 2));
		          possible.add(new Coordinate(7, 6));
		break;
		}	
		
		return possible;
	}
	
	
	public int checkCastle(Board b, char race) { // AMAZING IDEA BY JAYMIN
		
		// Shit Efficiency, try to fix later (Found solution)
		Set<Coordinate> opponentPieces = (race == 'b') ? b.white : b.black;
		Set<Coordinate> opponentMoves = new HashSet<Coordinate>();
		
		for(Coordinate each : opponentPieces) {
			if(b.board[each.x][each.y] instanceof King)
				continue;
			if(b.board[each.x][each.y] instanceof Pawn)
				opponentMoves.addAll(((Pawn)(b.board[each.x][each.y])).killableMoves(b));
			else
				opponentMoves.addAll(b.board[each.x][each.y].displayMoves(b));
		}
		
		switch(race) {
		case 'b': if(b.board[0][4] != null && b.board[0][4].move == 0 ) {
			int i = 0;
			if(b.board[0][0] != null && b.board[0][0].move == 0) {
				if(b.board[0][1] == null && b.board[0][2] == null && b.board[0][3] == null) {
					if(!opponentMoves.contains(new Coordinate(0, 2)) && !opponentMoves.contains(new Coordinate(0,3)) 
							&& !opponentMoves.contains(new Coordinate(0, 4))){
						i++;
					}
				}
			}
			
			if(b.board[0][7] != null && b.board[0][7].move == 0) {
				if(b.board[0][5] == null && b.board[0][6] == null) {
					if(!opponentMoves.contains(new Coordinate(0, 6)) && !opponentMoves.contains(new Coordinate(0,5)) 
							&& !opponentMoves.contains(new Coordinate(0, 4))){
						i+=10;
					}
				}
			}
			return i;
		}
		break;
			
		case 'w': if(b.board[7][4] != null && b.board[7][4].move == 0 ) {
			int i = 20;
			if(b.board[7][0] != null && b.board[7][0].move == 0) {
				if(b.board[7][1] == null && b.board[7][2] == null && b.board[7][3] == null) {
					if(!opponentMoves.contains(new Coordinate(7, 2)) && !opponentMoves.contains(new Coordinate(7,3)) 
							&& !opponentMoves.contains(new Coordinate(7, 4))){
						i++;
					}
				}
			}
			
			if(b.board[7][7] != null && b.board[7][7].move == 0) {
				if(b.board[7][5] == null && b.board[7][6] == null) {
					if(!opponentMoves.contains(new Coordinate(7, 6)) && !opponentMoves.contains(new Coordinate(7,5)) 
							&& !opponentMoves.contains(new Coordinate(7, 4))){
						i+=10;
					}
				}
			}
			return i;
		}
		break;
		}
		return 69;
	}
	
	// Conditions for Castling:
	// - King should never have moved
	// - Rook involved shouldn't have moved
	// - All blocks between the two must be empty
	// - King should not be in check
	// - King shouldn't move to an attackable block
	// - King should not move through check

}
