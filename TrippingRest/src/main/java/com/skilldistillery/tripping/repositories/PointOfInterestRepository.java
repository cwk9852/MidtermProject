package com.skilldistillery.tripping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tripping.entities.PointOfInterest;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Integer> {


}
