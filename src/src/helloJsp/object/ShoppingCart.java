package helloJsp.object;

import java.util.ArrayList;

public class ShoppingCart {

	private ArrayList<Item> items;
	
	public ShoppingCart() {
		// TODO Auto-generated constructor stub
		items = new ArrayList<Item>();
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}
