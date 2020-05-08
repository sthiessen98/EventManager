
package com.example.EventManager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends CrudRepository<Attendee, Long>{
	Attendee findByUsername(String lastName);
}

