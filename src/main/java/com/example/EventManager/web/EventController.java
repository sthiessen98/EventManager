package com.example.EventManager.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	/*
	@RequestMapping("/myEvents")
	public String myEvents(Model model) {
		Attendee attendee = Arepository.findByUsername("sthiessen");
		model.addAttribute("events", attendee.getEvents());
		return "myEvents";
	}
	*/
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
    
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteEvent(@PathVariable("id") Long eventId, Model model) {
    	Erepository.deleteById(eventId);
    	return "redirect:../home";
    }
    
    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String editEvent(@PathVariable("id") Long eventId, Model model){
    	model.addAttribute("event", Erepository.findById(eventId));
    	return "editEvent";
    }
    
    /*
     *  RESTful API / START
     */
    
    @RequestMapping(value="/events", method = RequestMethod.GET)
    public @ResponseBody List<Event> eventListRest(){
    	return (List<Event>) Erepository.findAll();
    }
    
    @RequestMapping(value="/eventId/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Event> findEventByIdRest(@PathVariable("id") Long id){
    	return Erepository.findById(id);
    }
    
    @RequestMapping(value="/attendees", method = RequestMethod.GET)
    public @ResponseBody List<Attendee> attendeeListRest(){
    	return (List<Attendee>) Arepository.findAll();
    }
    
    @RequestMapping(value="/attendeeId/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Attendee> findAttendeeByIdRest(@PathVariable("id") Long id){
    	return Arepository.findById(id);
    }
    /*
     *  RESTful API / END
     */
	
}
