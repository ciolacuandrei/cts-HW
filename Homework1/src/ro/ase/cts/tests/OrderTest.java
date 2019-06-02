package ro.ase.cts.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.ase.cts.models.Order;
import ro.ase.cts.models.Product;
import ro.ase.cts.utils.DeliveryMethod;
import ro.ase.cts.utils.PaymentMethod;
import ro.ase.java.exception.DeliveryMethodException;
import ro.ase.java.exception.EmptyOrderException;
import ro.ase.java.exception.PaymentMethodException;

public class OrderTest {
	private Order testOrder;
	
	
	@Before
	public void setUp() {
		this.testOrder = new Order();
	}
	
	@After
	public void tearDown() {
		this.testOrder.clearBasket();
	}
	
	@Test
	public void testBasketInitialization() {
		assertTrue(this.testOrder.getBasket().isEmpty());
	}
	
	
	// Homework 1- 5 tests(other 5 are in ProductTest)
	@Test
	public void testOrderBasketSize() {
		try {
			testOrder.setDeliveryMethod(DeliveryMethod.CURIER);
			testOrder.setPaymentMethod(PaymentMethod.CARD_ONLINE);
			testOrder.finishOrder();
			assertEquals(0, testOrder.getBasket().size());
			fail("Basket size must be >0");
		}catch(EmptyOrderException e) {
			e.printStackTrace();
		}catch(DeliveryMethodException e) {
			e.printStackTrace();
		}catch(PaymentMethodException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOrderBasketDeliveryMethod() {
		try {
			Product p= new Product("ABS", 100);
			testOrder.addNewProductToOrder(p);
			testOrder.setDeliveryMethod(null);
			testOrder.setPaymentMethod(PaymentMethod.CARD_ONLINE);
			testOrder.finishOrder();
			assertTrue(testOrder.getDeliveryMethod()!=null);
			fail("The delivery method must not be null");
		}catch(EmptyOrderException e) {
			e.printStackTrace();
		}catch(DeliveryMethodException e) {
			e.printStackTrace();
		}catch(PaymentMethodException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOrderBasketPaymentMethod() {
		try {
			Product p= new Product("ABS", 100);
			testOrder.addNewProductToOrder(p);
			testOrder.setDeliveryMethod(DeliveryMethod.CURIER);
			testOrder.setPaymentMethod(null);
			testOrder.finishOrder();
			assertTrue(testOrder.getPaymentMethod()!=null);
			fail("The payment method must not be null");
		}catch(EmptyOrderException e) {
			e.printStackTrace();
		}catch(DeliveryMethodException e) {
			e.printStackTrace();
		}catch(PaymentMethodException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNegativeTotalPrice() {
		try {
			Product p= new Product("ABS", -100);
			testOrder.addNewProductToOrder(p);
			testOrder.setDeliveryMethod(DeliveryMethod.CURIER);
			testOrder.setPaymentMethod(PaymentMethod.CARD_ONLINE);
			double totalPrice = 0;
			for(Product pr : testOrder.getBasket()) {
				totalPrice += pr.getProductPrice();
			}
			if(totalPrice<0) {
				fail("The total price cannot be <0");
			}
			testOrder.finishOrder();
		}catch(EmptyOrderException e) {
			e.printStackTrace();
		}catch(DeliveryMethodException e) {
			e.printStackTrace();
		}catch(PaymentMethodException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddEmptyProductToBasket() {
		Product p=new Product();
		testOrder.addNewProductToOrder(p);
		fail("The product added to the basket cannot be empty!");
	}
	
}
