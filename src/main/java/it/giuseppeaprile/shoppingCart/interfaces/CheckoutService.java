package it.giuseppeaprile.shoppingCart.interfaces;

import it.giuseppeaprile.shoppingCart.entities.ShoppingCart;

public interface CheckoutService {
	public abstract void calculateTotal(ShoppingCart shoppingCart);
	public abstract String printReceipt(ShoppingCart shoppingCart);
}

