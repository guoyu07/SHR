package com.agoda.hotel.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.agoda.hotel.utils.CollectionUtil;

public class Hotel {
	private String id;
	private String name;
	private String phoneNumer;
	private Integer starRanking;
	private Integer noOfRoom;
	private Address address;
	private Date createdDate;
	private Date maintenanceDate;
	Set<String> facilities;
	
	public Hotel(){
		
	}
	
	public Hotel(String name){
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public synchronized  void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumer() {
		return phoneNumer;
	}
	public synchronized  void setPhoneNumer(String phoneNumer) {
		this.phoneNumer = phoneNumer;
	}
	public Integer getStarRanking() {
		return starRanking;
	}
	public synchronized  void setStarRanking(Integer starRanking) {
		this.starRanking = starRanking;
	}
	public Integer getNoOfRoom() {
		return noOfRoom;
	}
	public synchronized  void setNoOfRoom(Integer noOfRoom) {
		this.noOfRoom = noOfRoom;
	}
	public Address getAddress() {
		return address;
	}
	public synchronized  void setAddress(Address address) {
		this.address = address;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public synchronized  void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getMaintenanceDate() {
		return maintenanceDate;
	}
	public synchronized void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}
	public Set<String> getFacilities() {
		return facilities;
	}
	public synchronized void setFacilities(Set<String> facilities) {
		this.facilities = facilities;
	}
	
	public synchronized void updateHotel(Hotel newHotel){
		if (newHotel.address != null){
			this.address = newHotel.address.clone();
		}
		
		this.name = newHotel.name;
		this.noOfRoom = newHotel.noOfRoom;
		
		if (newHotel.facilities != null){
			this.facilities = new LinkedHashSet<>(newHotel.facilities);
		}
		
		this.phoneNumer = newHotel.phoneNumer;
		this.starRanking = newHotel.starRanking;
		
		this.maintenanceDate = Calendar.getInstance().getTime();
	}
	
	public Hotel clone(){
		Hotel hotel = new Hotel();
		hotel.updateHotel(this);
		hotel.setId(this.getId());
		hotel.setCreatedDate(this.getCreatedDate());
		return hotel;
	}
	
	public String toString(){
		return "("+id+") "+name+" : "+address.toString()+", "+noOfRoom+" room(s) "+starRanking+" star(s). "+ CollectionUtil.getStringContent(facilities);
	}
}
