package com.arkadiuszguzik.TravelMeeting.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arkadiuszguzik.TravelMeeting.dao.SeatRepository;
import com.arkadiuszguzik.TravelMeeting.entity.Seat;

@Service
public class SeatServiceImpl implements SeatService {
	
	private SeatRepository serviceRepository;
	
	public SeatServiceImpl(SeatRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	@Override
	public Seat findBySeatId(int seatId) {
		Optional<Seat> result = serviceRepository.findById(seatId);
		
		Seat dbSeat = null;
		if(result.isPresent()) {
			dbSeat = result.get();
			
		}else {
			throw new RuntimeException("Did not find seat id - " + dbSeat);
		}
		
		return dbSeat;
	}

	

	@Override
	public void deleteBySeatId(int seatId) {

		serviceRepository.deleteById(seatId);
	}

}
