package it.giuseppeaprile.shoppingCart.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import it.giuseppeaprile.shoppingCart.entities.Item;
import it.giuseppeaprile.shoppingCart.interfaces.TaxService;
import it.giuseppeaprile.shoppingCart.mapping.TaxExemption;

public class TaxStrategy implements TaxService{
	
	public static BigDecimal BASIC_TAX_RATE = BigDecimal.valueOf(0.10);
	public static BigDecimal IMPORT_TAX_RATE = BigDecimal.valueOf(0.05);

	private BigDecimal taxes;
	
	public TaxStrategy() {
		this.taxes = BigDecimal.ZERO;
	}

	public BigDecimal calculateTaxes(Item item) {
		this.taxes = BigDecimal.ZERO;
		this.taxes = this.taxes.add(calculateImportTaxes(item)).add(calculateBasicTaxes(item));
		return this.taxes;
	}
	
	@Override
	public BigDecimal calculateImportTaxes(Item item) {
		
		BigDecimal importTax = BigDecimal.ZERO;
				
		if(item.getIsImported()) {
			importTax = importTax.add(IMPORT_TAX_RATE.multiply(item.getNetPrice()));
		}

		return roundToFiveCent(importTax);	
	}

	@Override
	public BigDecimal calculateBasicTaxes(Item item) {
		BigDecimal basicTax = BigDecimal.ZERO;
		
		TaxExemption exemptions = new TaxExemption();
		
		if(!exemptions.isItemExempt(item.getType())) {
			basicTax = basicTax.add(BASIC_TAX_RATE.multiply(item.getNetPrice()));
		}

		return roundToFiveCent(basicTax);
	}
	
	private BigDecimal roundToFiveCent(BigDecimal tax) {
		return tax.divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP).multiply(BigDecimal.valueOf(0.05)).setScale(2);
	}

}

