package com.group.foctg.holidayMaker.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Accommodation {
	
	@Id
	@GeneratedValue
	@Column
	private Long ID;
	
	@Column
	private String name;
	
	@Column
	private Boolean pool;
	
	@Column
	private Boolean nightEntertainment;
	
	@Column
	private Boolean childEvents;
	
	@Column
	private Boolean restaurant;
	
	@Column
	private Short distanceToBeach;
	
	@Column
	private Short distanceToCenter;
	
	@Column
	private Location location;
	
	@Column
	private String imageURL;
	
	@Column
	private String description;
	
	//@Column
	//private List<Booking> bookings;
	//private List<Room> rooms;
	//private User user;
	
	
	
	public Long getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getPool() {
		return pool;
	}
	
	public void setPool(Boolean pool) {
		this.pool = pool;
	}
	
	public Boolean getNightEntertainment() {
		return nightEntertainment;
	}
	
	public void setNightEntertainment(Boolean nightEntertainment) {
		this.nightEntertainment = nightEntertainment;
	}
	
	public Boolean getChildEvents() {
		return childEvents;
	}
	
	public void setChildEvents(Boolean childEvents) {
		this.childEvents = childEvents;
	}
	
	public Boolean getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Boolean restaurant) {
		this.restaurant = restaurant;
	}
	
	public Short getDistanceToBeach() {
		return distanceToBeach;
	}
	
	public void setDistanceToBeach(Short distanceToBeach) {
		this.distanceToBeach = distanceToBeach;
	}
	
	public Short getDistanceToCenter() {
		return distanceToCenter;
	}
	
	public void setDistanceToCenter(Short distanceToCenter) {
		this.distanceToCenter = distanceToCenter;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getImageURL() {
		return imageURL;
	}
	
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
