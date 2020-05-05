package com.example.EventManager.web;

import java.security.Principal;
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
	
	//home page
	@RequestMapping("/home")
	public String home(Model model, Principal principal) {
		model.addAttribute("events", Erepository.findAll());
		return "home";
	}
	
	//Attendees current events page
	@RequestMapping("/myEvents")
	public String myEvents(Model model, Principal principal) {
		Attendee attendee = Arepository.findByUsername(principal.getName());
		model.addAttribute("events", attendee.getEvents());
		return "myEvents";
	}
	
	//Event creation
	@RequestMapping("/addEvent")
	public String addEvent(Model model) {
		model.addAttribute("event", new Event());
		return "addEvent";
	}
	
	//Saving event after creation or edit
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Event event){
        Erepository.save(event);
        return "redirect:home";
    }  
    
    //Event deletion
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteEvent(@PathVariable("id") Long eventId, Model model) {
    	Erepository.deleteById(eventId);
    	return "redirect:../home";
    }
    
    //Event editing
    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String editEvent(@PathVariable("id") Long eventId, Model model){
    	model.addAttribute("event", Erepository.findById(eventId));
    	return "editEvent";
    }
    
    //Event registration for attendee
    @RequestMapping(value="/register/{id}", method = RequestMethod.GET)
    public String registerEvent(@PathVariable("id") Long eventId, Principal principal) {
    	Optional<Event> optEvent = Erepository.findById(eventId);
    	Attendee attendee = Arepository.findByUsername(principal.getName());
    	Event event = optEvent.get();
    	List<Event> currEvents = attendee.getEvents();
    	if(!currEvents.contains(event)) {
        	event.addAttendee(attendee);
        	attendee.addEvents(event);
            Erepository.save(event);
            Arepository.save(attendee);
    	}
    	
    	return "redirect:../home";
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
