package ro.ase.cts.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ro.ase.cts.models.Product;
import ro.ase.java.exception.InvalidDiscountException;
import ro.ase.java.exception.NegativePriceException;

public class ProductTest {
	private Product p;
	private Product pr;
	
	@Before
	public void setUp() {
		System.out.println("I am called");
		p = new Product("Air Pods", 800);
		pr=new Product();
	}
	
	@Test
	public void testDiscountMethod() {
		try {
			p.applyDiscount(0.1);
			assertEquals(720, p.getProductPrice(), 0.001);
		} catch (InvalidDiscountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNegativeDiscount() {
		try {
			p.applyDiscount(-0.2);
			fail("Metoda applyDiscount nu ar trebui sa accepte discount negative");
		} catch(InvalidDiscountException ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testGreaterDiscount() {
		try {
			p.applyDiscount(1.2);
			fail("Metoda applyDiscount nu ar trebui sa accepte discount mai mare de 100%");
		} catch (InvalidDiscountException ex) {
			ex.printStackTrace();
		}
	}
	
	// Homework 1- 5 tests(other 5 are in OrderTest)
	@Test
	public void testNegativePrice() {
		try {
			pr.setProductName("ABS");
			pr.setProductPrice(-100);
			fail("The price cannot be negative");
		} catch (NegativePriceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testProductName() {
		pr.setProductName(" ");
			if(pr.getProductName()==" ") {
			fail("The product must have a name");
			}
	}
	
	@Test
	public void testMultipleDiscounts() {
		try {
			p.applyDiscount(0.2);
			p.applyDiscount(0.2);
			fail("You cannot apply a discount on an already discounted product");
		} catch(InvalidDiscountException ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testSetProductPrice() {
			try {
				p.setProductPrice(200);
				assertEquals(200, p.getProductPrice(), 0.0001);
			} catch (NegativePriceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	public void testSameProductsNotMatching() {
		pr.setProductName("Air Pods");
		try {
			pr.setProductPrice(800);
		} catch (NegativePriceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pr.applyDiscount(0.25);
		} catch (InvalidDiscountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(p.getProductPrice(), pr.getProductPrice(), 0.0001);
		fail("Two products that have the same name must have the same discount!");
	}
}
