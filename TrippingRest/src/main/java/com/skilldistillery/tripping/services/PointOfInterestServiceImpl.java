package com.skilldistillery.tripping.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tripping.entities.PointOfInterest;
import com.skilldistillery.tripping.repositories.PointOfInterestRepository;

@Service
public class PointOfInterestServiceImpl implements PointOfInterestService {

	@Autowired
	private PointOfInterestRepository repo;

	@Override
	public List<PointOfInterest> index() {
		return repo.findAll();
	}

	@Override
	public PointOfInterest getPointOfInterestById(Integer id) {
		Optional<PointOfInterest> pointOpt = repo.findById(id);
		if (pointOpt.isPresent()) {
			PointOfInterest point = pointOpt.get();
			return point;
		}
		return null;
	}
	
	@Override
	public PointOfInterest create(PointOfInterest point) {
		point = repo.saveAndFlush(point);
		return point;
	}

	@Override
	public Boolean delete(Integer id) {
		Optional<PointOfInterest> pointOpt = repo.findById(id);
		PointOfInterest managedPointOfInterest = null;
		if (pointOpt.isPresent()) {
			managedPointOfInterest = pointOpt.get();
			repo.delete(managedPointOfInterest);
			return true;
		}
		return false;
	}

	@Override
	public PointOfInterest update(Integer id, PointOfInterest point) {
		Optional<PointOfInterest> optPointOfInterest = repo.findById(id);
		PointOfInterest managed = null;
		if (optPointOfInterest.isPresent()) {
			managed = optPointOfInterest.get();
			managed.setName(point.getName());
			repo.saveAndFlush(managed);
		}
		return managed;
	}


}
