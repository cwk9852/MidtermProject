export class EventReview {
  id: number;
  createDate: string;
  title: string;
  rating: number;
  reviewText: string;

  constructor(
    id?: number,
    createDate?: string,
    title?: string,
    rating?: number,
    reviewText?: string
  ) {
    this.id = id;
    this.createDate = createDate;
    this.title = title;
    this.rating = rating;
    this.reviewText = reviewText;
  }
}
