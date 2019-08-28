export class Activity {
  id: number;
  name: string;
  shortDescription: string;
  longDescription: string;
  imageUrl: string;
  iconUrl: string;

  constructor(
    id?: number,
    name?: string,
    shortDescription?: string,
    longDescription?: string,
    imageUrl?: string,
    iconUrl?: string
  ) {
    this.id = id;
    this.name = name;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
    this.imageUrl = imageUrl;
    this.iconUrl = iconUrl;
  }
}
