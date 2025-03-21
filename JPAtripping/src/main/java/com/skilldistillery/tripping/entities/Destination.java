package com.skilldistillery.tripping.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Destination {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Destination [name=");
		builder.append(name);
		builder.append(", shortDescription=");
		builder.append(shortDescription);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Destination other = (Destination) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Destination(String name, String shortDescription) {
		super();
		this.name = name;
		this.shortDescription = shortDescription;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;

	@Column(name = "short_description")
	private String shortDescription;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "image_url")
	private String image;

	@OneToMany(mappedBy = "destination")
	private List<DestinationReview> destinationReviews;

	@OneToMany(mappedBy = "destination", fetch = FetchType.EAGER)
	private List<PointOfInterest> points;

	@OneToMany(mappedBy = "destination")
	private List<Event> events;

//	INSTEAD OF VOID ID LIKE TO RETURN A STRING TO SHOW THAT ALL ADD/REMOVE WORK
	public void addDestinationReview(DestinationReview review) {
		if (destinationReviews == null) {
			destinationReviews = new ArrayList<>();
		}

		if (!destinationReviews.contains(review)) {
			destinationReviews.add(review);
			review.setDestination(this);
		}
	}

	public void removeDestinationReview(DestinationReview review) {
		if (destinationReviews != null && destinationReviews.contains(review)) {
			destinationReviews.remove(review);
		}
	}

	public void addPointOfInteres(PointOfInterest poi) {
		if (points == null) {
			points = new ArrayList<>();
		}

		if (!points.contains(poi)) {
			points.add(poi);
			poi.setDestination(this);
		}
	}

	public void removePointOfInterest(PointOfInterest poi) {
		if (points != null && points.contains(poi)) {
			points.remove(poi);
		}
	}

	public void addEvent(Event event) {
		if (events == null) {
			events = new ArrayList<>();
		}

		if (!events.contains(event)) {
			events.add(event);
			event.setDestination(this);
		}
	}

	public void removeEvent(Event event) {
		if (events != null && events.contains(event)) {
			events.remove(event);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<DestinationReview> getDestinationReviews() {
		return destinationReviews;
	}

	public void setDestinationReviews(List<DestinationReview> destinationReviews) {
		this.destinationReviews = destinationReviews;
	}

	public List<PointOfInterest> getPoints() {
		return points;
	}

	public void setPoints(List<PointOfInterest> points) {
		this.points = points;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Destination() {
		super();
	}

	public Destination(String name, String shortDescription, String image,
			List<DestinationReview> destinationUserReviews, List<User> users) {
		super();
		this.name = name;
		this.shortDescription = shortDescription;
		this.image = image;
		this.destinationReviews = destinationUserReviews;
	}

}
