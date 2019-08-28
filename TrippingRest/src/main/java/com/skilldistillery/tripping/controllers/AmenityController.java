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

import com.skilldistillery.tripping.entities.Amenity;
import com.skilldistillery.tripping.services.AmenityService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})

public class AmenityController {

	@Autowired
	private AmenityService svc;
	

	@GetMapping(path = "amenities")
	public List<Amenity> getAmenitys() {
		return svc.index();
	}
	
	@GetMapping(path = "amenities/{id}")
	public Amenity getAmenityById(@PathVariable Integer id, HttpServletResponse resp) {
		
		Amenity amenity = svc.getAmenityById(id);
		
		if (amenity == null) {
			resp.setStatus(404);
			return null; 
		}
		
		return amenity;
	}
	
	@DeleteMapping("amenities/{id}")
	public Boolean deleteAmenity(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			svc.delete(id);
			return true;
		} catch (Exception e) {
			resp.setStatus(409);
			return false;
		}
	}
	
	@PostMapping("amenities")
	public Amenity createAmenity(@RequestBody Amenity amenity, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(amenity);
		try {
			svc.create(amenity);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/");
			url.append(amenity.getId());
			String newAddrURL = url.toString();
			resp.addHeader("Location", newAddrURL);
		} catch (Exception e) {
			resp.setStatus(400);
			amenity = null;
		}
		return amenity;
	}
	
	@PutMapping("amenities/{id}")
	public Amenity replaceAmenity(@PathVariable Integer id, @RequestBody Amenity amenity, HttpServletRequest req, HttpServletResponse resp) {
		try {
			amenity = svc.update(id, amenity);
			resp.setStatus(200);
			StringBuffer url = req.getRequestURL();
			String newAddrURL = url.toString();
			resp.addHeader("URL", newAddrURL);
		} catch (Exception e) {
			resp.setStatus(400);
			amenity = null;
		}
		return amenity;
	}
	
}
