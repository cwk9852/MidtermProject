import { DestinationReview } from './destination-review';
import { Point } from './point';
import { Event } from './event';

export class Destination {
  id: number;
  name: string;
  shortDescription: string;
  description: string;
  image: string;
  destinationReviews: DestinationReview[];
  points: Point[];
  events: Event[];

constructor(id?: number,
            name?: string,
            shortDescription?: string,
            description?: string,
            image?: string,
            destinationReviews?: DestinationReview[],
            points?: Point[],
            events?: Event[],
  ) {
    this.id = id;
    this.name = name;
    this.shortDescription = shortDescription;
    this.description = description;
    this.image = image;
    this.destinationReviews = destinationReviews;
    this.points = points;
    this.events = events;
  }

}
