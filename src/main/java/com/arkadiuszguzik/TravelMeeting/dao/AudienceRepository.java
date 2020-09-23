package com.arkadiuszguzik.TravelMeeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arkadiuszguzik.TravelMeeting.entity.Audience;

public interface AudienceRepository extends JpaRepository<Audience, Integer> {

}
