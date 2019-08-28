import { Injectable } from '@angular/core';
import { Destination } from '../models/destination';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DestinationService {
  private url = 'http://localhost:8091/api/destinations';

  editDestination = null;

  constructor(private http: HttpClient) {}

  destroy(id: string | number) {
    console.log(id);
    return this.http.delete<any>(this.url + '/' + id);
  }

  index(): Observable<Destination[]> {
    return this.http.get<Destination[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'DestinationService.index(): error retrieving destination list'
        );
      })
    );
  // index(filter = '', sortOrder = 'asc',
  //       pageNumber = 0, pageSize = 3) {

  //   return this.http.get('http://localhost:8090/api/destinations', {
  //       params: new HttpParams()
  //           // .set('filter', filter)
  //           // .set('sortOrder', sortOrder)
  //           // .set('pageNumber', pageNumber.toString())
  //           // .set('pageSize', pageSize.toString())
  //   }).pipe(
  //       map(res =>  res['payload'])
  //   );
  }

  create(destination: Destination) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<Destination>(this.url, destination, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'DestinationService.create(): error creating destination'
        );
      })
    );
  }

  update(destination: Destination) {
    console.log(destination);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.put<any>(
      this.url + '/' + destination.id,
      destination,
      httpOptions
    );
  }
}
