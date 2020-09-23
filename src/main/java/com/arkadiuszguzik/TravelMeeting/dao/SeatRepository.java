package com.arkadiuszguzik.TravelMeeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arkadiuszguzik.TravelMeeting.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

}
