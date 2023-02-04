package battleship;

public class EmptySea extends Ship{
	
	/**
	 * Constructor setting length of ship to 1
	 */
	public EmptySea() {
		super(1);
	}
	
	/**
	 * This method overrides shootAt(int row, int column) that 
	 * is inherited from Ship, and always returns false to 
	 * indicate that nothing was hit. However, if it is "hit" we
	 * change the value in hit array to true.
	 */
	@Override
	boolean shootAt(int row, int column) {
		if (this.getBowRow() == row && this.getBowColumn() == column) {
			this.getHit()[0] = true;
		}
		return false;
	}
	
	/**
	 * This method overrides isSunk() that is inherited from Ship, 
	 * and always returns false to indicate that you didn’t sink anything
	 */
	@Override
	boolean isSunk() {
		return false;
	}
	
	/**
	 * Returns the single-character “-“ String to use in the Ocean’s 
	 * print method. (Note, this is the character to be displayed if a 
	 * shot has been fired, but nothing has been hit.)
	 */
	@Override
	public String toString() {
		return "-";
	}
	
	/**
	 * This method just returns the string “empty”
	 */
	@Override
	public String getShipType() {
		return "empty";
	}
}
