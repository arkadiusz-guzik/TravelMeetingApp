package com.arkadiuszguzik.TravelMeeting.service;

import com.arkadiuszguzik.TravelMeeting.entity.Audience;
import com.arkadiuszguzik.TravelMeeting.entity.Seat;

public interface AudienceService {
	
	public Audience findByAudienceId(int audienceId);
	
	public void deleteByAudienceId(int audienceId);
	public void saveSeat(int audienceId, Seat seat);

}
