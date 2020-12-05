package it.bartolomeotiralongo.shoppingCart.entities;

import java.math.BigDecimal;

import it.bartolomeotiralongo.shoppingCart.enums.ItemType;
import it.bartolomeotiralongo.shoppingCart.logic.TaxStrategy;

/***
 * Single item present in the store
 * 
 * @author Bartolomeo Tiralongo
 *
 */

public class Item {

	private String name;
	private BigDecimal netPrice;
	private ItemType type;
	private Boolean isImported;
	private BigDecimal taxes;

	public Item(String name, BigDecimal netPrice) {
		super();
		this.name = name;
		this.netPrice = netPrice;
		this.type = ItemType.OTHER;
		this.isImported = false;
		this.taxes = BigDecimal.ZERO;
	}
	
	public Item(String name, BigDecimal netPrice, ItemType type) {
		super();
		this.name = name;
		this.netPrice = netPrice;
		this.type = type;
		this.isImported = false;
		this.taxes = BigDecimal.ZERO;
	}
	
	public Item(String name, BigDecimal netPrice, ItemType type, Boolean isImported) {
		super();
		this.name = name;
		this.netPrice = netPrice;
		this.type = type;
		this.isImported = isImported;
		this.taxes = BigDecimal.ZERO;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getNetPrice() {
		return netPrice;
	}
	
	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}
	
	public ItemType getType() {
		return type;
	}
	
	public void setType(ItemType type) {
		this.type = type;
	}
	
	public Boolean getIsImported() {
		return isImported;
	}

	public void setIsImported(Boolean isImported) {
		this.isImported = isImported;
	}

	public BigDecimal getTaxes() {
		return taxes;
	}

	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}
	
	public BigDecimal getPriceAfterTaxes() {
		return netPrice.add(taxes);
	}
	
	public Item applyItemTaxes(TaxStrategy taxStrategy) {
		BigDecimal taxes = taxStrategy.calculateTaxes(this);
		this.taxes = this.taxes.add(taxes);
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isImported == null) ? 0 : isImported.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((netPrice == null) ? 0 : netPrice.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Item other = (Item) obj;
		if (isImported == null) {
			if (other.isImported != null)
				return false;
		} else if (!isImported.equals(other.isImported))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (netPrice == null) {
			if (other.netPrice != null)
				return false;
		} else if (!netPrice.equals(other.netPrice))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}