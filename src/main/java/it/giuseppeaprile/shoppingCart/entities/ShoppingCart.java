
package it.giuseppeaprile.shoppingCart.entities;

import java.util.HashSet;
import java.util.LinkedHashSet;

import it.giuseppeaprile.shoppingCart.exceptions.ZeroQuantityException;
import it.giuseppeaprile.shoppingCart.service.CheckoutStrategy;

public class ShoppingCart {
	
	private LinkedHashSet<Order> shoppingCart;
	private CheckoutStrategy checkoutStrategy;
	
	public ShoppingCart(CheckoutStrategy checkoutStrategy) {
		this.shoppingCart = new LinkedHashSet<>();
		this.checkoutStrategy = checkoutStrategy;
	}
	
	public void addOrder(Order order) {
		
		// Check if an element is already present in shopping cart
		if(this.shoppingCart.contains(order)) {
			
			// Add quantity
			for(Order orderInCart : this.shoppingCart) {
				if(orderInCart.equals(order)) {
					orderInCart.addItem();
				}
			}
			
		}else {
			
			// Add new Order
			this.shoppingCart.add(order);
			
		}
	}
	
	public boolean removeOrder(Order order) {
			
		// Check if an element is already present in shopping cart
		if(this.shoppingCart.contains(order)) {
			
			// Reduce quantity
			for(Order orderInCart : this.shoppingCart) {
				if(orderInCart.equals(order)) {
					try {
						orderInCart.removeItem();
					}catch (ZeroQuantityException e) {
						return this.shoppingCart.remove(orderInCart);
					}
				}
			}
		}

		return false;
		
	}
	
	public HashSet<Order> getAllOrders(){
		return this.shoppingCart;
	}
	
	public void calculateTotal(){
    	checkoutStrategy.calculateTotal(this);
	}
	
	public String printReceipt(){
    	return checkoutStrategy.printReceipt(this);
	}
	
}
