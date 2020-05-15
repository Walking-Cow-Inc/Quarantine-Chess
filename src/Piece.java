import java.util.Collection;
import java.util.Set;

public class Piece {
	protected int x;
	protected int y;
	protected char race;
	protected char type;
	protected int move;
	
	public Piece() { // This is never going to happen
		x = 0;
		y = 0;
		race = 'h';
		move = 0;
	}
	
	public Piece(int x, int y, char race, char type) {
		this.x = x;
		this.y = y;
		this.race = race;
		this.type = type;
	}
	
	public char getType() {
		return type;
	}
	
	public Set<Coordinate> displayMoves(Board b) {
		return null;
	}

	public Collection<? extends Coordinate> destructiveMoves(Board b) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// TODO: Take in x and y
	// TODO: Check for possible moves and return the moves (Function)
	// TODO: Display the possible moves (Only when the moves are more than 0)
	
}
