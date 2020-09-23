package com.arkadiuszguzik.TravelMeeting.controller;


import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.arkadiuszguzik.TravelMeeting.entity.Audience;
import com.arkadiuszguzik.TravelMeeting.entity.Seat;
import com.arkadiuszguzik.TravelMeeting.service.AudienceService;

@Controller
public class AudienceController {

	private AudienceService audienceService;

	public AudienceController(AudienceService audienceService) {
		this.audienceService = audienceService;
	}

	@GetMapping("/repertoire/manage/audience/add")
	public String getAudienceAdd(Model model, Principal principal) {
		
		if(principal != null) {
			String name = principal.getName();
			model.addAttribute("name", name);
		}
		
		Audience audience = new Audience();
		
		model.addAttribute("audience", audience);
		
		return "audience-form";
	}
	
	@GetMapping("/repertoire/manage/audience")
	public String getAudienceUpdate(@RequestParam("audienceId") int audienceId, Model model, Principal principal) {

		if(principal != null) {
			String name = principal.getName();
			model.addAttribute("name", name);
		}
		
		Audience audience = audienceService.findByAudienceId(audienceId);

		model.addAttribute("audience", audience);

		return "audience-form";
	}

	

	@GetMapping("/repertoire/manage/audience/delete")
	public String deleteAudience(@RequestParam("audienceId") int audienceId) {
		audienceService.deleteByAudienceId(audienceId);

		return "redirect:/repertoire/manage";
	}


	@PostMapping("/repertoire/manage/seat/save")
	public String saveSeat(@RequestParam("audienceId") int audienceId, @ModelAttribute("seat") Seat seat) {
		
		audienceService.saveSeat(audienceId, seat);

		return "redirect:/repertoire/manage";
	}
	
}
