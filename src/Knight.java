import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece{ // Horse
	
	public Knight() {
		super();
	}
	
	public Knight(int x, int y, char race) {
		super(x, y, race, 'H');
	}
	
	public Set<Coordinate> displayMoves(Board b){
		Set<Coordinate> possible = new HashSet<Coordinate>();
		
		// One up
		if((x+1 < 8) && (y+2 < 8)) {
			if(b.board[x+1][y+2] == null || b.board[x+1][y+2].race != race)
				possible.add(new Coordinate(x+1, y+2));
		}
		
		if((x+1 < 8) && (y-2 >= 0)) {
			if(b.board[x+1][y-2] == null || b.board[x+1][y-2].race != race)
				possible.add(new Coordinate(x+1, y-2));
		}
		
		// Two up
		if((x+2 < 8) && (y+1 < 8)) {
			if(b.board[x+2][y+1] == null || b.board[x+2][y+1].race != race)
				possible.add(new Coordinate(x+2, y+1));
		}
		
		if((x+2 < 8) && (y-1 >= 0)) {
			if(b.board[x+2][y-1] == null || b.board[x+2][y-1].race != race)
				possible.add(new Coordinate(x+2, y-1));
		}
		
		// One down (literally)
		if((x-1 >= 0) && (y+2 < 8)) {
			if(b.board[x-1][y+2] == null || b.board[x-1][y+2].race != race)
				possible.add(new Coordinate(x-1, y+2));
		}
		
		if((x-1 >= 0) && (y-2 >= 0)) {
			if(b.board[x-1][y-2] == null || b.board[x-1][y-2].race != race)
				possible.add(new Coordinate(x-1, y-2));
		}
		
		// Two down (never happening)
		if((x-2 >= 0) && (y+1 < 8)) {
			if(b.board[x-2][y+1] == null || b.board[x-2][y+1].race != race)
				possible.add(new Coordinate(x-2, y+1));
		}
		
		if((x-2 >= 0) && (y-1 >= 0)) {
			if(b.board[x-2][y-1] == null || b.board[x-2][y-1].race != race)
				possible.add(new Coordinate(x-2, y-1));
		}
		
		// Three down doesn't even exist
		
		return possible;
	}
}
