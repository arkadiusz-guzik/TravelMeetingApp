package com.arkadiuszguzik.TravelMeeting.service;

import java.util.List;

import com.arkadiuszguzik.TravelMeeting.entity.Audience;
import com.arkadiuszguzik.TravelMeeting.entity.Meeting;
import com.arkadiuszguzik.TravelMeeting.pojo.ListOfSeats;

public interface MeetingService {
	public List<Meeting> findAll();
	public void buy(ListOfSeats listOfSeats, String userId);
	public Meeting findByMeetingId(int meetingId);
	public void saveMeeting(Meeting meeting);
	public void deleteByMeetingId(int meetingId);
	public void saveAudience(int meetingId, Audience audience);
	
}
