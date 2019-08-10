package com.skilldistillery.tripping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tripping.entities.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {


}
