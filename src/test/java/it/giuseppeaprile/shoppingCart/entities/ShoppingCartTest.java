package it.giuseppeaprile.shoppingCart.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.giuseppeaprile.shoppingCart.service.CheckoutStrategy;
import it.giuseppeaprile.shoppingCart.service.OrderStringParser;
import it.giuseppeaprile.shoppingCart.service.TaxStrategy;

public class ShoppingCartTest {

	ShoppingCart shoppingCart;
	TaxStrategy taxStrategy;
	
	@Before
	public void setUp() throws Exception {
		shoppingCart = new ShoppingCart(new CheckoutStrategy());
		taxStrategy = new TaxStrategy();
	}
	
	@Test
	public void addItemAsSeparateOrder() {
		// Add same item as separate Order
    	
    	String orders = "1 imported bottle of perfume at 27.99\n" + 
    			"    		1 bottle of perfume at 18.99\n" + 
    			"    		1 bottle of perfume at 18.99\n" +
    			"    		1 packet of headache pills at 9.75\n" + 
    			"    		3 box of imported chocolates at 11.25";
    	
    	String expected = "1 imported bottle of perfume: 32.19\n" + 
    			"2 bottle of perfume: 41.78\n" + 
    			"1 packet of headache pills: 9.75\n" + 
    			"3 imported box of chocolates: 35.55\n" + 
    			"Sales Taxes: 9.80\n" + 
    			"Total: 119.27";
    	
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
	
	@Test
	public void reduceQuantityOrder() {
		// Add same item as separate Order
    	
    	String orders = "1 imported bottle of perfume at 27.99\n" + 
    			"    		1 bottle of perfume at 18.99\n" + 
    			"    		1 bottle of perfume at 18.99\n" +
    			"    		1 packet of headache pills at 9.75\n" + 
    			"    		3 box of imported chocolates at 11.25";
    	
    	String expected = "1 imported bottle of perfume: 32.19\n" + 
    			"1 bottle of perfume: 20.89\n" + 
    			"1 packet of headache pills: 9.75\n" + 
    			"3 imported box of chocolates: 35.55\n" + 
    			"Sales Taxes: 7.90\n" + 
    			"Total: 98.38";
    	
    	String actual = "";
    	
    	for(String line : orders.split("\n")) {
    		OrderStringParser parser = new OrderStringParser(line);
    		Order o = parser.parse();
    		o.applyTaxes(taxStrategy);
    		shoppingCart.addOrder(o);
    	}
    	
    	OrderStringParser parser = new OrderStringParser("1 bottle of perfume at 18.99");
    	Order o = parser.parse();
    	shoppingCart.removeOrder(o);
    	
    	shoppingCart.calculateTotal();
    	actual = shoppingCart.printReceipt();
    	
    	assertEquals(expected, actual);
	}
	
	@Test
	public void removeOrder() {
		// Add same item as separate Order
    	
    	String orders = "1 imported bottle of perfume at 27.99\n" + 
    			"    		1 bottle of perfume at 18.99\n" +
    			"    		1 packet of headache pills at 9.75\n" + 
    			"    		3 box of imported chocolates at 11.25";
    	
    	String expected = "1 imported bottle of perfume: 32.19\n" + 
    			"1 packet of headache pills: 9.75\n" + 
    			"3 imported box of chocolates: 35.55\n" + 
    			"Sales Taxes: 6.00\n" + 
    			"Total: 77.49";
    	
    	String actual = "";
    	
    	for(String line : orders.split("\n")) {
    		OrderStringParser parser = new OrderStringParser(line);
    		Order o = parser.parse();
    		o.applyTaxes(taxStrategy);
    		shoppingCart.addOrder(o);
    	}
    	
    	OrderStringParser parser = new OrderStringParser("1 bottle of perfume at 18.99");
    	Order o = parser.parse();
    	shoppingCart.removeOrder(o);
    	
    	shoppingCart.calculateTotal();
    	actual = shoppingCart.printReceipt();
    	
    	assertEquals(expected, actual);
	}
	
}

