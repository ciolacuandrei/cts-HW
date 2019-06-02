package ro.ase.cts.classes;

import java.util.ArrayList;

import ro.ase.cts.exceptions.InvalidCompanyNameException;
import ro.ase.cts.exceptions.MathTestsException;
import ro.ase.cts.exceptions.NumberOfSeatsException;
import ro.ase.cts.exceptions.TicketPriceException;

public class FlyingCompany {
	private String companyName;
	private int noSeats;
	private ArrayList<Integer> ticketPrice;
	
	public static final int MAX_NAME_LETTERS = 60;
	public static final int MIN_NAME_LETTERS = 2;
	public static final int MIN_SEAT_NUMBER = 50;
	public static final int MAX_SEAT_NUMBER = 300;

	public FlyingCompany() {
		ticketPrice=new ArrayList<>();
	}
	
	public FlyingCompany(String name, int seats, ArrayList<Integer> price) {
		this.companyName = name;
		this.noSeats = seats;
		this.ticketPrice = new ArrayList<>();
		
		for (Integer priceTicket : ticketPrice) {
			this.ticketPrice.add(priceTicket);
		}
}
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String name) throws InvalidCompanyNameException {
		if (name.length() < MIN_NAME_LETTERS) {
			throw new InvalidCompanyNameException("The name is too short, it must be between " + MIN_NAME_LETTERS + " and " + MAX_NAME_LETTERS);
		}
		
		if (name.length() > MAX_NAME_LETTERS) {
			throw new InvalidCompanyNameException("The name is too long, it must be between " + MIN_NAME_LETTERS + " and " + MAX_NAME_LETTERS);
		
		}
		
		this.companyName = name;
	}

	public int getNoSeats() {
		return this.noSeats;
	}

	public void setNoSeats(Integer noSeats) throws NumberOfSeatsException {
		if(noSeats == null) {
			throw new NumberOfSeatsException(companyName + "has a null number of seats!");
		}
		
		if(noSeats < MIN_SEAT_NUMBER || noSeats > MAX_SEAT_NUMBER) {
			throw new NumberOfSeatsException(companyName + "must have a number of seats between " + MIN_SEAT_NUMBER + " and " + MAX_SEAT_NUMBER);
		}
		
		this.noSeats = noSeats;
	}

	public ArrayList<Integer> getTicketPrices() {
		return this.ticketPrice;
	}

	public int getTicketPrice(int i) {
		return ticketPrice.get(i);
	}
	
	public void setTicketPrice(ArrayList<Integer> ticketPrice) {
		this.ticketPrice = ticketPrice;
}
	
	public void addTicket(int price) throws TicketPriceException {
		if (price < 50 || price > 500) {
			throw new TicketPriceException("The ticket price must be between 50 and 500!");
		}
		
		ticketPrice.add(price);
		
	}
	
	public double avgPrice() {
		double sum = 0;
		
		for(Integer price : ticketPrice) {
			sum=sum + price;
		}
		
		return sum/ticketPrice.size();
	}

	public int maxSeatNumber(int[] values) throws MathTestsException {
		if(values.length < 0 || values.length > MAX_SEAT_NUMBER)
			throw new MathTestsException("The number of values exceeds the given interval");
		
		int max = values[0];
		
		for(int i=1;i<values.length;i++) {
			if(max < values[i]) {
				max = values[i];
			}
		}
		
		return max;
}
}
