package ro.ase.cts.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.ase.cts.classes.FlyingCompany;
import ro.ase.cts.exceptions.InvalidCompanyNameException;
import ro.ase.cts.exceptions.MathTestsException;
import ro.ase.cts.exceptions.NumberOfSeatsException;
import ro.ase.cts.exceptions.TicketPriceException;

class FlyingCompanyTest {
	FlyingCompany fc;
	
	@Before
	public void setUp() {
		this.fc = new FlyingCompany();
	}
	
	@After
	public void tearDown() {
		this.fc = null;
	}

	@Test
	public void testCompanyName() throws InvalidCompanyNameException {
		fc.setCompanyName("BlueAir");;
		assertEquals("BlueAir",fc.getCompanyName());
	}
	
	@Test
	public void testSetNameRangeBelowLimit() throws InvalidCompanyNameException {
		fc.setCompanyName("a");
	}
	
	@Test
	public void testSetNameRangeAboveLimit() throws InvalidCompanyNameException {
		String name = "                                                                                                                 "
				+ "                                                                                                                     "
				+ "                                                                                                                     "
				+ "                                                                                                                     "
				+ "                                                                 ";
		fc.setCompanyName(name);
	}
	@Test
	public void testMaxInAscOrder() {
		int[] values = {1,2,3,4,5,6,7,8,9};
		int expected = 9;
		
		try {
			
			int actual = fc.maxSeatNumber(values);
			assertEquals(expected, actual);
			
		} catch (MathTestsException e) {
			fail("Exception");
		}
	}
	
	@Test
	public void testMaxInDescOrder() {
		int[] values = {9,8,7,6,5,4,3,2,1};
		int expected = 9;
		
		try {	
			int actual = fc.maxSeatNumber(values);
			assertEquals(expected, actual);
		} catch (MathTestsException e) {
			fail("Exception");
		}
	}
	
	@Test
	public void testAddNormalNoSeats() throws NumberOfSeatsException  {
		fc.setNoSeats(100);
		assertEquals(100, fc.getNoSeats());
	}
	
	@Test
	public void testNegativeNoSeats() throws NumberOfSeatsException {
		int noSeats = -1;
		fc.setNoSeats(noSeats);
	}
	
	@Test
	public void testNullNoSeats() throws NumberOfSeatsException {
		Integer noSeats = null;
		fc.setNoSeats(noSeats);
}
	@Test
	public void testSetLowerBoundaryAsNoSeats() {
		int noSeats = fc.MIN_SEAT_NUMBER;
		
		try {
			fc.setNoSeats(noSeats);
			int actualNoSeats = fc.getNoSeats();
			assertEquals(noSeats, actualNoSeats);
		} catch (NumberOfSeatsException e) {
			fail("Exception not expected");
		}
	}
	
	@Test
	public void testSetUpperBoundaryAsNoSeats() {
		int noSeats = fc.MAX_SEAT_NUMBER;
		
		try {
			fc.setNoSeats(noSeats);
			int actualNoSeats = fc.getNoSeats();
			assertEquals(noSeats, actualNoSeats);
		} catch (NumberOfSeatsException e) {
			fail("Exception not expected");
		}
	}
	
	@Test
	public void testCardinality0() {
		int[] values=null;
		
		try{
			fc.maxSeatNumber(values);
			fail("Doesn't throw an exception");
		}
		catch(MathTestsException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCardinality1() {
		int[] values = {1};
		int expected = 1;
		try {
			int actual = fc.maxSeatNumber(values);
			assertEquals(expected, actual);
		} catch (MathTestsException e) {
			e.printStackTrace();
			fail("Test Failed!");
		}
	}
	
	@Test
	public void testCardinalityN() {
		int[] values = {12,15,2,4,5,9,4,2,3,5};
		int expectedMin = 15;
		try {
			int actualMin = fc.maxSeatNumber(values);
			assertEquals(expectedMin, actualMin);
		} catch (MathTestsException e) {
			e.printStackTrace();
			fail("Test Failed!");
		}
	}
	
	@Test
	public void testAvgTime() throws TicketPriceException  {
		Random rand = new Random();
		
		for (int i = 0; i < 100000; i++) {
			fc.addTicket((rand.nextInt(9) + 1));
		}

		long start = System.currentTimeMillis();
		fc.avgPrice();
		long finish = System.currentTimeMillis();

		if ((finish - start) < 1000) {
			assertTrue(true);
		} 
		else {
			fail("Average time exceeds limits!");
		}
	}
	
	@Test
	public void testInverse() throws TicketPriceException{
		fc.addTicket(6);
		assertEquals(6, fc.getTicketPrice(0));
	}
	
	@Test
	public void testCrossCheck() throws TicketPriceException {
		fc.addTicket(60);
		fc.addTicket(100);
		fc.addTicket(80);
		fc.addTicket(200);

		float expected = (float) fc.avgPrice();
		float actual=0;
		for(int i=0;i<4;i++) {
			actual = actual + fc.getTicketPrice(i);
		}
		
		 actual = actual / 4;
		assertEquals(expected, actual, 0.0001);
	}
}
