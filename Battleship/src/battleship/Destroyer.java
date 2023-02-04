package battleship;

public class Destroyer extends Ship{

	static final int DESTROYERLENGTH = 2;
	
	public Destroyer() {
		super(Destroyer.DESTROYERLENGTH);
	}
	
	/**
	 * Returns that the ship is cruiser
	 */
	@Override
	public String getShipType() {
		return "destroyer";
	}
}
