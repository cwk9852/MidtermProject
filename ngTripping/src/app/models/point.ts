import { Address } from './address';
import { Amenity } from './amenity';
import { Activity } from './activity';
import { PointComment } from './point-comment';

export class Point {
  id: number;
  name: string;
  address: Address;
  shortDescription: string;
  longDescription: string;
  amenities: Amenity[];
  activities: Activity[];
  comments: PointComment[];

  constructor(
    id?: number,
    name?: string,
    address?: Address,
    shortDescription?: string,
    longDescription?: string,
    amenities?: Amenity[],
    activities?: Activity[],
    comments?: PointComment[]
  ) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
    this.amenities = amenities;
    this.activities = activities;
    this.comments = comments;
  }
}
