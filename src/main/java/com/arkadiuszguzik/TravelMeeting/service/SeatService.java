package com.arkadiuszguzik.TravelMeeting.service;

import com.arkadiuszguzik.TravelMeeting.entity.Seat;

public interface SeatService {
	public Seat findBySeatId(int seatId);
	
	public void deleteBySeatId(int seatId);
}
