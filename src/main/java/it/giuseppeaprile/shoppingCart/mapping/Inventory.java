package it.giuseppeaprile.shoppingCart.mapping;

import java.util.HashMap;
import java.util.Map;

import it.giuseppeaprile.shoppingCart.enums.ItemType;

public class Inventory {

	private Map<String, ItemType> categoryMapping;
	
	public Inventory() {
		categoryMapping = new HashMap<String, ItemType>();
		categoryMapping.put("book", ItemType.BOOK);
		categoryMapping.put("music CD", ItemType.OTHER);
		categoryMapping.put("chocolate bar", ItemType.FOOD);
		categoryMapping.put("box of chocolates", ItemType.FOOD);
		categoryMapping.put("box of chocolate", ItemType.FOOD);
		categoryMapping.put("bottle of perfume", ItemType.OTHER);
		categoryMapping.put("packet of headache pills", ItemType.MEDICAL);
	}
	
	public void addItemInInventory(String itemName, ItemType type) {
		categoryMapping.put(itemName, type);
	}
	
	public void removeItemInInventory(String itemName, ItemType type) {
		categoryMapping.remove(itemName);
	}
	
	public ItemType getItemType(String itemName) {
		return categoryMapping.get(itemName);
	}
	
	public boolean isItemInInventory(String itemName) {
		return categoryMapping.containsKey(itemName);
	}

}

