package com.skilldistillery.tripping.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tripping.entities.Event;
import com.skilldistillery.tripping.services.EventService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4203"})

public class EventController {

	@Autowired
	private EventService svc;
	

	@GetMapping(path = "events")
	public List<Event> getEvents() {
		return svc.index();
	}
	
	@GetMapping(path = "events/{id}")
	public Event getEventById(@PathVariable Integer id, HttpServletResponse resp) {
		
		Event event = svc.getEventById(id);
		
		if (event == null) {
			resp.setStatus(404);
			return null; 
		}
		
		return event;
	}
	
	@DeleteMapping("events/{id}")
	public Boolean deleteEvent(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			svc.delete(id);
			return true;
		} catch (Exception e) {
			resp.setStatus(409);
			return false;
		}
	}
	
	@PostMapping("events")
	public Event createEvent(@RequestBody Event event, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(event);
		try {
			svc.create(event);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/");
			url.append(event.getId());
			String newAddrURL = url.toString();
			resp.addHeader("Location", newAddrURL);
		} catch (Exception e) {
			resp.setStatus(400);
			event = null;
		}
		return event;
	}
	
	@PutMapping("events/{id}")
	public Event replaceEvent(@PathVariable Integer id, @RequestBody Event event, HttpServletRequest req, HttpServletResponse resp) {
		try {
			event = svc.update(id, event);
			resp.setStatus(200);
			StringBuffer url = req.getRequestURL();
			String newAddrURL = url.toString();
			resp.addHeader("URL", newAddrURL);
		} catch (Exception e) {
			resp.setStatus(400);
			event = null;
		}
		return event;
	}
	
}
