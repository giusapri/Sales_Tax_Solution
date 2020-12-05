package it.giuseppeaprile.shoppingCart.exceptions;

public class ZeroQuantityException extends Exception {
	
	public ZeroQuantityException() {
		super("The Item quantity in Order cannot be zero. Consider to remove object instead.");
	}
	
}
