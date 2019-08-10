package com.skilldistillery.tripping.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tripping.entities.Event;
import com.skilldistillery.tripping.repositories.EventRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository repo;

	@Override
	public List<Event> index() {
		return repo.findAll();
	}

	@Override
	public Event getEventById(Integer id) {
		Optional<Event> eventOpt = repo.findById(id);
		if (eventOpt.isPresent()) {
			Event event = eventOpt.get();
			return event;
		}
		return null;
	}
	
	@Override
	public Event create(Event event) {
		event = repo.saveAndFlush(event);
		return event;
	}

	@Override
	public Boolean delete(Integer id) {
		Optional<Event> eventOpt = repo.findById(id);
		Event managedEvent = null;
		if (eventOpt.isPresent()) {
			managedEvent = eventOpt.get();
			repo.delete(managedEvent);
			return true;
		}
		return false;
	}

	@Override
	public Event update(Integer id, Event event) {
		Optional<Event> optEvent = repo.findById(id);
		Event managed = null;
		if (optEvent.isPresent()) {
			managed = optEvent.get();
			managed.setName(event.getName());
			repo.saveAndFlush(managed);
		}
		return managed;
	}


}
