package battleship;

//Import Random package
import java.util.Random;


public class Ocean {
	
	private Ship[][] ships = new Ship[10][10];
	
	private int shotsFired;
	
	private int hitCount;
	
	private int shipsSunk;
	
	/**
	 * Constructor for ocean. Initializes every ship in the ocean as empty sea
	 */
	public Ocean() {
		//Initialize every ship as empty sea in the beginning
		for (int i =0; i < 10;i++) {
			for (int j = 0; j < 10; j++) {
				ships[i][j] = new EmptySea();
				ships[i][j].setBowRow(i);
				ships[i][j].setBowColumn(j);
			}
		}
		
		//initialize shots, hits, and ships sunk
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
	}
	
	/**
	 * Place all ten ships randomly on the (initially empty) ocean. 
	 * Place larger ships before smaller ones, or you may end up
	 *  with no legal place to put a large ship. You will want 
	 *  to use the Random class in the java.util package, so 
	 *  look that up in the Java API.
	 */
	void placeAllShipsRandomly() {
		Random randomizer = new Random();
		
		
		//True if ship has not been placed, false if been placed
		boolean b1 = true;
		boolean c1 = true;
		boolean c2 = true;
		boolean d1 = true;
		boolean d2 = true;
		boolean d3 = true;
		boolean s1 = true;
		boolean s2 = true;
		boolean s3 = true;
		boolean s4 = true;
		
		//While the ships haven't all been placed keep going
		while (b1 || c1 || c2 || d1 || d2 || d3 || s1 || s2 || s3 || s4) {
			//Random placement being checked in ocean
			int row = randomizer.nextInt(10);
			int col = randomizer.nextInt(10);
			
			
			//If 0, then horizontal is false, if 1 then horizontal is true
			int whichHorizontal = randomizer.nextInt(2);
			boolean horizontal = false;
			if (whichHorizontal == 1) {
				horizontal = true;
			}
			
			
			//If battleship hasn't been placed, check if it can be placed at the location. If so, place it.
			//If the battleship can't be placed at that location, continue and we get new random coordinates to try
			//for battleship. We do this so we place the ships in order. Also set the indicator bool to false after
			if (b1) {
				Battleship battleship = new Battleship();
				if (battleship.okToPlaceShipAt(row, col, horizontal, this)) {
					battleship.placeShipAt(row, col, horizontal, this);
					b1 = false;
				} else {
					continue;
				}
			} else if (c1) {
			//If cruiser hasn't been placed, check if it can be placed at the location. If so, place it.
			//If the cruiser can't be placed at that location, continue and we get new random coordinates to try
			//for cruiser. We do this so we place the ships in order. Also set the indicator bool to false after
				Cruiser cruiserOne = new Cruiser();
				if (cruiserOne.okToPlaceShipAt(row, col, horizontal, this)) {
					cruiserOne.placeShipAt(row, col, horizontal, this);
					c1 = false;
				} else {
					continue;
				}
			} else if (c2) {
			//If cruiser hasn't been placed, check if it can be placed at the location. If so, place it.
			//If the cruiser can't be placed at that location, continue and we get new random coordinates to try
			//for cruiser. We do this so we place the ships in order. Also set the indicator bool to false after
				Cruiser cruiserTwo = new Cruiser();
				if (cruiserTwo.okToPlaceShipAt(row, col, horizontal, this)) {
					cruiserTwo.placeShipAt(row, col, horizontal, this);
					c2 = false;
				} else {
					continue;
				}
			} else if (d1) {
			//If destroyer hasn't been placed, check if it can be placed at the location. If so, place it.
			//If the destroyer can't be placed at that location, continue and we get new random coordinates to try
			//for destroyer. We do this so we place the ships in order. Also set the indicator bool to false after
				Destroyer destroyerOne = new Destroyer();
				if (destroyerOne.okToPlaceShipAt(row, col, horizontal, this)) {
					destroyerOne.placeShipAt(row, col, horizontal, this);
					d1 = false;
				} else {
					continue;
				}
			} else if (d2) {
			//If destroyer hasn't been placed, check if it can be placed at the location. If so, place it.
			//If the destroyer can't be placed at that location, continue and we get new random coordinates to try
			//for destroyer. We do this so we place the ships in order. Also set the indicator bool to false after
				Destroyer destroyerTwo = new Destroyer();
				if (destroyerTwo.okToPlaceShipAt(row, col, horizontal, this)) {
					destroyerTwo.placeShipAt(row, col, horizontal, this);
					d2 = false;
				} else {
					continue;
				}
			} else if (d3) {
			//If destroyer hasn't been placed, check if it can be placed at the location. If so, place it.
			//If the destroyer can't be placed at that location, continue and we get new random coordinates to try
			//for destroyer. We do this so we place the ships in order. Also set the indicator bool to false after
				Destroyer destroyerThree = new Destroyer();
				if (destroyerThree.okToPlaceShipAt(row, col, horizontal, this)) {
					destroyerThree.placeShipAt(row, col, horizontal, this);
					d3 = false;
				} else {
					continue;
				}
			} else if (s1) {
			//If destroyer hasn't been placed, check if it can be placed at the location. If so, place it.
			//If the destroyer can't be placed at that location, continue and we get new random coordinates to try
			//for destroyer. We do this so we place the ships in order. Also set the indicator bool to false after
				Submarine submarineOne = new Submarine();
				if (submarineOne.okToPlaceShipAt(row, col, horizontal, this)) {
					submarineOne.placeShipAt(row, col, horizontal, this);
					s1 = false;
				} else {
					continue;
				}
			} else if (s2) {
			//If destroyer hasn't been placed, check if it can be placed at the location. If so, place it.
			//If the destroyer can't be placed at that location, continue and we get new random coordinates to try
			//for destroyer. We do this so we place the ships in order. Also set the indicator bool to false after
				Submarine submarineTwo = new Submarine();
				if (submarineTwo.okToPlaceShipAt(row, col, horizontal, this)) {
					submarineTwo.placeShipAt(row, col, horizontal, this);
					s2 = false;
				} else {
					continue;
				}
			} else if (s3) {
			//If destroyer hasn't been placed, check if it can be placed at the location. If so, place it.
			//If the destroyer can't be placed at that location, continue and we get new random coordinates to try
			//for destroyer. We do this so we place the ships in order. Also set the indicator bool to false after
				Submarine submarineThree = new Submarine();
				if (submarineThree.okToPlaceShipAt(row, col, horizontal, this)) {
					submarineThree.placeShipAt(row, col, horizontal, this);
					s3 = false;
				} else {
					continue;
				}
			} else if (s4) {
			//If destroyer hasn't been placed, check if it can be placed at the location. If so, place it.
			//If the destroyer can't be placed at that location, continue and we get new random coordinates to try
			//for destroyer. We do this so we place the ships in order. Also set the indicator bool to false after
				Submarine submarineFour = new Submarine();
				if (submarineFour.okToPlaceShipAt(row, col, horizontal, this)) {
					submarineFour.placeShipAt(row, col, horizontal, this);
					s4 = false;
				} else {
					continue;
				}
			}
		}
	}
	
	
	/**
	 * Returns true if the given location contains a ship, false if it does not
	 * @param row of the ocean
	 * @param column of the ocean
	 * @return true if location has a ship, false otherwise
	 */
	boolean isOccupied(int row, int column) {
		
		//If the ship is an empty sea ship, then return false; otherwise return true
		Ship[][] shipArray = this.getShipArray();
		if (shipArray[row][column].getShipType() == "empty") {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns true if the given location contains a ”real” ship, still 
	 * afloat, (not an EmptySea), false if it does not. In addition, this 
	 * method updates the number of shots that have been fired, and the number
	 *  of hits. Note: If a location contains a “real” ship, shootAt should
	 *  return true every time the user shoots at that same location. Once 
	 *  a ship has been ”sunk”, additional shots at its location should 
	 *  return false.
	 * @param row of the ocean
	 * @param column of the ocean
	 * @return true if the location has a real ship and is still afloat; false 
	 * otherwise
	 */
	boolean shootAt(int row, int column) {
		//Increment shots fired count
		this.shotsFired++;
		
		//Ocean has to be occupied to return a true value
		if (this.isOccupied(row, column)) {
			
			//If the ship is sunk return false
			if (this.getShipArray()[row][column].isSunk()) {
				return false;
			}
			
			//Valid hit, meaning the ship hasn't been hit there before
			//and increment hit count. If the ship is now sunk increment
			//ships sunk.
			if (this.getShipArray()[row][column].shootAt(row, column)) {
				this.hitCount++;
				if (this.getShipArray()[row][column].isSunk()) {
					this.shipsSunk++;
				}
				return true;
			} else {
				return false;
			}
		}
		//Even if unoccupied we shoot so that we can get '-' on empty sea
		this.getShipArray()[row][column].shootAt(row, column);
		return false;
	}
	
	/**
	 * Returns the number of shots fired in the game
	 * @return the number of shots fired in the game
	 */
	int getShotsFired() {
		return this.shotsFired;
	}
	/**
	 * Returns the number of hits recorded (in the game). All hits are counted, not just
the first time a given square is hit.
	 * @return the number of hits recorded
	 */
	int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * Returns the number of ships sunk (in the game)
	 * @return number of ships sunk
	 */
	int getShipsSunk() {
		return this.shipsSunk;
	}
	
	/**
	 * Returns true if all ships have been sunk, otherwise false
	 * @return true if all ships have been sunk, otherwise false
	 */
	boolean isGameOver() {
		if (this.getShipsSunk() == 10) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the 10x10 array of Ships. The methods in the Ship class that take 
	 * an Ocean parameter need to be able to look at the contents of this array; 
	 * the placeShipAt() method even needs to modify it. While it is undesirable 
	 * to allow methods in one class to directly access instance variables in another 
	 * class, sometimes there is just no good alternative.
	 * @return
	 */
	Ship[][] getShipArray() {
		return this.ships;
	}
	
	/*
	 * Prints the Ocean. To aid the user, row numbers should be displayed along
	 *  the left edge of the array, and column numbers should be displayed 
	 *  along the top. Numbers should be 0 to 9, not 1 to 10.
	 */
	void print() {
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		
		
		Ship[][] shipArray = this.getShipArray();
		
		//Loop through each ship and print the value
		for (int i = 0; i < 10; i++) {
			
			//Get row value for display
			Integer temp = i;
			String lineStr = temp.toString() + " ";
			for (int j = 0; j < 10; j++) {
				
				//Get the current ship, hit array, bow row, and bow col
				Ship currShip = shipArray[i][j];
				boolean [] hitArray = currShip.getHit();
				int bowRow = currShip.getBowRow();
				int bowCol = currShip.getBowColumn();
				
				//Find the distance between the bow position and current position
				int dist = Math.abs(bowCol - j) + Math.abs(bowRow - i);
				
				
				//The distance between the bow position and the current position is the calculated
				//distance because bow position in hit array is always index 0.
				boolean hitOrNot = hitArray[dist];
				
				
				if (currShip.isSunk() || hitOrNot) {
					lineStr+=currShip.toString() + " ";
				} else {
					lineStr+=". ";
				}
			}
			lineStr = lineStr.substring(0,lineStr.length() - 1);
			System.out.println(lineStr);
		}
		
	}
	
	void printWithShips() {
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		
		Ship[][] shipArray = this.getShipArray();
		
		//Loop through each ship and print the value
		for (int i = 0; i < 10; i++) {
			
			//Get row value for display
			Integer temp = i;
			String lineStr = temp.toString() + " ";
			for (int j = 0; j < 10; j++) {
				Ship currShip = shipArray[i][j];
				String shipType = currShip.getShipType();
				
				if (shipType == "battleship") {
					lineStr+= "b ";
				} else if (shipType == "cruiser") {
					lineStr+= "c ";
				} else if (shipType == "destroyer") {
					lineStr+= "d ";
				} else if (shipType == "submarine") {
					lineStr+= "s ";
				} else {
					lineStr+= "  ";
				}
			}
			lineStr = lineStr.substring(0,lineStr.length() - 1);
			//System.out.println(lineStr);
		}
	}
	
}
