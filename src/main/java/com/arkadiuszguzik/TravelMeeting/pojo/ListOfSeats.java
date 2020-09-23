package com.arkadiuszguzik.TravelMeeting.pojo;

import java.util.List;

public class ListOfSeats {
	
	private int meetingId;
	private int audienceId;
	private List<String> name;
	
	public ListOfSeats() {
		
	}

	public ListOfSeats(int meetingId, int audienceId, List<String> name) {
		this.meetingId = meetingId;
		this.audienceId = audienceId;
		this.name = name;
	}

	public int getAudienceId() {
		return audienceId;
	}



	public void setAudienceId(int audienceId) {
		this.audienceId = audienceId;
	}



	public List<String> getName() {
		return name;
	}



	public void setName(List<String> name) {
		this.name = name;
	}

	public int getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}
	
}
