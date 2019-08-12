package com.skilldistillery.tripping.services;

import java.util.List;

import com.skilldistillery.tripping.entities.PointOfInterest;

public interface PointOfInterestService {

	List<PointOfInterest> index();

	PointOfInterest getPointOfInterestById(Integer id);

	PointOfInterest create(PointOfInterest point);

	Boolean delete(Integer id);

	PointOfInterest update(Integer id, PointOfInterest point);

}