package hello.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Item {
	 protected Item() {}
	 private String name;
//	 @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
//	 private Customer customer;
	private Long customerId;
	
//	public Customer getCustomer() {
//		return customer;
//	}
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Item(Long id, ItemType itemType, String name, float price) {
		super();
		this.id = id;
		this.itemType = itemType;
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;
	 
	 private ItemType itemType;
	 private float price;
	
	public void setId(long id) {
		this.id = id;
	}
	public ItemType getItemType() {
		return itemType;
	}
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	 
	 
}
