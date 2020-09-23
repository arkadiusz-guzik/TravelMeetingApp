package com.arkadiuszguzik.TravelMeeting.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arkadiuszguzik.TravelMeeting.entity.Seat;
import com.arkadiuszguzik.TravelMeeting.service.SeatService;

@Controller
public class SeatController {

	private SeatService seatService;
	
	public SeatController(SeatService seatService) {
		this.seatService = seatService;
	}

	@GetMapping("/repertoire/manage/seat/add")
	public String getseatAdd(Model model, Principal principal) {
		
		if(principal != null) {
			String name = principal.getName();
			model.addAttribute("name", name);
		}
		
		Seat seat = new Seat();
		
		model.addAttribute("seat", seat);
		
		return "seat-form";
	}
	
	@GetMapping("/repertoire/manage/seat")
	public String getSeatUpdate(@RequestParam("seatId") int seatId, Model model, Principal principal) {
		
		if(principal != null) {
			String name = principal.getName();
			model.addAttribute("name", name);
		}

		Seat seat = seatService.findBySeatId(seatId);

		model.addAttribute("seat", seat);

		return "seat-form";
	}


	@GetMapping("/repertoire/manage/seat/delete")
	public String deleteSeat(@RequestParam("seatId") int seatId) {

		seatService.deleteBySeatId(seatId);

		return "redirect:/repertoire/manage";
	}
}
