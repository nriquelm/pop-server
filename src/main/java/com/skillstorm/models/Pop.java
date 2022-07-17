package com.skillstorm.models;

public class Pop {
	
	//members
	private int id;
	private String name;
	private int quantity;
	private double cost;
	private String series;
	private String status;
	
	//constructors
	public Pop() {
		
	}
	public Pop(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	//getters and setters
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	//toString method
	@Override
	public String toString() {
		return "Pop [id=" + id + ", name=" + name + ", quantity=" + quantity + ", cost=" + cost + ", series=" + series
				+ ", status=" + status + "]";
	}
	
	
	
	

}
