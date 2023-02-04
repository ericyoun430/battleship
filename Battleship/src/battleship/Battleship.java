package battleship;

public class Battleship extends Ship{
	
	static final int BATTLESHIPLENGTH = 4;

	/**
	 * Constructor that sets length variable to 4
	 */
	public Battleship() {
		super(Battleship.BATTLESHIPLENGTH);
	}
	
	/**
	 * Returns that the ship is battleship
	 */
	@Override
	public String getShipType() {
		return "battleship";
	}
}
