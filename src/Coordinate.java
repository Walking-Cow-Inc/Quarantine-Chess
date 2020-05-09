public class Coordinate {
	public int x;
	public int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public boolean equals(Object o) {
		if(o instanceof Coordinate) {
			Coordinate toCheck = (Coordinate) o;
			return toCheck.x == x && toCheck.y == y;
		}
		else
			return false;
	}
	
	public int hashCode() {
		return x*1000 + y;
	}
}

// 2, 3:- One number for 2, 3: Suppose x
// 4, 5:- A different number, suppose y
// 2, 3:- You'll get x
