import { EventReview } from './event-review';
import { EventImage } from './event-image';

export class Event {
  id: number;
  reviews: EventReview[];
  images: EventImage[];
  name: string;
  shortDescription: string;
  longDescription: string;
  startDate: string;
  endDate: string;
  eventDetails: string;

  constructor(
    id?: number,
    reviews?: EventReview[],
    images?: EventImage[],
    name?: string,
    shortDescription?: string,
    longDescription?: string,
    startDate?: string,
    endDate?: string,
    eventDetails?: string
  ) {
    this.id = id;
    this.reviews = reviews;
    this.images = images;
    this.name = name;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
    this.startDate = startDate;
    this.endDate = endDate;
    this.eventDetails = eventDetails;
  }
}
