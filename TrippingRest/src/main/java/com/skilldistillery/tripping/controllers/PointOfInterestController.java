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

import com.skilldistillery.tripping.entities.PointOfInterest;
import com.skilldistillery.tripping.services.PointOfInterestService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})

public class PointOfInterestController {

	@Autowired
	private PointOfInterestService svc;
	

	@GetMapping(path = "points")
	public List<PointOfInterest> getPointOfInterests() {
		return svc.index();
	}
	
	@GetMapping(path = "points/{id}")
	public PointOfInterest getPointOfInterestById(@PathVariable Integer id, HttpServletResponse resp) {
		
		PointOfInterest point = svc.getPointOfInterestById(id);
		
		if (point == null) {
			resp.setStatus(404);
			return null; 
		}
		
		return point;
	}
	
	@DeleteMapping("points/{id}")
	public Boolean deletePointOfInterest(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			svc.delete(id);
			return true;
		} catch (Exception e) {
			resp.setStatus(409);
			return false;
		}
	}
	
	@PostMapping("points")
	public PointOfInterest createPointOfInterest(@RequestBody PointOfInterest point, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(point);
		try {
			svc.create(point);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/");
			url.append(point.getId());
			String newAddrURL = url.toString();
			resp.addHeader("Location", newAddrURL);
		} catch (Exception e) {
			resp.setStatus(400);
			point = null;
		}
		return point;
	}
	
	@PutMapping("points/{id}")
	public PointOfInterest replacePointOfInterest(@PathVariable Integer id, @RequestBody PointOfInterest point, HttpServletRequest req, HttpServletResponse resp) {
		try {
			point = svc.update(id, point);
			resp.setStatus(200);
			StringBuffer url = req.getRequestURL();
			String newAddrURL = url.toString();
			resp.addHeader("URL", newAddrURL);
		} catch (Exception e) {
			resp.setStatus(400);
			point = null;
		}
		return point;
	}
	
}
