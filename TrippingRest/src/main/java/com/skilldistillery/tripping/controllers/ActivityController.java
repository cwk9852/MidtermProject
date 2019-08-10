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

import com.skilldistillery.tripping.entities.Activity;
import com.skilldistillery.tripping.services.ActivityService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4203"})

public class ActivityController {

	@Autowired
	private ActivityService svc;
	

	@GetMapping(path = "activities")
	public List<Activity> getActivitys() {
		return svc.index();
	}
	
	@GetMapping(path = "activities/{id}")
	public Activity getActivityById(@PathVariable Integer id, HttpServletResponse resp) {
		
		Activity activity = svc.getActivityById(id);
		
		if (activity == null) {
			resp.setStatus(404);
			return null; 
		}
		
		return activity;
	}
	
	@DeleteMapping("activities/{id}")
	public Boolean deleteActivity(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			svc.delete(id);
			return true;
		} catch (Exception e) {
			resp.setStatus(409);
			return false;
		}
	}
	
	@PostMapping("activities")
	public Activity createActivity(@RequestBody Activity activity, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(activity);
		try {
			svc.create(activity);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/");
			url.append(activity.getId());
			String newAddrURL = url.toString();
			resp.addHeader("Location", newAddrURL);
		} catch (Exception e) {
			resp.setStatus(400);
			activity = null;
		}
		return activity;
	}
	
	@PutMapping("activities/{id}")
	public Activity replaceActivity(@PathVariable Integer id, @RequestBody Activity activity, HttpServletRequest req, HttpServletResponse resp) {
		try {
			activity = svc.update(id, activity);
			resp.setStatus(200);
			StringBuffer url = req.getRequestURL();
			String newAddrURL = url.toString();
			resp.addHeader("URL", newAddrURL);
		} catch (Exception e) {
			resp.setStatus(400);
			activity = null;
		}
		return activity;
	}
	
}
