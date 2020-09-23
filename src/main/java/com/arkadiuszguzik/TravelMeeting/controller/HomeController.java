package com.arkadiuszguzik.TravelMeeting.controller;

import java.security.Principal;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arkadiuszguzik.TravelMeeting.entity.User;
import com.arkadiuszguzik.TravelMeeting.service.UserService;
import com.arkadiuszguzik.TravelMeeting.user.CrmUser;



@Controller
public class HomeController {
	
	@Autowired
    private UserService userService;
    
    @InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHomeWithName(Model model, Principal principal) {

		if (principal != null) {
			String name = principal.getName();
			model.addAttribute("name", name);
		}
		return "home";
	}
	 

	@GetMapping("/login")
	public String getLogin(Model model, Principal principal) {
		if(principal != null) {
			String name = principal.getName();
			model.addAttribute("name", name);
			return "home";
		}
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String getLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/home";
	}
	
	@GetMapping("/registration")
	public String getRegistration(Model model, Principal principal) {
		if(principal != null) {
			String name = principal.getName();
			model.addAttribute("name", name);
			return "home";
		}
		
		model.addAttribute("crmUser", new CrmUser());
		return "registration";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theCrmUser.getUserName();
		
		
		 if (theBindingResult.hasErrors()){
			 return "registration";
	        }

        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

        	return "registration";
        }
    						
        userService.save(theCrmUser);
        
        
        return "login";		
	}
}
