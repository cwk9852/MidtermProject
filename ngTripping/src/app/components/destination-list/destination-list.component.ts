import { Destinations } from './../../services/destinations';
import { DestinationService } from './../../services/destination.service';
import { NgForm } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Destination } from 'src/app/models/destination';
import {MatPaginator} from '@angular/material/paginator';
import {PageEvent} from '@angular/material/paginator';

@Component({
  selector: 'app-destination-list',
  templateUrl: './destination-list.component.html',
  styleUrls: ['./destination-list.component.css']
})
export class DestinationListComponent implements OnInit {
  title = 'Tripping';

  selected = null;

  editDestination = null;

  newDestination = new Destination();

  displayedColumns: string[] = ['name', 'shortDescription', 'description', 'image', 'points', 'events', 'reviews'];

  destinations: Destinations;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

  constructor(private svc: DestinationService) {}

  ngOnInit() {
    this.load();
  }

  load() {
    this.destinations = new Destinations(this.svc);
    this.destinations.load();
  }

  getNumberOfDestinations = function() {
    return this.destinations.length;
  };

  displayDestination = function(destination: Destination) {
    this.selected = destination;
  };

  displayTable = function() {
    this.selected = null;
    this.editDestination = null;
    this.load();
  };

  log(phrase) {
    console.log(phrase);
  }

  addDestination() {
    this.svc.create(this.newDestination).subscribe(
      good => {
        console.log(good);
      },
      bad => {
        console.error(bad);
      },
      () => {
        this.newDestination = new Destination();
        this.load();
      }
    );
  }

  saveCheck(destination: Destination) {
    this.svc.update(destination).subscribe(
      good => {
        console.log('change success');
      },
      bad => {
        console.error(bad);
      },
      () => {
        this.load();
      }
    );
    this.load();
    this.editDestination = null;
    this.selected = null;
  }

  setEditDestination() {
    this.editDestination = Object.assign({}, this.selected);
  }

  saveEdit() {
    this.svc.update(this.editDestination).subscribe(
      good => {
        console.log('update good');
      },
      bad => {
        console.error(bad);
      },
      () => {
        this.newDestination = new Destination();
        this.load();
      }
    );
    this.load();
    this.editDestination = null;
    this.selected = null;
  }

  deleteDestination(id: number) {
    this.svc.destroy(id).subscribe(
      good => {
        console.log('Delete Success');
      },
      bad => {
        console.error(bad);
      },
      () => {
        this.load();
        this.editDestination = null;
        this.selected = null;
      }
    );
  }

}
