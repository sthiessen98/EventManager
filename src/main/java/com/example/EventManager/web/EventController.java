package com.example.EventManager.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.EventManager.domain.Attendee;
import com.example.EventManager.domain.AttendeeRepository;
import com.example.EventManager.domain.Event;
import com.example.EventManager.domain.EventRepository;

@Controller
public class EventController {
	
	@Autowired
	private EventRepository Erepository;
	
	@Autowired
	private AttendeeRepository Arepository;
	
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
}
