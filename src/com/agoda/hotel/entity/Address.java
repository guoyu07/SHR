package com.agoda.hotel.entity;

public class Address {
	String country;
	String city;
	String street;
	
	public Address(){
		
	}
	
	public Address(String country, String city, String street){
		this.country = country;
		this.city = city;
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Address clone(){
		return new Address(this.country,this.city,this.street);
	}
	
	public String toString(){
		return country+" - "+city+" - "+street;
	}
	
}
