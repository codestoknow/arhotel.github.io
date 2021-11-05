package com.hotel.model;

public class Product {

	private int id;
	private String name;
	private String catagory;
	private Double price;
	private String image;
	
	
	public Product() {
		
	}

	public Product(int id, String name, String catagory, Double price, String image) {
		this.id = id;
		this.name = name;
		this.catagory = catagory;
		this.price = price;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", catagory=" + catagory + ", price=" + price + ", image="
				+ image + "]";
	}
	
	
}
