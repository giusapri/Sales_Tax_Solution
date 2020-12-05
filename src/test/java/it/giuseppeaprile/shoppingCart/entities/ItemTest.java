package it.giuseppeaprile.shoppingCart.entities;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import it.giuseppeaprile.shoppingCart.enums.ItemType;
import it.giuseppeaprile.shoppingCart.service.CheckoutStrategy;
import it.giuseppeaprile.shoppingCart.service.OrderStringParser;
import it.giuseppeaprile.shoppingCart.service.TaxStrategy;

public class ItemTest {

	ShoppingCart shoppingCart;
	TaxStrategy taxStrategy;
	
	@Before
	public void setUp() throws Exception {
		shoppingCart = new ShoppingCart(new CheckoutStrategy());
		taxStrategy = new TaxStrategy();
	}

	@Test
	public void testItemSetterGetter() {
    	
		String orders = "2 book at 12.49\n" + 
    			"    		1 music CD at 14.99\n" + 
    			"    		1 chocolate bar at 0.85";
    	
    	String expected = "2 book: 24.98\n" + 
    			"1 music CD: 16.49\n" + 
    			"1 chocolate bar: 0.85\n" + 
    			"1 imported chocolate bar: 0.90\n" + 
    			"Sales Taxes: 1.55\n" + 
    			"Total: 43.22";
    	
    	String actual = "";
    	
    	for(String line : orders.split("\n")) {
    		OrderStringParser parser = new OrderStringParser(line);
    		Order o = parser.parse();
    		
    		if(o.getQuantity() == 1) 
        		o = new Order(o.getItem());
    		
    		o.applyTaxes(taxStrategy);
    		shoppingCart.addOrder(o);
    		
    	}
    	
    	Item item = new Item("chocolate bar?", BigDecimal.valueOf(0.05));
    	item.setName("chocolate bar");
    	item.setIsImported(true);
    	item.setNetPrice(BigDecimal.valueOf(0.85));
    	item.setType(ItemType.FOOD);
    	item.setTaxes(BigDecimal.valueOf(0.05));
    	
    	Order o = new Order(item);
    	
    	shoppingCart.addOrder(o);
    	
    	shoppingCart.calculateTotal();
    	actual = shoppingCart.printReceipt();
    	
    	assertEquals(expected, actual);
	}
	
	@Test
	public void testItemConstructor() {
    	String orders = "2 book at 12.49\n" + 
    			"    		1 music CD at 14.99\n";
    	
    	String expected = "2 book: 24.98\n" + 
    			"1 music CD: 16.49\n" + 
    			"1 chocolate bar: 0.85\n" + 
    			"Sales Taxes: 1.50\n" + 
    			"Total: 42.32";
    	
    	String actual = "";
    	
    	for(String line : orders.split("\n")) {
    		if(line.trim() == "")
    			continue;
    		
    		OrderStringParser parser = new OrderStringParser(line);
    		Order o = parser.parse();
    		o.applyTaxes(new TaxStrategy());
    		shoppingCart.addOrder(o);
    	}
    	
    	Item item = new Item("chocolate bar", BigDecimal.valueOf(0.85), ItemType.FOOD);
    	Order o = new Order(item);
    	o.applyTaxes(taxStrategy);
    	
    	shoppingCart.addOrder(o);
    	
    	shoppingCart.calculateTotal();
    	actual = shoppingCart.printReceipt();
    	
    	assertEquals(expected, actual);
	}

}

