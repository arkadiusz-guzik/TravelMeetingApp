package com.arkadiuszguzik.TravelMeeting.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="audiences")
public class Audience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="audience_id")
	private int audienceId;
	@Column(name="hour")
	private String hour;
	@Column(name="price")
	private double price;
	
	
	@OneToMany(mappedBy="audience",
				cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE,
						  CascadeType.DETACH, CascadeType.REFRESH})
	private List<Seat> seats;
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="meeting_id")
	private Meeting meeting;
	
	public Audience() {
		
	}
	
	public Audience(String hour, double price) {
		this.hour = hour;
		this.price = price;
	}

	public void add(Seat seat){
		
		if(seats == null) {
			seats = new ArrayList<>();
		}
		
		seats.add(seat);
		seat.setAudience(this);
	}
	
	public int getAudienceId() {
		return audienceId;
	}

	public void setAudienceId(int audience_id) {
		this.audienceId = audience_id;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
