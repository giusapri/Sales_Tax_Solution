package it.giuseppeaprile.shoppingCart.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.giuseppeaprile.shoppingCart.service.CheckoutStrategy;
import it.giuseppeaprile.shoppingCart.service.OrderStringParser;
import it.giuseppeaprile.shoppingCart.service.TaxStrategy;

public class OrderTest {

	ShoppingCart shoppingCart;
	TaxStrategy taxStrategy;
	
	@Before
	public void setUp() throws Exception {
		shoppingCart = new ShoppingCart(new CheckoutStrategy());
		taxStrategy = new TaxStrategy();
	}


	@Test
	public void testOrderConstructorOneArgument() {
		// Add same item as separate Order
    	
		String orders = "2 book at 12.49\n" + 
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
    		
    		if(o.getQuantity() == 1) 
        		o = new Order(o.getItem());
    		
    		o.applyTaxes(taxStrategy);
    		shoppingCart.addOrder(o);
    		
    	}
    	
    	shoppingCart.calculateTotal();
    	actual = shoppingCart.printReceipt();
    	
    	assertEquals(expected, actual);
	}

}

