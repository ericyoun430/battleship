package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);

		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		//Test if bow area is occupied once placed.
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		//Test placing a bad spot ship
		Ship submarine2 = new Submarine();
		if (submarine2.okToPlaceShipAt(0, 1, false, ocean)) {
			submarine2.placeShipAt(0, 1, false, ocean);
		}
		assertFalse(ocean.isOccupied(0, 1));
		
		//Test placing ship in a good spot and seeing if occupied
		Ship submarine3 = new Submarine();
		submarine3.placeShipAt(0, 2, false, ocean);
		assertTrue(ocean.isOccupied(0, 2));
	}

	@Test
	void testShootAt() {
		
		//Basic destroyer shoot at placement
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		//Test shooting a sunk ship; second shoot at should be false because it is already sunk
		Submarine submarine = new Submarine();
		submarine.placeShipAt(0,0,true,ocean);
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertFalse(ocean.shootAt(0, 0));
		
		//Testing true when you hit a spot on a ship even a repeat when not sunk
		Cruiser cruiser = new Cruiser();
		cruiser.placeShipAt(8, 8, false, ocean);
		assertTrue(ocean.shootAt(8, 8));
		assertTrue(ocean.shootAt(8, 8));
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//Even when shooting a sunk ship, shots fired should increase
		Ship submarine1 = new Submarine();
		submarine1.placeShipAt(3,3,false,ocean);
		assertTrue(ocean.shootAt(3, 3));
		assertEquals(7, ocean.getShotsFired());
		assertFalse(ocean.shootAt(3, 3));
		assertEquals(8, ocean.getShotsFired());
		
		//Test shooting battleship
		Ship battleship = new Battleship();
		battleship.placeShipAt(9, 8, true, ocean);
		assertTrue(ocean.shootAt(9, 8));
		assertEquals(9, ocean.getShotsFired());
		assertTrue(ocean.shootAt(9, 7));
		assertEquals(10, ocean.getShotsFired());
		assertTrue(ocean.shootAt(9, 6));
		assertEquals(11, ocean.getShotsFired());
		assertTrue(ocean.shootAt(9, 5));
		assertEquals(12, ocean.getShotsFired());
	}

	@Test
	void testGetHitCount() {
		
		//Test hitting a ship
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//Test hitting and sinking a submarine
		Submarine submarine = new Submarine();
		submarine.placeShipAt(0, 0, true, ocean);
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());		
		assertEquals(2,ocean.getHitCount());
		
		//Test sinking and shooting after it is sunk, it should not increase
		//Also testing hitting the same spot before it is sunk should increase
		Cruiser cruiser = new Cruiser();
		cruiser.placeShipAt(8, 8, true, ocean);
		assertTrue(ocean.shootAt(8, 8));
		assertFalse(cruiser.isSunk());
		assertEquals(3, ocean.getHitCount());
		assertTrue(ocean.shootAt(8, 8));
		assertFalse(cruiser.isSunk());
		assertEquals(4, ocean.getHitCount());
		assertTrue(ocean.shootAt(8, 7));
		assertFalse(cruiser.isSunk());
		assertEquals(5, ocean.getHitCount());
		assertTrue(ocean.shootAt(8, 6));
		assertTrue(cruiser.isSunk());
		assertEquals(6, ocean.getHitCount());
		assertFalse(ocean.shootAt(8, 6));
		assertEquals(6, ocean.getHitCount());
		
	}
	
	@Test
	void testGetShipsSunk() {
		
		//Testing that ships sunk doesn't increase when not sunk
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//Testing incrementing sunk when ship sinks
		Submarine submarine = new Submarine();
		submarine.placeShipAt(0, 0, true, ocean);
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		//Testing shooting the same spot does not increment sunk after it is sunk
		Destroyer destroyer1 = new Destroyer();
		destroyer1.placeShipAt(8, 8, true, ocean);
		assertTrue(ocean.shootAt(8, 8));
		assertTrue(ocean.shootAt(8, 7));
		assertTrue(destroyer1.isSunk());
		assertEquals(4, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		assertFalse(ocean.shootAt(8, 8));
		assertEquals(4, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
	}

	@Test
	void testGetShipArray() {
		//Testing size of ocean and empty
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//Place battleship
		Battleship battleship = new Battleship();
		battleship.placeShipAt(5, 5, true, ocean);
		assertEquals("battleship",shipArray[5][5].getShipType());
		assertEquals("battleship",shipArray[5][4].getShipType());
		assertEquals("battleship",shipArray[5][3].getShipType());
		assertEquals("battleship",shipArray[5][2].getShipType());

		//Place Destroyer
		Destroyer destroyer = new Destroyer();
		destroyer.placeShipAt(2, 0, false, ocean);
		assertEquals("destroyer",shipArray[2][0].getShipType());
		assertEquals("destroyer",shipArray[1][0].getShipType());
		assertEquals("empty",shipArray[0][0].getShipType());
	}

}
