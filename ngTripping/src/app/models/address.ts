export class Address {
  id: number;
  street1: string;
  street2: string;
  city: string;
  state: string;
  zipcode: number;
  phone: string;
  latitude: string;
  longitude: string;
  constructor(
    id?: number,
    street1?: string,
    street2?: string,
    city?: string,
    state?: string,
    zipcode?: number,
    phone?: string,
    latitude?: string,
    longitude?: string
  ) {
    this.id = id;
    this.street1 = street1;
    this.street2 = street2;
    this.city = city;
    this.state = state;
    this.zipcode = zipcode;
    this.phone = phone;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
