package com.example.EventManager.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String home(Model model) {
		model.addAttribute("events", Erepository.findAll());
		return "home";
	}
	
	@RequestMapping("/addEvent")
	public String addEvent(Model model) {
		model.addAttribute("event", new Event());
		return "addEvent";
	}
	
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Event event){
        Erepository.save(event);
        return "redirect:home";
    }  
	
}
