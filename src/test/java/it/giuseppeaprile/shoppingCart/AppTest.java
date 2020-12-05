package it.bartolomeotiralongo.shoppingCart;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import it.bartolomeotiralongo.shoppingCart.entities.Order;
import it.bartolomeotiralongo.shoppingCart.entities.ShoppingCart;
import it.bartolomeotiralongo.shoppingCart.logic.CheckoutStrategy;
import it.bartolomeotiralongo.shoppingCart.logic.OrderStringParser;
import it.bartolomeotiralongo.shoppingCart.logic.TaxStrategy;


public class AppTest 
{
	ShoppingCart shoppingCart;
	TaxStrategy taxStrategy;
	
	@Before
	public void setUp() throws Exception {
		shoppingCart = new ShoppingCart(new CheckoutStrategy());
		taxStrategy = new TaxStrategy();
	}
	
	@Test
	public void testShoppingCartInput1() {
		/*
    	Input 1:
    		2 book at 12.49
    		1 music CD at 14.99
    		1 chocolate bar at 0.85
    		
		Output 1:
			2 book: 24.98
			1 music CD: 16.49
			1 chocolate bar: 0.85
			Sales Taxes: 1.50
			Total: 42.32
		*/
    	
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
    		o.applyTaxes(taxStrategy);
    		shoppingCart.addOrder(o);
    	}
    	
    	shoppingCart.calculateTotal();
    	actual = shoppingCart.printReceipt();
    	
    	assertEquals(expected, actual);
	}

	@Test
	public void testShoppingCartInput2() {
		/*
		Input 2:
			1 imported box of chocolates at 10.00
			1 imported bottle of perfume at 47.50
			
		Output 2:
			1 imported box of chocolates: 10.50
			1 imported bottle of perfume: 54.65
			Sales Taxes: 7.65
			Total: 65.15
    	 */
    	
    	String orders = "1 imported box of chocolates at 10.00\n" + 
    			"			1 imported bottle of perfume at 47.50";
    	
    	String expected = "1 imported box of chocolates: 10.50\n" + 
    			"1 imported bottle of perfume: 54.65\n" + 
    			"Sales Taxes: 7.65\n" + 
    			"Total: 65.15";
    	
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
	public void testShoppingCartInput3() {
		/*
    	Input 3:
    		1 imported bottle of perfume at 27.99
    		1 bottle of perfume at 18.99
    		1 packet of headache pills at 9.75
    		3 box of imported chocolates at 11.25
    		
		Output 3:
			1 imported bottle of perfume: 32.19
			1 bottle of perfume: 20.89
			1 packet of headache pills: 9.75
			3 imported box of chocolates: 35.55
			Sales Taxes: 7.90
			Total: 98.38
    	*/
    	
    	String orders = "1 imported bottle of perfume at 27.99\n" + 
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
    	
    	shoppingCart.calculateTotal();
    	actual = shoppingCart.printReceipt();
    	
    	assertEquals(expected, actual);
	}
	
	@Test
	public void testShoppingCartInput4() {
		/*
    	Input 1:
			1 book at 12.49
			1 music CD at 14.99
			1 chocolate bar at 0.85

		Output 1:
			1 book : 12.49
			1 music CD: 16.49
			1 chocolate bar: 0.85
			Sales Taxes: 1.50
			Total: 29.83
		*/
    	
    	String orders = "1 book at 12.49\n" + 
    			"    		1 music CD at 14.99\n" + 
    			"    		1 chocolate bar at 0.85";
    	
    	String expected = "1 book: 12.49\n" + 
    			"1 music CD: 16.49\n" + 
    			"1 chocolate bar: 0.85\n" + 
    			"Sales Taxes: 1.50\n" + 
    			"Total: 29.83";
    	
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
	public void testShoppingCartInput5() {
		/*
    	Input 3:
    		1 imported bottle of perfume at 27.99
			1 bottle of perfume at 18.99
			1 packet of headache pills at 9.75
			1 box of imported chocolates at 11.25
    		
		Output 3:
			1 imported bottle of perfume: 32.19
			1 bottle of perfume: 20.89
			1 packet of headache pills: 9.75
			1 imported box of chocolates: 11.85
			Sales Taxes: 6.70
			Total: 74.68
    	*/
    	
    	String orders = "1 imported bottle of perfume at 27.99\n" + 
    			"    		1 bottle of perfume at 18.99\n" + 
    			"    		1 packet of headache pills at 9.75\n" + 
    			"    		1 box of imported chocolates at 11.25";
    	
    	String expected = "1 imported bottle of perfume: 32.19\n" + 
    			"1 bottle of perfume: 20.89\n" + 
    			"1 packet of headache pills: 9.75\n" + 
    			"1 imported box of chocolates: 11.85\n" + 
    			"Sales Taxes: 6.70\n" + 
    			"Total: 74.68";
    	
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
