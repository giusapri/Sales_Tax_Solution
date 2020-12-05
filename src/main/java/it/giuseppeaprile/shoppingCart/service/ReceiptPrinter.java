package it.giuseppeaprile.shoppingCart.service;

import java.math.BigDecimal;
import java.util.Locale;

import it.giuseppeaprile.shoppingCart.entities.Order;
import it.giuseppeaprile.shoppingCart.entities.ShoppingCart;

public class ReceiptPrinter {

	private CheckoutStrategy checkout;
	private ShoppingCart shoppingCart;
	
	public ReceiptPrinter(CheckoutStrategy checkout, ShoppingCart shoppingCart) {
		this.checkout = checkout;
		this.shoppingCart = shoppingCart;
	}
	
	public String print() {
		String receipt = "";
		
		for(Order order : this.shoppingCart.getAllOrders()) {
			if(order.getItem().getIsImported())
				receipt += String.format(Locale.ENGLISH, "%d imported %s: %.2f\n", order.getQuantity(), order.getItem().getName(), BigDecimal.valueOf(order.getQuantity()).multiply(order.getItem().getPriceAfterTaxes()));
			else
				receipt += String.format(Locale.ENGLISH, "%d %s: %.2f\n", order.getQuantity(), order.getItem().getName(), BigDecimal.valueOf(order.getQuantity()).multiply(order.getItem().getPriceAfterTaxes()));
		}
		
		receipt += String.format(Locale.ENGLISH, "Sales Taxes: %.2f\n", checkout.getTaxes());
		receipt += String.format(Locale.ENGLISH, "Total: %.2f", checkout.getTotal());
		
		return receipt;
	}
	
}

