package it.giuseppeaprile.shoppingCart.exceptions;

public class NoShoppingCartFoundException extends Exception {

	public NoShoppingCartFoundException() {
		super("No Shopping Cart object found. Consider to set a shopping cart before doing operations");
	}
	
}

