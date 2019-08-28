import {CollectionViewer, DataSource} from '@angular/cdk/collections';
import { Destination } from '../models/destination';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { DestinationService } from './destination.service';
import { catchError, finalize } from 'rxjs/operators';

export class Destinations implements DataSource<Destination> {
  private destinationSubject = new BehaviorSubject<Destination[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  private length = null;
  private destinations: Observable<Destination[]> = null;
  public loading$ = this.loadingSubject.asObservable();
  constructor(private svc: DestinationService) {}

  connect(collectionViewer: CollectionViewer): Observable<Destination[]> {
      return this.destinationSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
      this.destinationSubject.complete();
      this.loadingSubject.complete();
  }

  load(destinationId?: number, filter = '',
       sortDirection = 'asc', pageIndex = 0, pageSize = 3) {

      this.loadingSubject.next(true);

      this.svc.index().pipe(
          catchError(() => of([])),
          finalize(() => this.loadingSubject.next(false))
      ).subscribe(data => { this.destinationSubject.next(data),
        this.length = data.length + ' Destinations'; }
      );
      // this.svc.index().pipe(
      //     catchError(() => of([])),
      //     finalize(() => this.loadingSubject.next(false))
      // ).subscribe(data =>
      // );
  }
}
