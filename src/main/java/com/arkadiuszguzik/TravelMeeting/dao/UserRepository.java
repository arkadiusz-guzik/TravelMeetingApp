package com.arkadiuszguzik.TravelMeeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arkadiuszguzik.TravelMeeting.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
