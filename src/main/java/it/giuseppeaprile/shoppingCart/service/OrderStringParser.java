package it.giuseppeaprile.shoppingCart.service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.giuseppeaprile.shoppingCart.entities.Item;
import it.giuseppeaprile.shoppingCart.entities.Order;
import it.giuseppeaprile.shoppingCart.mapping.Inventory;

public class OrderStringParser {

	private static final String INPUT_PATTERN = "([0-9]) (.*?) at ([0-9\\.]*)";
	
	private String input;
	private Order order;
	private Item item;
	
	public OrderStringParser() {
		super();
		this.input = "";
	}
	
	public OrderStringParser(String input) {
		super();
		this.input = input;
	}
	
	public Order parse() throws IllegalArgumentException, NumberFormatException, NullPointerException{
		
		final Pattern pattern = Pattern.compile(INPUT_PATTERN, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(this.input);
		
		while(matcher.find()) {
			String quantity = matcher.group(1);
			String name = matcher.group(2);
			String price = matcher.group(3);
			
			int q = Integer.parseInt(quantity);
			BigDecimal p = BigDecimal.valueOf(Double.parseDouble(price));
			boolean isImported = name.contains("imported") ? true : false;
			name = stripKeyword(name, "imported");
			
			Inventory inventory = new Inventory();
			
			this.item = new Item(name, p, inventory.getItemType(name), isImported);
			this.order = new Order(this.item, q);
			
		}
		
		return this.order;
	}
	
	private String stripKeyword(String string, String keyword) {
		if(!string.contains(keyword))
			return string;
		
		String strippedString = "";
		int indexKeyword = string.indexOf(keyword);
		
		strippedString += string.substring(0, indexKeyword).trim();
		strippedString += " ";
		strippedString += string.substring(indexKeyword + keyword.length(), string.length()).trim();
		
		return strippedString.trim();
		
	}
	
	public String getInput() {
		return input;
	}
	
	public void setInput(String input) {
		this.input = input;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
			
}

