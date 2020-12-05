package it.giuseppeaprile.shoppingCart.interfaces;

import java.math.BigDecimal;

import it.giuseppeaprile.shoppingCart.entities.Item;

public interface TaxService {
	
	public abstract BigDecimal calculateImportTaxes(Item item);
	public abstract BigDecimal calculateBasicTaxes(Item item);
	
}

