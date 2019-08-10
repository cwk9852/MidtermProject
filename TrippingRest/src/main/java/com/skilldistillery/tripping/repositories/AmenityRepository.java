package com.skilldistillery.tripping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tripping.entities.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Integer> {


}
