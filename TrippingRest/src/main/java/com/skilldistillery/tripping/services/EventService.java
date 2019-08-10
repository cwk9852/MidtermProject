package com.skilldistillery.tripping.services;

import java.util.List;

import com.skilldistillery.tripping.entities.Event;

public interface EventService {

	List<Event> index();

	Event getEventById(Integer id);

	Event create(Event event);

	Boolean delete(Integer id);

	Event update(Integer id, Event event);

}