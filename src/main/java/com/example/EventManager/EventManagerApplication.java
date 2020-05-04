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
import com.example.EventManager.domain.User;
import com.example.EventManager.domain.UserRepository;


@SpringBootApplication
public class EventManagerApplication {
	private static final Logger log = LoggerFactory.getLogger(EventManagerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(EventRepository Erepo, AttendeeRepository Arepo, UserRepository uRepo) {
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
			
			//Add a couple of test Attendees
			log.info("Added test attendees to repo..");
			Arepo.save(new Attendee("Spencer", "Thiessen", "sthiessen"));
			Arepo.save(new Attendee("Matt", "Thompson", "mthompson"));
			
			log.info("Fetch all attendees..");
			for(Attendee attendee : Arepo.findAll()) {
				log.info(attendee.toString());
			}
			
			
			//Add 2 users. 1 Organizer, 1 Attendee
			User user1 = new User("sthiessen", "$2a$10$XucSWZodB/ffmUVbkxhu4uqFrWPK4fBx02chp5SESipShCegekpKi", "ORGANIZER"); //sthiessen, Password1
			User user2 = new User("mthompson","$2a$10$6R4H7BNa.iBE.RNzDGXrxeA640y.23e/95/tV/p/nXjPuVl51DGKW","ATTENDEE"); //mthompson, Password2
			
			uRepo.save(user1);
			uRepo.save(user2);
		};
	}

}
