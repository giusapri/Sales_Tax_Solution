package it.giuseppeaprile.shoppingCart.entities;

import it.giuseppeaprile.shoppingCart.exceptions.ZeroQuantityException;
import it.giuseppeaprile.shoppingCart.service.TaxStrategy;

/**
 * Order of the user
 * 
 * @author giuseppeaprile
 *
 */
public class Order{

	private Item item;
	private int quantity;
	
	public Order(Item item) {
		super();
		this.item = item;
		this.quantity = 1;
	}
	
	public Order(Item item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void addItem() {
		this.quantity += 1;
	}
	
	public void removeItem() throws ZeroQuantityException {
		if(this.quantity - 1 <= 0)
			throw new ZeroQuantityException();
		
		this.quantity -= 1;
	}

	public Order applyTaxes(TaxStrategy taxStrategy) {
		this.item.applyItemTaxes(taxStrategy);
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}
	
}

