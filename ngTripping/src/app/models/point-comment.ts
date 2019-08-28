import { Identifiers } from '@angular/compiler';
export class PointComment {
  id: number;
  commentText: string;
  rating: number;
  commentDate: string;

  constructor(
    id?: number,
    commentText?: string,
    rating?: number,
    commentDate?: string
  ) {
    this.id = id;
    this.commentText = commentText;
    this.rating = rating;
    this.commentDate = commentDate;
  }
}
