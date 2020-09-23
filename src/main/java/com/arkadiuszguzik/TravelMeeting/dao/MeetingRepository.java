package com.arkadiuszguzik.TravelMeeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arkadiuszguzik.TravelMeeting.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

}
