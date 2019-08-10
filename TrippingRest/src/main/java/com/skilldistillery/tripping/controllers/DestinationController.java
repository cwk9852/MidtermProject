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

import com.skilldistillery.tripping.entities.Destination;
import com.skilldistillery.tripping.services.DestinationService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4203"})

public class DestinationController {

	@Autowired
	private DestinationService svc;
	
	@GetMapping(path = "ping")
	public String getPong() {
		return "pong";
	}

	@GetMapping(path = "destinations")
	public List<Destination> getDestinations() {
		return svc.index();
	}
	
	@GetMapping(path = "destinations/{id}")
	public Destination getDestinationById(@PathVariable Integer id, HttpServletResponse resp) {
		
		Destination destination = svc.getDestinationById(id);
		
		if (destination == null) {
			resp.setStatus(404);
			return null; 
		}
		
		return destination;
	}
	
	@DeleteMapping("destinations/{id}")
	public Boolean deleteDestination(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			svc.delete(id);
			return true;
		} catch (Exception e) {
			resp.setStatus(409);
			return false;
		}
	}
	
	@PostMapping("destinations")
	public Destination createDestination(@RequestBody Destination destination, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(destination);
		try {
			svc.create(destination);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/");
			url.append(destination.getId());
			String newAddrURL = url.toString();
			resp.addHeader("Location", newAddrURL);
		} catch (Exception e) {
			resp.setStatus(400);
			destination = null;
		}
		return destination;
	}
	
	@PutMapping("destinations/{id}")
	public Destination replaceDestination(@PathVariable Integer id, @RequestBody Destination destination, HttpServletRequest req, HttpServletResponse resp) {
		try {
			destination = svc.update(id, destination);
			resp.setStatus(200);
			StringBuffer url = req.getRequestURL();
			String newAddrURL = url.toString();
			resp.addHeader("URL", newAddrURL);
		} catch (Exception e) {
			resp.setStatus(400);
			destination = null;
		}
		return destination;
	}
	
}
