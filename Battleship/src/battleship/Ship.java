package battleship;

abstract class Ship {
	
	
	//The row that contains the bow (front part of the ship)
	private int bowRow;
	
	//The column that contains the bow (front part of the ship)
	private int bowColumn;
	
	//The length of the ship
	private int length;
	
	//A boolean that represnts whether the ship is going to be placed horizontally
	//or vertically
	private boolean horizontal;
	
	//An array of booleans that indicate whether that part of the ship has been hit or not
	private boolean[] hit;
	
	/**
	 * This constructor sets the length of the particular ship and initializes
	 * the hit array based on that length
	 * @param length of the ship
	 */
	public Ship(int length) {
		this.length = length;
		this.hit = new boolean[length];
	}
	
	/**
	 * Returns the ship length
	 * @return ship length
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Returns the row corresponding to the position of the bow
	 * @return row corresponding to the position of the bow
	 */
	public int getBowRow() {
		return this.bowRow;
	}
	
	/**
	 * Returns the bow column location
	 * @return bow column location
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}
	
	/**
	 * Returns the hit array
	 * @return hit array
	 */
	public boolean[] getHit() {
		return this.hit;
	}
	
	/**
	 * Returns whether the ship is horizontal or not
	 * @return whether the ship is horizontal or now
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}
	
	/**
	 * Sets the value of bowRow
	 * @param row that the bow should be set
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	/**
	 * Sets the value of BowColumn
	 * @param column that the bow should be set
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * Sets the value of the instance variable horizontal
	 * @param horizontal yes or no ship is horizontal
	 */
	public void setHorizontal (boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
	 * Returns the type of ship as a String
	 * @return the type of ship as a String
	 */
	public abstract String getShipType();
	
	/**
	 * Based on the given row, column, and orientation, returns true if it is
	 * okay to put a ship of this length with its bow in this location; false otherwise.
	 * The ship must not overlap another ship, or touch another ship (vertically, horizontally,
	 * or diagonally), and it must not "stick out" beyond the array. Does not actually change
	 * either the ship or the Ocean - it just says if it is legal to do so.
	 * @param row of the ocean
	 * @param column of the ocean 
	 * @param horizontal boolean if ship is horizontal
	 * @param ocean shows the position of ships
	 * @return
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		int length = this.getLength();
		
		//If initial placement is taken then return false
		if (ocean.isOccupied(row,column)) {
			return false;
		}
		
		
		
		//If the ship is vertical then we check all necessary spaces
		if (horizontal == false) {
			
			//Trying to prove that ship can not be placed in up position
			boolean up = true;
			
			//Test if the location is blocked by bounds
			if (row+1 - length <= -1) {
				return false;
				
			//If location itself isn't blocked we continue to check surroundings blocks
			} else {
				//Testing when bow is on the bottom and thus is "up"
				//Loop through a rectangle that surrounds the length of the ship
				//meaning the rectangle is of length: 3 and width: length + 2.
				for (int i = row - length; i < row+2; i++) {
					for (int j = column-1; j < column+2; j++) {
						int curRow = i;
						int curCol = j;
						
						//If the current position is out of bounds, continue as being
						//next to the boundary is okay
						if (curRow < 0 || curRow >= 10 || curCol >= 10 || curCol < 0) {
							continue;
						} else {
							//If the current position is in the bounds and is occupied, this
							//is not a valid orientation of the ship
							if (ocean.isOccupied(curRow, curCol)) {
								return false;
							}
						}
					}
				}
			}

			//If either left or right are still true, then all squares around at least
			// one of the horizontal orientations are unoccupied.
			return up;
		}
		//Trying to prove that up and down cases don't work
		boolean right = true;
		
		//Test if the location is blocked by bounds
		if (column+1 - length <= -1) {
			return false;
			
		//If location itself isn't blocked we continue to check surroundings blocks
		} else {
			//Testing when the bow is at the right
			//Loop through a rectangle that surrounds the length of the ship
			//meaning the rectangle is of length: length+2 and width: 3
			for (int i = row-1; i < row+2; i++) {
				for (int j = column-length; j < column+2; j++) {
					int curRow = i;
					int curCol = j;
					
					
					//If the current position is out of bounds, continue
					if (curRow < 0 || curRow >= 10 || curCol >= 10 || curCol < 0) {
						continue;
					} else {
						//If the current position is in the bounds and is occupied, this
						//is not a valid orientation of the ship
						if (ocean.isOccupied(curRow, curCol)) {
							return false;
						}
					}
				}
			}
		}
		//If either right is true, then it is possible to place here. Otherwise, 
		//it is not possible to place here.
		return right;
	}
	
	/**
	 * Puts the ship in the ocean. This involves giving values to the bowRow, bowColumn, and
	 *  horizontal instance variables in the ship, and it also involves putting a reference to 
	 *  the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean object. 
	 *  (Note: This will be as many as four identical references; you can’t refer to a ”part” of a ship, only to the whole ship.)
	 * @param row in the ocean
	 * @param column in the ocean
	 * @param horizontal true if horizontal, false if vertical
	 * @param ocean object that contains grid
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		
		int length = this.getLength();
		
		//If horizontal place the ship in the correct horizontal spaces
		if (horizontal) {
			for (int i = 0; i < length; i++) {
				ocean.getShipArray()[row][column-i] = this;
			}
		//If vertical place the ship in the correct vertical spaces
		} else {
			for (int k = 0; k < length; k++) {
				ocean.getShipArray()[row-k][column] = this;
			}
		}
	}
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, 
	 * mark that part of the ship as “hit” (in the hit array, index 0 indicates the bow) and return 
	 * true; otherwise return false.
	 * @param row in the ocean
	 * @param column in the ocean
	 * @return boolean true if it hits and false otherwise
	 */
	boolean shootAt(int row, int column) {
		
		//If the ship is sunk return false automatically
		if (this.isSunk()) {
			return false;
		}
		
		int length = this.getLength();
		int bowRow = this.getBowRow();
		int bowCol = this.getBowColumn();
		boolean horizontal = this.isHorizontal();
		boolean[] hitArray = this.getHit();
		
		
		if (horizontal) {
			//Index in the hit array, where index 0 is the bow
			int hitVal = bowCol - column;
			
			//If the column is in the bow column, and hit is between the two ends of the ship,
			//It is in the right area. Overwrite value at the hit val to true whether or not it has been hit there
			if (row == bowRow && (column <= bowCol && column > bowCol - length)) {
				hitArray[hitVal] = true;
				return true;
			//If not within this range then miss
			} else {
				return false;
			}
		} else {
			//Index in the hit array, where index 0 is the bow
			int hitVal = bowRow-row;
			
			//If the row is in the bow row, and the hit is between the two ends of the ship, it is in the right area.
			//If the ship has been hit there then return true, true if it hasn't
			if (column == bowColumn && (row <= bowRow && row > bowRow - length)) {
				hitArray[hitVal] = true;
				return true;
				
			//If not in this range, then miss
			} else {
				return false;
			}
		}
	}
	
	/**
	 * Returns true if every part of the ship has been hit, false otherwise
	 * @return boolean true if ship is sunk, false otherwise
	 */
	boolean isSunk() {
		boolean[] hitArray = this.getHit();
		
		//Loop through hit array and if any part of the ship hasn't been hit 
		//return false. If every part has been hit then return true
		for (int i = 0; i < hitArray.length; i++) {
			if (hitArray[i] == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns a single-character String to use in the Ocean’s print 
	 * method. This method should return ”s” if the ship has been sunk 
	 * and ”x” if it has not been sunk. This method can be used to print
	 *  out locations in the ocean that have been shot at; it should not
	 *   be used to print locations that have not been shot at. 
	 *   Since toString behaves exactly the same for all ship types, 
	 *   it is placed here in the Ship class.
	 */
	@Override
	public String toString() {
		if (this.isSunk()) {
			return "s";
		} else {
			return "x";
		}
	}
}
