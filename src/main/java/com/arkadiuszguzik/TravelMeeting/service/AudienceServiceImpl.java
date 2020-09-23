package com.arkadiuszguzik.TravelMeeting.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arkadiuszguzik.TravelMeeting.dao.AudienceRepository;
import com.arkadiuszguzik.TravelMeeting.entity.Audience;
import com.arkadiuszguzik.TravelMeeting.entity.Seat;


@Service
public class AudienceServiceImpl implements AudienceService {

	private AudienceRepository audienceRepository;
	
	public AudienceServiceImpl(AudienceRepository audienceRepository) {
		this.audienceRepository = audienceRepository;
	}
	
	@Override
	public Audience findByAudienceId(int audienceId) {
		Optional<Audience> result = audienceRepository.findById(audienceId);
		
		Audience dbAudience = null;
		if(result.isPresent()) {
			dbAudience = result.get();
			
		}else {
			throw new RuntimeException("Did not find audience id - " + audienceId);
		}
		
		return dbAudience;
	}

	

	@Override
	public void deleteByAudienceId(int audienceId) {
		
		audienceRepository.deleteById(audienceId);
	}
	
	
	
	@Override
	public void saveSeat(int audienceId, Seat seat) {
		Audience dbAudience = findByAudienceId(audienceId);
		 
		dbAudience.add(seat);
		
		audienceRepository.save(dbAudience);
	}

}
