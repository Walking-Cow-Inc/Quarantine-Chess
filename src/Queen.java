import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece{ // We don't have
	
	public Queen() {
		super();
	}
	
	public Queen(int x, int y, char race) {
		super(x, y, race, 'Q');
	}
	
	public Queen(Queen q) {
		x = q.x;
		y = q.y;
		move = q.move;
		race = q.race;
		type = q.type;
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
		else if(x == kingPos.x && y > kingPos.y) {
			int j = y-1;
			while(j > kingPos.y) {
				between.add(new Coordinate(x, j));
			}
		}
		else if(x == kingPos.x && y < kingPos.y) {
			int j = y+1;
			while(j < kingPos.y) {
				between.add(new Coordinate(x, j));
			}
		}
		else if(y == kingPos.y && x > kingPos.x) {
			int i = x-1;
			while(i > kingPos.x) {
				between.add(new Coordinate(i, y));
			}
		}
		else if(y == kingPos.y && x < kingPos.x) {
			int i = x+1;
			while(i < kingPos.x) {
				between.add(new Coordinate(i, y));
			}
		}
		return between;
	}
}
