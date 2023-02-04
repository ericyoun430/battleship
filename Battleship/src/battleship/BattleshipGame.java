package battleship;

//Import scanner for user input
import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {
		
		//Create ocean and place ships randomly
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		
		//Print out rules
		System.out.println("Rules:");
		System.out.println("The computer places the ten ships on the ocean in such a way that no ships are immediately adjacent to each ");
		System.out.println("other, either horizontally, vertically, or diagonally. ");
		System.out.println("The human player does not know where the ships are. The initial display of the ocean printed to the ");
		System.out.println("console therefore shows a 10 by 10 array of ‘.‘");
		System.out.println("The human player tries to hit the ships, by indicating a specific row and column number (r,c). The ");
		System.out.println("computer responds with one bit of information saying, “hit” or “miss”.");
		System.out.println("When a ship is hit but not sunk, the program does not provide any information about what kind of a ");
		System.out.println("ship was hit. However, when a ship is hit and sinks, the program prints out a message ");
		System.out.println("“You just sank a ship - (type).” After each shot, the computer re-displays the ocean with the new information.");
		System.out.println("A ship is “sunk” when every square of the ship has been hit. Thus, it takes four hits (in four different places) ");
		System.out.println("to sink a battleship, three to sink a cruiser, two for a destroyer, and one for a submarine.");
		System.out.println("The object is to sink the fleet with as few shots as possible; the best possible score would be 20 ");
		System.out.println("(lower scores are better.) When all ships have been sunk, the program prints out a message that the ");
		System.out.println("game is over, and tells how many shots were required.");
		
		//Print out the game board at the beginning
		ocean.print();
		
		Scanner scanner = new Scanner(System.in);
		
		//While the game is not over, we keep asking for the human to shoot
		while (ocean.isGameOver()==false) {
			System.out.println("Enter row,column:");
			String input = scanner.next();
			
			//Checking if the user put in valid input
			if (validInput(input) == false) {
				System.out.println("Enter row from 0-9 and column from 0-9 in row,col fashion.");
				continue;
			}
			
			//Get good inputs to use
			int [] output = cleanInput(input);
			
			int row = output[0];
			int col = output[1];
			
			//If shootAt returns true, then we check if that ship is sunk. If it is sunk then we say the ship sank
			//and the type of ship. If it isn't sunk then we print you got a hit
			//If shootAt is false then it is a miss
			if (ocean.shootAt(row, col)) {
				Ship currShip = ocean.getShipArray()[row][col];
				if (currShip.isSunk()) {
					System.out.println("You just sank a ship - " + currShip.getShipType());
				} else {
					System.out.println("You got a hit");
				}
			} else {
				System.out.println("You missed");
			}
			
			//System.out.println(ocean.getShipArray()[0][0].getHit()[0]);
			
			//Print out the ocean again
			ocean.print();
			ocean.printWithShips();
			
			
		}
		
		//Game is over and print out total shots fired
		System.out.println("");
		System.out.println("The game is over:");
		System.out.println("It took you " + ocean.getShotsFired() +" total shots to win.");
		
		scanner.close();
	}
	
	/**
	 * Takes input and says if the input is valid or not
	 * @param input as a string that needs to be checked for validity
	 * @return boolean true if valid, false if not
	 */
	private static boolean validInput(String input) {
		//Input has to be length 3 if in row,col input format
		if (input.length() != 3) {
			return false;
		}
		
		//Get the first and last characters as strings
		String firstVal = input.substring(0, 1);
		String secondVal = input.substring(2);
		
		//If the parse doesn't work,return false
	    try {
	        Integer.parseInt(firstVal);
	        Integer.parseInt(secondVal);
	    } catch (NumberFormatException e) {
	        return false;
	    }
		
	    //If all works return true
		return true;
	}
	
	/**
	 * Takes in input that is already validated and then outputs an array with valid inputs in row, col format
	 * @param input String that has been checked for validity already
	 * @return an array with row value in index 0 and column value in index 1, with both being ints.
	 */
	private static int[] cleanInput(String input) {
		//Get the values and parse.
		String firstVal = input.substring(0, 1);
		String secondVal = input.substring(2);
        int firstNum = Integer.parseInt(firstVal);
        int secondNum = Integer.parseInt(secondVal);
        
        //Put into array and output
        int [] output = new int[2];
        output[0] = firstNum;
        output[1] = secondNum;
        return output;
	}

}
