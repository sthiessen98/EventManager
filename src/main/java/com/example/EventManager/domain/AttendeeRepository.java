
package com.example.EventManager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AttendeeRepository extends CrudRepository<Attendee, Long>{
	List<Attendee> findByLastName(String lastName);
}
