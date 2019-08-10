package com.skilldistillery.tripping.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tripping.entities.Activity;
import com.skilldistillery.tripping.repositories.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository repo;

	@Override
	public List<Activity> index() {
		return repo.findAll();
	}

	@Override
	public Activity getActivityById(Integer id) {
		Optional<Activity> activityOpt = repo.findById(id);
		if (activityOpt.isPresent()) {
			Activity activity = activityOpt.get();
			return activity;
		}
		return null;
	}
	
	@Override
	public Activity create(Activity activity) {
		activity = repo.saveAndFlush(activity);
		return activity;
	}

	@Override
	public Boolean delete(Integer id) {
		Optional<Activity> activityOpt = repo.findById(id);
		Activity managedActivity = null;
		if (activityOpt.isPresent()) {
			managedActivity = activityOpt.get();
			repo.delete(managedActivity);
			return true;
		}
		return false;
	}

	@Override
	public Activity update(Integer id, Activity activity) {
		Optional<Activity> optActivity = repo.findById(id);
		Activity managed = null;
		if (optActivity.isPresent()) {
			managed = optActivity.get();
			managed.setName(activity.getName());
			repo.saveAndFlush(managed);
		}
		return managed;
	}


}
