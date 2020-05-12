import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece{ // IDK
	
	public Bishop() {
		super(); 
	}
	
	public Bishop(int x, int y, char race) {
		super(x, y, race, 'B');
	}
	
	public Bishop(Bishop q) {
		x = q.x;
		y = q.y;
		move = q.move;
		race = q.race;
		type = q.type;
	}
	
	public Set<Coordinate> displayMoves(Board b){
		Set<Coordinate> possible = new HashSet<Coordinate>();
		
		// x and y both increment
		int i = x+1, j = y+1;
		while((i < 8) && (j < 8)) {
			if(b.board[i][j] != null) {
				if(b.board[i][j].race != race)
					possible.add(new Coordinate(i, j));
				break;
			}
			else
				possible.add(new Coordinate(i, j));
			i++;
			j++;
		}
		
		// x decrements and y increments
		i = x-1;
		j = y+1;
		while((i >= 0) && (j<8)) {
			if(b.board[i][j] != null) {
				if(b.board[i][j].race != race)
					possible.add(new Coordinate(i, j));
				break;
			}
			else
				possible.add(new Coordinate(i, j));
			i--;
			j++;
		}
		
		// x increments and y decrements
		i = x+1;
		j = y-1;
		while((i < 8) && (j >= 0)) {
			if(b.board[i][j] != null) {
				if(b.board[i][j].race != race)
					possible.add(new Coordinate(i, j));
				break;
			}
			else
				possible.add(new Coordinate(i, j));
			
			i++;
			j--;
		}
		
		// x and y both decrement
		i = x-1;
		j = y-1;
		while((i >= 0) && (j >= 0)) {
			if(b.board[i][j] != null) {
				if(b.board[i][j].race != race)
					possible.add(new Coordinate(i, j));
				break;
			}
			else
				possible.add(new Coordinate(i, j));
			i--;
			j--;
		}
		
		return possible;
	}
	
	public Set<Coordinate> getBetween(Coordinate kingPos){
		Set<Coordinate> between = new HashSet<Coordinate>();
		if(x > kingPos.x && y > kingPos.y) {
			int i = x-1, j = y-1;
			while(i > kingPos.x) {
				between.add(new Coordinate(i, j));
				i--;
				j--;
			}
		}
		else if(x > kingPos.x && y < kingPos.y) {
			int i = x-1, j = y+1;
			while(i > kingPos.x) {
				between.add(new Coordinate(i, j));
				i--;
				j++;
			}
		}
		else if(x < kingPos.x && y > kingPos.y) {
			int i = x+1, j = y-1;
			while(i < kingPos.x) {
				between.add(new Coordinate(i, j));
				i++;
				j--;
			}
		}
		else if(x < kingPos.x && y < kingPos.y){
			int i = x+1, j = y+1;
			while(i < kingPos.x) {
				between.add(new Coordinate(i, j));
				i++;
				j++;
			}
		}
		return between;
	}
}
