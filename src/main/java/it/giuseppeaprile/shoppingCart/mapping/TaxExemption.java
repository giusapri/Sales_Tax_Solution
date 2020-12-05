package it.giuseppeaprile.shoppingCart.mapping;

import java.util.HashSet;
import it.giuseppeaprile.shoppingCart.enums.ItemType;

public class TaxExemption {

	public HashSet<ItemType> basicSalesExemption;
	
	public TaxExemption() {
		basicSalesExemption = new HashSet<>();
		basicSalesExemption.add(ItemType.BOOK);
		basicSalesExemption.add(ItemType.FOOD);
		basicSalesExemption.add(ItemType.MEDICAL);
	}
	
	public HashSet<ItemType> getBasicSalesExemption() {
		return basicSalesExemption;
	}
	
	public boolean isItemExempt(ItemType type) {
		return basicSalesExemption.contains(type);
	}
	
}

