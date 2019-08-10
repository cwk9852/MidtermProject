package com.skilldistillery.tripping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tripping.entities.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {


}
