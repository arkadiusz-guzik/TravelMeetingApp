package com.arkadiuszguzik.TravelMeeting.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.arkadiuszguzik.TravelMeeting.entity.Audience;
import com.arkadiuszguzik.TravelMeeting.entity.Meeting;
import com.arkadiuszguzik.TravelMeeting.pojo.ListOfSeats;
import com.arkadiuszguzik.TravelMeeting.service.MeetingService;

@Controller
public class MeetingController {

	private MeetingService meetingService;
	public static String uploadDirectory = Paths.get("src/main/resources/static/image/photos").toString();
	
	public MeetingController(MeetingService meetingService) {
		this.meetingService = meetingService;
	}
	
	@RequestMapping(value = "/repertoire/list", method = RequestMethod.GET)
	public String getRepertoire(Model model, Principal principal) {

		if (principal != null) {
			String name = principal.getName();
			model.addAttribute("name", name);
		}

		List<Meeting> meetings = meetingService.findAll();
		model.addAttribute("meetings", meetings);
		

		return "repertoire";
	}

	@ResponseBody
	@RequestMapping(value = "/repertoire/buy", method = RequestMethod.POST)
	public String getSeat(@RequestBody ListOfSeats listOfSeats, Principal principal) {
		
		meetingService.buy(listOfSeats, principal.getName());
		
		return "redirect:/repertoire/list";
	}

	@GetMapping("/repertoire/manage")
	public String getManage(Model model, Principal principal) {
		
		if (principal != null) {
			String name = principal.getName();
			model.addAttribute("name", name);
		}

		List<Meeting> meetings = meetingService.findAll();

		model.addAttribute("meetings", meetings);

		return "manage";
	}


	@GetMapping("/repertoire/manage/meeting/add")
	public String getMeetingAdd(Model model, Principal principal) {
		
		Meeting meeting = new Meeting();
		
		if (principal != null) {
			String name = principal.getName();
			model.addAttribute("name", name);
		}
		
		model.addAttribute("meeting", meeting);
		
		return "meeting-form";
	}
	
	@GetMapping("/repertoire/manage/meeting")
	public String getMeetingUpdate(@RequestParam("meetingId") int meetingId, Model model, Principal principal) {
		
		if(principal != null) {
			String name = principal.getName();
			model.addAttribute("name", name);
		}

		Meeting meeting = meetingService.findByMeetingId(meetingId);

		model.addAttribute("meeting", meeting);

		return "meeting-form";
	}

	@PostMapping("/repertoire/manage/meeting/save")
	public String saveMeetingImg(@RequestParam("files") MultipartFile[] files, @ModelAttribute("meeting") Meeting meeting) {

		meetingService.saveMeeting(meeting);
		
		StringBuilder fileNames = new StringBuilder();

		if(files != null) {
			for(MultipartFile file : files) {
				Path fileNameAndPath = Paths.get(uploadDirectory, meeting.getTitle()+".jpg");
				fileNames.append(file.getOriginalFilename());
				
				try {
					Files.write(fileNameAndPath,  file.getBytes());
					
				}catch(IOException e) {
					e.printStackTrace();
					System.out.print("ZDj");
				}	
			}
		}
		return "redirect:/repertoire/manage";
	}

	@GetMapping("/repertoire/manage/meeting/delete")
	public String deletMeeting(@RequestParam("meetingId") int meetingId) {
		meetingService.deleteByMeetingId(meetingId);

		return "redirect:/repertoire/manage";
	}
	
	@PostMapping("/repertoire/manage/audience/save")
	public String saveMeeting(@RequestParam("meetingId") int meetingId, @ModelAttribute("audience") Audience audience) {

		meetingService.saveAudience(meetingId, audience);

		return "redirect:/repertoire/manage";
	}
	
}
