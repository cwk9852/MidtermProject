package com.skilldistillery.tripping.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tripping.entities.Amenity;
import com.skilldistillery.tripping.repositories.AmenityRepository;

@Service
public class AmenityServiceImpl implements AmenityService {

	@Autowired
	private AmenityRepository repo;

	@Override
	public List<Amenity> index() {
		return repo.findAll();
	}

	@Override
	public Amenity getAmenityById(Integer id) {
		Optional<Amenity> amenityOpt = repo.findById(id);
		if (amenityOpt.isPresent()) {
			Amenity amenity = amenityOpt.get();
			return amenity;
		}
		return null;
	}
	
	@Override
	public Amenity create(Amenity amenity) {
		amenity = repo.saveAndFlush(amenity);
		return amenity;
	}

	@Override
	public Boolean delete(Integer id) {
		Optional<Amenity> amenityOpt = repo.findById(id);
		Amenity managedAmenity = null;
		if (amenityOpt.isPresent()) {
			managedAmenity = amenityOpt.get();
			repo.delete(managedAmenity);
			return true;
		}
		return false;
	}

	@Override
	public Amenity update(Integer id, Amenity amenity) {
		Optional<Amenity> optAmenity = repo.findById(id);
		Amenity managed = null;
		if (optAmenity.isPresent()) {
			managed = optAmenity.get();
			managed.setName(amenity.getName());
			repo.saveAndFlush(managed);
		}
		return managed;
	}


}
