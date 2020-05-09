import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece{ // Haathi
	
	public Rook() {
		super();
	}
	
	public Rook(int x, int y, char race) {
		super(x, y, race, 'R');
	}
	
	public Set<Coordinate> displayMoves(Board b){
		Set<Coordinate> possible = new HashSet<Coordinate>();
		
		// Loop from x to 0, y remains the same
		for(int i = x-1; i >= 0; i--) {
			if(b.board[i][y] != null) {
				if(b.board[i][y].race != race)
					possible.add(new Coordinate(i, y));
				break;
			}
			else
				possible.add(new Coordinate(i, y));
		}
		
		// Loop from x to 7, y remains the same
		for(int i = x+1; i < 8; i++) {
			if(b.board[i][y] != null) {
				if(b.board[i][y].race != race)
					possible.add(new Coordinate(i, y));
				break;
			}
			else
				possible.add(new Coordinate(i, y));
		}
		
		// Loop from y to 0, x remains the same
		for(int i = y-1; i >= 0; i--) {
			if(b.board[x][i] != null) {
				if(b.board[x][i].race != race)
					possible.add(new Coordinate(x, i));
				break;
			}
			else
				possible.add(new Coordinate(x, i));
		}
		
		// Loop from y to 7, x remains the same
		for(int i = y+1; i < 8; i++) {
			if(b.board[x][i] != null) {
				if(b.board[x][i].race != race)
					possible.add(new Coordinate(x, i));
				break;
			}
			else
				possible.add(new Coordinate(x, i));
		}
		
		
		return possible;
	}
	
}
