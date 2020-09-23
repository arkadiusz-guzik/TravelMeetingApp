package com.arkadiuszguzik.TravelMeeting;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.arkadiuszguzik.TravelMeeting.controller.MeetingController;

@SpringBootApplication
public class TravelMeetingApplication {

	public static void main(String[] args) {
		new File(MeetingController.uploadDirectory).mkdir();
		SpringApplication.run(TravelMeetingApplication.class, args);
	}

}
