package com.skilldistillery.tripping.services;

import java.util.List;

import com.skilldistillery.tripping.entities.Destination;

public interface DestinationService {

	List<Destination> index();

	Destination getDestinationById(Integer id);

	Destination create(Destination destination);

	Boolean delete(Integer id);

	Destination update(Integer id, Destination destination);

}
