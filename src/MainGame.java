import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainGame {
	// TODO: Make both the players
	// TODO: Make the board
	// TODO: Start with white move
	// Format of each move:
	// - I want to move 1,3 (Input)
	// - What is there in 1,3 on our board (Board)
	// - Show all the valid moves for the Piece and the number of possible moves and if there are killings(Piece)
	//   - If the number of possible moves is zero, then run the correction function
	//   - Else, ask for the move from the player
	// - If the player enters a stupid move, go to error correcting
	// - Else: Check if there was a killing
	// - Change the board accordingly
	// Switch (Input)
	
	private static char turn = 'w'; // w is White and b is Black 
	
	public static void main(String args[]) {
		Board b = new Board();  // This makes the board
		System.out.println(b);
		int x = 0, y = 0, newx, newy;
		Scanner sc = new Scanner(System.in);
		Set<Coordinate> possible = new HashSet<>();
		
		while(gameNotOver(b) == 0) {
			switch(turn) {
			case 'w': System.out.println("White's turn");
			System.out.println("Enter the piece which you want to move");
			x = sc.nextInt();
			y = sc.nextInt();
			if(x > 7 || x < 0 || y > 7 || y < 0) {
				System.out.println("Out of your limits, just like she was\n\n"); // Change before showing
				continue;
			}
			if(b.board[x][y] == null || b.board[x][y].race != turn || b.board[x][y].displayMoves(b).isEmpty()) {
				System.out.println("No chances, in more than one way");
				System.out.print("It's still ");
				continue;
			}
			// It's not empty
			possible = b.board[x][y].displayMoves(b);
			System.out.print("The allowed moves for this piece are: \n| ");
			for(Coordinate each : possible) {
				System.out.print(each + " | ");
			}
			System.out.println("\n");
			turn = 'b';
			break;
			
			case 'b': System.out.println("Black's turn");
			System.out.println("Enter the piece which you want to move");
			x = sc.nextInt();
			y = sc.nextInt();
			if(x > 7 || x < 0 || y > 7 || y < 0) {
				System.out.println("Out of your limits, just like she was\n\n"); // Change before showing
				continue;
			}
			if(b.board[x][y] == null || b.board[x][y].race != turn || b.board[x][y].displayMoves(b).isEmpty()) {
				System.out.println("No chances, in more than one way");
				System.out.print("It's still ");
				continue;
			}
			// It's not empty
			possible = b.board[x][y].displayMoves(b);
			System.out.print("The allowed moves for this piece are: \n| ");
			for(Coordinate each : possible) {
				System.out.print(each + " | ");
			}
			System.out.println("\n");
			turn = 'w';
			break;
			}
			
			// Where to move the piece
			int a = 1;
			while(a != 0) {
				System.out.println("Enter the position where you want to move this piece to");
				newx = sc.nextInt();
				newy = sc.nextInt();
				
				if(b.board[x][y] instanceof King) {
					
					// This part is only to move the Rook
					if(newy - y == 2) {
						b.board[x][5] = b.board[x][7];
						b.board[x][5].y = 5;
						b.board[x][7] = null;
						switch(x) {
						case 0: b.black.remove(new Coordinate(0, 7));
						b.black.add(new Coordinate(0, 5));
						break;
						
						case 7: b.white.remove(new Coordinate(7, 7));
						b.white.add(new Coordinate(7, 5));
						break;
						}
					}
					else if(y - newy == 2) {
						b.board[x][3] = b.board[x][0];
						b.board[x][3].y = 3;
						b.board[x][0] = null;
						switch(x) {
						case 0: b.black.remove(new Coordinate(0, 0));
						b.black.add(new Coordinate(0, 3));
						break;
						
						case 7: b.white.remove(new Coordinate(7, 0));
						b.white.add(new Coordinate(7, 3));
						break;
						}
					}
					
					// To add the new King positions to the array
					switch(b.board[x][y].race) {
					case 'b': b.kingPos[0] = new Coordinate(newx, newy);
					break;
					case 'w': b.kingPos[1] = new Coordinate(newx, newy);
					break;
					}
				}
				
				if(possible.contains(new Coordinate(newx, newy))) {
					
					b.board[newx][newy] = b.board[x][y]; // Changes the position of the old piece on the board
					b.board[newx][newy].x = newx;
					b.board[newx][newy].y = newy;
					b.board[newx][newy].move++;
					
					if(b.board[x][y].race == 'b') {
						b.black.remove(new Coordinate(x, y));
						b.black.add(new Coordinate(newx, newy));
						b.white.remove(new Coordinate(newx, newy));
					}
					
					else {
						b.white.remove(new Coordinate(x, y));
						b.white.add(new Coordinate(newx, newy));
						b.black.remove(new Coordinate(newx, newy));
					}
					
					if(b.board[newx][newy] instanceof Pawn)
						((Pawn)b.board[newx][newy]).convertIfPossible(b);
					
					b.board[x][y] = null; // Move is complete
					System.out.println(b);
					
					Set<Coordinate> moves = new HashSet<Coordinate>();
					switch(b.board[newx][newy].race) {
					case 'b': for(Coordinate each : b.black) {
						if(b.board[each.x][each.y] instanceof King)
							continue;
						if(b.board[each.x][each.y] instanceof Pawn)
							moves.addAll(((Pawn)(b.board[each.x][each.y])).killableMoves(b));
						else
							moves.addAll(b.board[each.x][each.y].displayMoves(b));
					}
					// Now we have all the moves of the black pieces
					if(moves.contains(b.kingPos[1])){
						System.out.println("Check");
						((King)b.board[b.kingPos[1].x][b.kingPos[1].y]).checkCheck = true;
					}
						break;
					case 'w': for(Coordinate each : b.white) {
						if(b.board[each.x][each.y] instanceof King)
							continue;
						if(b.board[each.x][each.y] instanceof Pawn)
							moves.addAll(((Pawn)(b.board[each.x][each.y])).killableMoves(b));
						else
							moves.addAll(b.board[each.x][each.y].displayMoves(b));
					}
					// Now we have all the moves of the white pieces
					if(moves.contains(b.kingPos[0])){
						System.out.println("Check");
						((King)b.board[b.kingPos[0].x][b.kingPos[0].y]).checkCheck = true;
					}
					break;
					}
					
					a = 0;
				}
				else {
					System.out.print("Re-");
				}
			}
		}
		if(gameNotOver(b) == 1)
			System.out.println("White wins. I don't have a dream");
		else if(gameNotOver(b) == 2)
			System.out.println("Black wins. Slavery is no more");
		else
			System.out.println("Well that was pointless");
	}
		
		
		
//		for(int i = 0; i < 8; i++) {
//			Set<Coordinate> possible = b.board[7][i].displayMoves(b);
//			System.out.println("This piece is: 7, " + i);
//			for(Coordinate each : possible) {
//				System.out.println(each);
//			}
//			System.out.println("\n\n");
//		}

	private static List<Coordinate> getKiller(Board b, char race){
		List<Coordinate> killers = new ArrayList<>();
		Set<Coordinate> opponentPieces = (race == 'b') ? b.white : b.black;
		Coordinate king = (race == 'b') ? b.kingPos[0] : b.kingPos[1];
		Set<Coordinate> opponentMoves = new HashSet<Coordinate>();
		
		for(Coordinate each : opponentPieces) {
			if(b.board[each.x][each.y] instanceof King)
				continue;
			if(b.board[each.x][each.y] instanceof Pawn) {
				if ((((Pawn)(b.board[each.x][each.y])).killableMoves(b)).contains(king))
					killers.add(each);
			}
			else {
				if(b.board[each.x][each.y].displayMoves(b).contains(king))
					killers.add(each);
			}
		}
		return killers;
	}
	
	private static int gameNotOver(Board b) {
		// For Checkmate: 3 parts
		// Part 1: King is in check and has no available moves
		
		// This if check if the black king is in check
		if((((King)b.board[b.kingPos[0].x][b.kingPos[0].y]).checkCheck)&&b.board[b.kingPos[0].x][b.kingPos[0].y].displayMoves(b).size() == 0){
			// Part 2: Some piece can kill the checking piece
			List<Coordinate> killers = new ArrayList<Coordinate>(getKiller(b, 'b'));
			if(killers.size() > 1)
				return 1;
			
			// Now check if the killer can be killed
			for(Coordinate each : b.black) {
				if(b.board[each.x][each.y] instanceof King)
					continue;
				if(b.board[each.x][each.y] instanceof Pawn) {
					if(((Pawn)b.board[each.x][each.y]).killableMoves(b).contains(killers.get(0)))
							return 0;
				}
				else {
					if((b.board[each.x][each.y]).displayMoves(b).contains(killers.get(0)))
							return 0;
				}
			}
			return 1;
		}
		
		//This if checks if the white king is in check
		if((((King)b.board[b.kingPos[1].x][b.kingPos[1].y]).checkCheck)&&b.board[b.kingPos[1].x][b.kingPos[1].y].displayMoves(b).size() == 0){
			// Part 2: Some piece can kill the checking piece
			List<Coordinate> killers = new ArrayList<Coordinate>(getKiller(b, 'w'));
			if(killers.size() > 1)
				return 2;
			
			// Now check if the killer can be killed
			for(Coordinate each : b.white) {
				if(b.board[each.x][each.y] instanceof King)
					continue;
				if(b.board[each.x][each.y] instanceof Pawn) {
					if(((Pawn)b.board[each.x][each.y]).killableMoves(b).contains(killers.get(0)))
							return 0;
				}
				else {
					if((b.board[each.x][each.y]).displayMoves(b).contains(killers.get(0)))
							return 0;
				}
			}
			return 2;
		}
		// Part 3: Some piece obstructs the checking piece
		return 0;
	}
	
	// Progress Report (13th April 07:56 PM IST): Made and defined 10 classes and decided structure of the game 
	// Progress Report (14th April 07:21 PM IST): Made the Board and displayed it using 2 lines (not really, but sure) (Jaymin learned how to use classes)
	// Progress Report (15th April 07:39 PM IST): Determined movements for the Pawn piece (No visual results but dimaag kharab kiya)
	// Progress Report (16th April 11:17 PM IST): Determined movements for Rook, Bishop and Queen (#HardWork not really but sure) (#NoQueenGang))
	// Progress Report (17th April The hole day): Happy Birthday JaDeBlue (Pronounced 'Ja De')
	// Progress Report (18th April 07:18 PM IST): Changed from ArrayList to Set, Fixed the movement for Pawn, Changed the board to differentiate race (#racism)
	// Progress Report (19th April 11:99 PM IST): Created the movement for the knight, fixed (many) bugs and (tried to do) the movement for the King (and failed)
	// Progress Report (20th April 11:16 PM IST): Fixed King (hopefully didn't fail) and made killableMoves for pawn. FIRST PART OF GAME DONE!!! 
	// Progress Report (21th April 11:99 PM IST): Figured out turn switching with error correction (mostly), took input for the piece and formatted the output.
	// Progress Report (22th April The hole day): Laptop Update (#shewonttalktous)
	// Progress Report (23th April 11:55 PM IST): The pieces move and killing works. (Kinda) Lots of TESTING (Testing for the win).
	// Progress Report (24th April 11:06 PM IST): Fixed the pawn movement issue (AGAIN). Tried to make castling but failed spectacularly (What a surprise)
	// Progress Report (25th April 11:99 PM IST): Castling (Like that's it) (Small amount of testing pending) (Found and fixed other errors)
	// Progress Report (A 3 day time leap taken): We just lazy (One of the many reasons we don't have queens)
	// Progress Report (29th April 11:16 PM IST): Pawn conversion complete (We are converters) (Short work, but effective. That's what she said)
	// Progress Report (A 2 day time leap taken): Vatsav had exams.
	// Progress Report (02nd  May  11:09 PM IST): Made a single variable which doesn't do shit (Yaay!)
	// Progress Report (A 1 day time leap taken): Vatsav had to talk to his gf.
	// Progress Report (04th  May  11:04 PM IST): Figured out one part of how to finish the game. We now know if the King is in check.
	// Progress Report (A 1 day time leap taken): Vatsav had a nice sleep.
	// Progress Report (06th  May  11:22 PM IST): Figured out how to do check, but didn't do it. (Just like she'll never do us)
	// Progress Report (A 1 day time leap taken): We forgot
	// Progress Report (08th  May  11:99 PM IST): Finished Part 2 for checkmate and tested previous work (Both be drunk and dead)
}