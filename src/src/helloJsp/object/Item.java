package helloJsp.object;

public class Item {

	private Integer quantity;
	private Integer idItem;
	private Integer price;
	private String description;

	public Item(Integer quantity, Integer idItem, Integer price) {
		// TODO Auto-generated constructor stub
		this.quantity = quantity;
		this.idItem = idItem;
		this.price = price;
		this.description = "";
	}
	
	public Item(Integer quantity, Integer idItem, Integer price, String description) {
		// TODO Auto-generated constructor stub
		this.quantity = quantity;
		this.idItem = idItem;
		this.price = price;
		this.description = description;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
