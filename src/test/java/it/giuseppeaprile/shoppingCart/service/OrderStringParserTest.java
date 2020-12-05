package it.giuseppeaprile.shoppingCart.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import it.giuseppeaprile.shoppingCart.entities.Order;
import it.giuseppeaprile.shoppingCart.entities.ShoppingCart;
import it.giuseppeaprile.shoppingCart.service.TaxStrategy;

public class OrderStringParserTest {

	ShoppingCart shoppingCart;
	TaxStrategy taxStrategy;
	
	@Before
	public void setUp() throws Exception {
		shoppingCart = new ShoppingCart(new CheckoutStrategy());
		taxStrategy = new TaxStrategy();
	}

	
	@Test(expected = NumberFormatException.class)
	public void testParserWrongPrice() throws NumberFormatException{
    	String orders = "2 book at twelve fourty-nine\n" + 
    			"    		1 music CD at 14.99\n" + 
    			"    		1 chocolate bar at 0.85";
    	
    	String expected = "2 book: 24.98\n" + 
    			"1 music CD: 16.49\n" + 
    			"1 chocolate bar: 0.85\n" + 
    			"Sales Taxes: 1.50\n" + 
    			"Total: 42.32";
    	
    	String actual = "";
    	
    	for(String line : orders.split("\n")) {
    		OrderStringParser parser = new OrderStringParser(line);
    		Order o = parser.parse();
    		o.applyTaxes(taxStrategy);
    		shoppingCart.addOrder(o);
    	}
    	
    	shoppingCart.calculateTotal();
    	actual = shoppingCart.printReceipt();
    	
    	assertEquals(expected, actual);
	}
	
	@Test(expected = NullPointerException.class)
	public void testParserWrongQuantity() throws NullPointerException{
    	String orders = "Two book at 12,49\n" + 
    			"    		1 music CD at 14.99\n" + 
    			"    		1 chocolate bar at 0.85";
    	
    	String expected = "2 book: 24.98\n" + 
    			"1 music CD: 16.49\n" + 
    			"1 chocolate bar: 0.85\n" + 
    			"Sales Taxes: 1.50\n" + 
    			"Total: 42.32";
    	
    	String actual = "";
    	
    	for(String line : orders.split("\n")) {
    		OrderStringParser parser = new OrderStringParser(line);
    		Order o = parser.parse();
    		o.applyTaxes(taxStrategy);
    		shoppingCart.addOrder(o);
    	}
    	
    	shoppingCart.calculateTotal();
    	actual = shoppingCart.printReceipt();
    	
    	assertEquals(expected, actual);
	}

}

