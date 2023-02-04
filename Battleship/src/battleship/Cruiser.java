package battleship;

public class Cruiser extends Ship{

	static final int CRUISERLENGTH = 3;
	
	public Cruiser() {
		super(Cruiser.CRUISERLENGTH);
	}
	
	/**
	 * Returns that the ship is cruiser
	 */
	@Override
	public String getShipType() {
		return "cruiser";
	}
}
