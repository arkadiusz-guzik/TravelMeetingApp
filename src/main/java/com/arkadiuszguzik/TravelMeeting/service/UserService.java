package com.arkadiuszguzik.TravelMeeting.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.arkadiuszguzik.TravelMeeting.entity.User;
import com.arkadiuszguzik.TravelMeeting.user.CrmUser;

public interface UserService extends UserDetailsService {
	User findByUserName(String userName);
	
	void save(CrmUser crmUser);
}
