import { HttpClient } from '@angular/common/http';
import { Destinations } from 'src/app/services/destinations';
import { Component } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { DestinationService } from 'src/app/services/destination.service';
import { Destination } from 'src/app/models/destination';

@Component({
  selector: 'app-destination-dashboard',
  templateUrl: './destination-dashboard.component.html',
  styleUrls: ['./destination-dashboard.component.css']
})
export class DestinationDashboardComponent {

  /** Based on the screen size, switch from standard to one column per row */
  destinations: Destinations = new Destinations(this.svc);
  destArray: Destination[] = [];
  title = 'Tripping';

  selected = null;

  editDestination = null;

  newDestination = new Destination();

  displayedColumns: string[] = ['name', 'shortDescription', 'description', 'image', 'points', 'events', 'reviews'];

  // cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
  //   map(({ matches }) => {
  //     if (matches) {
  //       return [
  //         { title: 'Card 1', cols: 4, rows: 1 },
  //         { title: 'Card 2', cols: 4, rows: 1 },
  //         { title: 'Card 3', cols: 4, rows: 1 },
  //         { title: 'Card 1', cols: 4, rows: 1 },
  //         { title: 'Card 2', cols: 4, rows: 1 },
  //         { title: 'Card 3', cols: 4, rows: 1 },
  //         { title: 'Card 4', cols: 4, rows: 1 }
  //       ];
  //     }
  //     return this.destArray;
  //   })
  // );

  constructor(private breakpointObserver: BreakpointObserver, private svc: DestinationService) {
    this.svc.index().subscribe(
      good => {
        this.destArray = good;
        return good;
      },
      bad => {
        console.error(bad);
      },
      () => {
        console.log(this.destArray);
      }
    );
  }
}
