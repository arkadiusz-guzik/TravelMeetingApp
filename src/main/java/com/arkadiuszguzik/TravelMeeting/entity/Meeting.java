package com.arkadiuszguzik.TravelMeeting.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="meetings")
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="meeting_id")
	private int meetingId;
	@Column(name="title")
	private String title;
	@Column(name="date")
	private String date;
	@Column(name="description")
	private String description;
	@Column(name="time")
	private int runningTime;
	
	@OneToMany(mappedBy="meeting",
			cascade= {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE,
					  CascadeType.DETACH, CascadeType.REFRESH})
	List<Audience> audiences;
	
	public Meeting() {
		
	}

	public Meeting(String title, String date, String description, int runningTime) {
		this.title = title;
		this.date = date;
		this.description = description;
		this.runningTime = runningTime;
	}

	public void add(Audience audience){
		
		if(audiences == null) {
			audiences = new ArrayList<>();
		}
		
		audiences.add(audience);
		audience.setMeeting(this);
	}

	public int getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Audience> getAudiences() {
		return audiences;
	}

	public void setAudiences(List<Audience> audiences) {
		this.audiences = audiences;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
}
