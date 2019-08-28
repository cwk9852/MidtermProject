export class Amenity {
  id: number;
  name: string;
  shortDescription: string;
  longDescription: string;
  iconUrl: string;

  constructor(
    id?: number,
    name?: string,
    shortDescription?: string,
    longDescription?: string,
    iconUrl?: string
  ) {
    this.id = id;
    this.name = name;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
    this.iconUrl = iconUrl;
  }
}
