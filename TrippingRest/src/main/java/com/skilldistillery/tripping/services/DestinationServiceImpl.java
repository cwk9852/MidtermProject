package com.skilldistillery.tripping.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tripping.entities.Destination;
import com.skilldistillery.tripping.repositories.DestinationRepository;

@Service
public class DestinationServiceImpl implements DestinationService {

	@Autowired
	private DestinationRepository repo;

	@Override
	public List<Destination> index() {
		return repo.findAll();
	}

	@Override
	public Destination getDestinationById(Integer id) {
		Optional<Destination> destinationOpt = repo.findById(id);
		if (destinationOpt.isPresent()) {
			Destination destination = destinationOpt.get();
			return destination;
		}
		return null;
	}
	
	@Override
	public Destination create(Destination destination) {
		destination = repo.saveAndFlush(destination);
		return destination;
	}

	@Override
	public Boolean delete(Integer id) {
		Optional<Destination> destinationOpt = repo.findById(id);
		Destination managedDestination = null;
		if (destinationOpt.isPresent()) {
			managedDestination = destinationOpt.get();
			repo.delete(managedDestination);
			return true;
		}
		return false;
	}

	@Override
	public Destination update(Integer id, Destination destination) {
		Optional<Destination> optDestination = repo.findById(id);
		Destination managed = null;
		if (optDestination.isPresent()) {
			managed = optDestination.get();
			managed.setDescription(destination.getDescription());
			repo.saveAndFlush(managed);
		}
		return managed;
	}


}
