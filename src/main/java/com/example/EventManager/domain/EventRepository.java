package com.example.EventManager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event> findByName(String name);
	//List<Event> findByAttendee(Attendee attendee);
}

