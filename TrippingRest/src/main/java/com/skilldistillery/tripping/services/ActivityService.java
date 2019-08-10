package com.skilldistillery.tripping.services;

import java.util.List;

import com.skilldistillery.tripping.entities.Activity;

public interface ActivityService {

	List<Activity> index();

	Activity getActivityById(Integer id);

	Activity create(Activity activity);

	Boolean delete(Integer id);

	Activity update(Integer id, Activity activity);

}