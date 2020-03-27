package com.example.EventManager;

import java.time.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.EventManager.domain.Attendee;
import com.example.EventManager.domain.AttendeeRepository;
import com.example.EventManager.domain.Event;
import com.example.EventManager.domain.EventRepository;


@SpringBootApplication
public class EventManagerApplication {
	private static final Logger log = LoggerFactory.getLogger(EventManagerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(EventRepository Erepo, AttendeeRepository Arepo) {
		return(args)->{
			log.info("Added events to repo..");
			//Add a couple test Events
			Erepo.save(new Event("My Test Event", "2015-01-02", "2015-01-04"));
			Erepo.save(new Event("Birthday Party", "2017-03-02", "2017-03-03"));
			Erepo.save(new Event("Concert", "2017-05-27", "2017-06-01"));

			log.info("Fetch all events");
			for(Event event : Erepo.findAll()) {
				log.info(event.toString());
			}
			
			log.info("Added test attendees to repo");
			Arepo.save(new Attendee("Paul", "Rudd"));
			Arepo.save(new Attendee("Matt", "Thompson"));
			
			log.info("Fetch all attendees..");
			for(Attendee attendee : Arepo.findAll()) {
				log.info(attendee.toString());
			}
		};
	}

}
