package com.skilldistillery.tripping.services;

import java.util.List;

import com.skilldistillery.tripping.entities.Amenity;

public interface AmenityService {

	List<Amenity> index();

	Amenity getAmenityById(Integer id);

	Amenity create(Amenity amenity);

	Boolean delete(Integer id);

	Amenity update(Integer id, Amenity amenity);

}