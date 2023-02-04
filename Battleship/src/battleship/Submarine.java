package battleship;

public class Submarine extends Ship{

	static final int SUBMARINELENGTH = 1;
	
	public Submarine() {
		super(Submarine.SUBMARINELENGTH);
	}
	
	/**
	 * Returns that the ship is cruiser
	 */
	@Override
	public String getShipType() {
		return "submarine";
	}
}
