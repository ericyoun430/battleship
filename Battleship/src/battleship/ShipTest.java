package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		//Test battleship length
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//Test cruiser length
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		
		//Test submarine length
		ship = new Submarine();
		assertEquals(1, ship.getLength());
		
		//Test destroyer length
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
	}

	@Test
	void testGetBowRow() {
		//Test horizontal battleship bow
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//Test vertical battleship bow
		Ship battleship1 = new Battleship();
		int row1 = 5;
		int column1 = 4;
		boolean horizontal1 = false;
		battleship1.placeShipAt(row1, column1, horizontal1, ocean);
		assertEquals(row1, battleship1.getBowRow());
		
		//Test cruiser horizontal
		Ship cruiser = new Battleship();
		int row2 = 8;
		int column2 = 9;
		boolean horizontal2 = true;
		cruiser.placeShipAt(row2, column2, horizontal2, ocean);
		assertEquals(row2, cruiser.getBowRow());
		
		//Test cruiser vertical
		Ship cruiser1 = new Battleship();
		int row3 = 4;
		int column3 = 9;
		boolean horizontal3 = false;
		cruiser1.placeShipAt(row3, column3, horizontal3, ocean);
		assertEquals(row3, cruiser1.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		//Testing basic horizontal placement
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//Test vertical battleship bow
		Ship battleship1 = new Battleship();
		int row1 = 5;
		int column1 = 4;
		boolean horizontal1 = false;
		battleship1.placeShipAt(row1, column1, horizontal1, ocean);
		assertEquals(column1, battleship1.getBowColumn());
		
		//Test cruiser horizontal
		Ship cruiser = new Battleship();
		int row2 = 8;
		int column2 = 9;
		boolean horizontal2 = true;
		cruiser.placeShipAt(row2, column2, horizontal2, ocean);
		assertEquals(column2, cruiser.getBowColumn());
		
		//Test cruiser vertical
		Ship cruiser1 = new Battleship();
		int row3 = 4;
		int column3 = 9;
		boolean horizontal3 = false;
		cruiser1.placeShipAt(row3, column3, horizontal3, ocean);
		assertEquals(column3, cruiser1.getBowColumn());
	}

	@Test
	void testGetHit() {
		//Testing creation of hit array
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//Testing ship getting hit; first value in hit array should be hit because 
		//that is bow location
		ship = new Battleship();
		ship.placeShipAt(5, 5, true, ocean);
		ocean.shootAt(5, 5);
		assertTrue(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);
		
		//Testing ship getting hit with 2 shots and 1 shot missing
		ship = new Cruiser();
		ship.placeShipAt(8, 5, true, ocean);
		ocean.shootAt(8, 5);
		ocean.shootAt(8, 3);
		ocean.shootAt(7, 7);
		assertTrue(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertTrue(ship.getHit()[2]);
	}
	@Test
	void testGetShipType() {
		//Testing battleship type
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());

		//Testing cruiser type
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		
		//Testing destroyer type
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		
		//Testing submarine type
		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
	}
	
	@Test
	void testIsHorizontal() {
		//Testing battleship horizontal
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//Testing battleship vertical
		Ship battleship1 = new Battleship();
		int row1 = 4;
		int column1 = 4;
		boolean horizontal1 = false;
		battleship1.placeShipAt(row1, column1, horizontal1, ocean);
		assertFalse(battleship1.isHorizontal());
		
		//Testing cruiser vertical
		Cruiser cruiser = new Cruiser();
		int row2 = 4;
		int column2 = 4;
		boolean horizontal2 = false;
		cruiser.placeShipAt(row2, column2, horizontal2, ocean);
		assertFalse(cruiser.isHorizontal());
	}
	
	@Test
	void testSetBowRow() {
		//Setting bow row for horizontal battleship
		Ship battleship = new Battleship();
		int row = 0;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());

		//Setting bow row for cruiser
		Ship cruiser = new Cruiser();
		int row1 = 4;
		cruiser.setBowRow(row1);
		assertEquals(row1, cruiser.getBowRow());
		
		//Setting bow row for destroyer
		Ship destroyer = new Destroyer();
		int row2 = 4;
		destroyer.setBowRow(row2);
		assertEquals(row2, destroyer.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		//Setting bow col for battleship
		Ship battleship = new Battleship();
		int column = 4;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//Setting bow col for cruiser
		Ship cruiser = new Cruiser();
		int column1 = 4;
		cruiser.setBowColumn(column1);
		assertEquals(column1, cruiser.getBowColumn());
		
		//Setting bow col for destroyer
		Ship destroyer = new Destroyer();
		int column2 = 4;
		destroyer.setBowColumn(column2);
		assertEquals(column2, destroyer.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		//Testing battleship horizontal
		Ship battleship = new Battleship();
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//Testing battleship vertical
		Ship battleship1 = new Battleship();
		boolean horizontal1 = false;
		battleship1.setHorizontal(horizontal1);
		assertFalse(battleship1.isHorizontal());
		
		//Testing cruiser vertical
		Ship cruiser = new Cruiser();
		boolean horizontal2 = false;
		cruiser.setHorizontal(horizontal2);
		assertFalse(cruiser.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		Ship test = new Battleship();
		Ship test1 = new Destroyer();
		System.out.println(test.okToPlaceShipAt(8, 7, false, ocean));
		test.placeShipAt(8, 7, false, ocean);
		
		
		System.out.println(test1.okToPlaceShipAt(9, 8, true, ocean));
		System.out.println(ocean.isOccupied(8, 7));
		System.out.println(ocean.isOccupied(9, 8));
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		
		assertTrue(ok, "OK to place ship here.");
		
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		//test placing a ship right on top of each other complete overlap
		Battleship battleship1 = new Battleship();
		int row1 = 0;
		int column1 = 4;
		boolean horizontal1 = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row1, column1, horizontal1, ocean);
		
		assertFalse(ok1, "Not ok to place ship here.");
		
		//test placing a ship right on top of each other partial overlap
		Battleship battleship2 = new Battleship();
		int row2 = 0;
		int column2 = 6;
		boolean horizontal2 = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row2, column2, horizontal2, ocean);
		
		assertFalse(ok2, "Not ok to place ship here.");
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship touching from the bottom side
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//test touching vertically on the right
		Battleship battleship3 = new Battleship();
		row = 0;
		column = 5;
		horizontal = false;
		boolean ok3 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3);
		
		//test touching from the top side 
		Battleship battleship4 = new Battleship();
		row = 0;
		column = 3;
		horizontal = true;
		boolean ok4 = battleship4.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4);
		
		//test diagonal
		Battleship battleship5 = new Battleship();
		row = 1;
		column = 8;
		horizontal = true;
		boolean ok5 = battleship5.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok5);
	}

	@Test
	void testPlaceShipAt() {
		
		//Test placing a battleship
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//Test placing cruiser and that it only takes up the 3 spaces it should.
		Cruiser cruiser = new Cruiser();
		int row1 = 1;
		int column1 = 4;
		boolean horizontal1 = true;
		cruiser.placeShipAt(row1, column1, horizontal1, ocean);
		assertEquals(row1,cruiser.getBowRow());
		assertEquals(column1,cruiser.getBowColumn());
		assertTrue(cruiser.isHorizontal());
		assertEquals("empty", ocean.getShipArray()[1][5].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[1][4]);
		assertEquals(cruiser, ocean.getShipArray()[1][3]);
		assertEquals(cruiser, ocean.getShipArray()[1][2]);
		assertEquals("empty", ocean.getShipArray()[1][1].getShipType());
		
		//Test placing a vertical ship
		Destroyer destroyer = new Destroyer();
		int row2 = 8;
		int column2 = 4;
		boolean horizontal2 = false;
		destroyer.placeShipAt(row2, column2, horizontal2, ocean);
		assertEquals(row2,destroyer.getBowRow());
		assertEquals(column2,destroyer.getBowColumn());
		assertFalse(destroyer.isHorizontal());
		assertEquals("empty",ocean.getShipArray()[9][4].getShipType());
		assertEquals(destroyer, ocean.getShipArray()[8][4]);
		assertEquals(destroyer, ocean.getShipArray()[7][4]);
		assertEquals("empty",ocean.getShipArray()[6][4].getShipType());
		
		//Test placing a vertical ship
		Destroyer destroyer2 = new Destroyer();
		int row3 = 1;
		int column3 = 8;
		boolean horizontal3 = false;
		destroyer2.placeShipAt(row3, column3, horizontal3, ocean);
		assertEquals(row3,destroyer2.getBowRow());
		assertEquals(column3,destroyer2.getBowColumn());
		assertFalse(destroyer2.isHorizontal());
		assertEquals("empty",ocean.getShipArray()[2][8].getShipType());
		assertEquals(destroyer2, ocean.getShipArray()[1][8]);
		assertEquals(destroyer2, ocean.getShipArray()[0][8]);
	}

	@Test
	void testShootAt() {
		
		//Base hit array before shot at; test empty sea shots
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		assertFalse(battleship.shootAt(2, 9));
		assertFalse(battleship.shootAt(0, 5));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//Testing shooting at the ship at bow location
		assertTrue(battleship.shootAt(row, column));
		boolean[] hitArray1 = {true, false, false, false};
		assertArrayEquals(hitArray1, battleship.getHit());
		
		//Testing shooting at the ship at other locations
		assertTrue(battleship.shootAt(0, 8));
		boolean[] hitArray2 = {true, true, false, false};
		assertArrayEquals(hitArray2, battleship.getHit());
		
		//Testing shooting at an already hit part - should still print true
		assertTrue(battleship.shootAt(0, 8));
		boolean[] hitArray3 = {true, true, false, false};
		assertArrayEquals(hitArray3, battleship.getHit());
		
		//Testing shooting the entire ship
		assertTrue(battleship.shootAt(0, 7));
		assertTrue(battleship.shootAt(0, 6));
		boolean[] hitArray4 = {true, true, true, true};
		assertArrayEquals(hitArray4, battleship.getHit());
		
		//Testing shooting at a sunk ship
		assertFalse(battleship.shootAt(0, 9));
		assertFalse(battleship.shootAt(0, 8));
		assertFalse(battleship.shootAt(0, 7));
		assertFalse(battleship.shootAt(0, 6));
	}
	
	@Test
	void testIsSunk() {
		
		//Testing missed shot - shouldn't be sunk
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//Testing a sink on submarine
		Ship submarine1 = new Submarine();
		int row1 = 3;
		int column1 = 4;
		boolean horizontal1 = true;
		submarine1.placeShipAt(row1, column1, horizontal1, ocean);
		
		assertFalse(submarine1.isSunk());
		assertTrue(submarine1.shootAt(3, 4));
		assertTrue(submarine1.isSunk());
		
		//Testing a sink on battleship
		Battleship battleship = new Battleship();
		battleship.placeShipAt(0, 3, true, ocean);
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(0, 0));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(0, 1));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(0, 2));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(0, 3));
		assertTrue(battleship.isSunk());
		;
	}

	@Test
	void testToString() {
		//If the ship isn't sunk, print x
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//Testing a sink on submarine
		Submarine submarine = new Submarine();
		assertEquals("x",submarine.toString());
		submarine.placeShipAt(0, 0, true, ocean);
		submarine.shootAt(0, 0);
		assertEquals("s",submarine.toString());
		
		//Testing a sink on destroyer
		Destroyer destroyer = new Destroyer();
		assertEquals("x",destroyer.toString());
		destroyer.placeShipAt(0, 8, true, ocean);
		destroyer.shootAt(0, 8);
		assertEquals("x",destroyer.toString());
		destroyer.shootAt(0, 7);
		assertEquals("s",submarine.toString());
		
	}

}
