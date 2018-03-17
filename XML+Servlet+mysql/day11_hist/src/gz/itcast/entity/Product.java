﻿package gz.itcast.entity;

public class Product {

	private String id;
	private String proName;
	private String proType;
	private double price;
	public String getId() {
		return id;
	}
	public String getProName() {
		return proName;
	}
	public String getProType() {
		return proType;
	}
	public double getPrice() {
		return price;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public void setProType(String proType) {
		this.proType = proType;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", proName=" + proName + ", proType="
				+ proType + ", price=" + price + "]";
	}
	public Product(String id, String proName, String proType, double price) {
		super();
		this.id = id;
		this.proName = proName;
		this.proType = proType;
		this.price = price;
	}
	public Product() {
		
	}
	
	
}
