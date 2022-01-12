package com.example.client.service;
import java.util.List;

import com.example.client.repository.EventRepository;
import com.example.client.model.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventService {
    @Autowired
	private EventRepository repo;
	
	public List<Event> listAll() {
		return repo.findAll();
	}

	public Event get(long event_id) {
		return repo.findById(event_id).get();
	}

	public void save(Event event) {
		repo.save(event);
	}
}