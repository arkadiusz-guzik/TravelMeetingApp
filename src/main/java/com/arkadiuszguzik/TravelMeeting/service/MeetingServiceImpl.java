package com.arkadiuszguzik.TravelMeeting.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arkadiuszguzik.TravelMeeting.dao.MeetingRepository;
import com.arkadiuszguzik.TravelMeeting.entity.Audience;
import com.arkadiuszguzik.TravelMeeting.entity.Meeting;
import com.arkadiuszguzik.TravelMeeting.entity.Seat;
import com.arkadiuszguzik.TravelMeeting.pojo.ListOfSeats;

@Service
public class MeetingServiceImpl implements MeetingService{
	
	private MeetingRepository meetingRepository;
	
	public MeetingServiceImpl(MeetingRepository meetingRepository) {
		this.meetingRepository = meetingRepository;
	}

	@Override
	public List<Meeting> findAll() {
		return meetingRepository.findAll();
	}

	@Override
	public void buy(ListOfSeats listOfSeats, String userId) {
		Optional<Meeting> result = meetingRepository.findById(listOfSeats.getMeetingId());

		Meeting dbMeeting = null;
		if(result.isPresent()) {
			dbMeeting = result.get();
			
		}else {
			throw new RuntimeException("Did not find meeting id - " + listOfSeats.getMeetingId());
		}
		
		Audience dbAudience = dbMeeting.getAudiences().get(listOfSeats.getAudienceId());
	
		if( dbAudience != null) {
			for(int i=0; i<dbAudience.getSeats().size(); i++) {
				for(String nameOfSeat : listOfSeats.getName()) {
					Seat dbSeat = dbAudience.getSeats().get(i);
					if(dbSeat.getName().equalsIgnoreCase(nameOfSeat)) {
						dbSeat.setUserId(userId);
					}
				}
				
			}
		}else {
			throw new RuntimeException("Did not find adudience id - " + listOfSeats.getAudienceId());
		}
		
		meetingRepository.save(dbMeeting);
		
	}

	@Override
	public Meeting findByMeetingId(int meetingId) {
		Optional<Meeting> result = meetingRepository.findById(meetingId);
		
		Meeting dbMeeting = null;
		if(result.isPresent()) {
			dbMeeting = result.get();
			
		}else {
			throw new RuntimeException("Did not find meeting id - " + meetingId);
		}
		
		return dbMeeting;
	}

	@Override
	public void saveMeeting(Meeting meeting) {
		
		meetingRepository.save(meeting);
		
	}

	@Override
	public void deleteByMeetingId(int meetingId) {
		
		meetingRepository.deleteById(meetingId);
		
	}
	
	
	/*------------------------------------------------------*/
	
	@Override
	public void saveAudience(int meetingId, Audience audience) {
		Meeting dbMeeting = findByMeetingId(meetingId);
		dbMeeting.add(audience);
		meetingRepository.save(dbMeeting);
	}

	
	
	
}
