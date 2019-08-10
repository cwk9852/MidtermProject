package com.skilldistillery.tripping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tripping.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {


}
