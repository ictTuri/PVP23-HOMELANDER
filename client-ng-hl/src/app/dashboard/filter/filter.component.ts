import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Cities } from './dropdown-values.data';

@Component({
  selector: 'hl-filter',
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent implements OnInit{

  @Output() filterApplied = new EventEmitter<any>();

  cities = Cities;
  filter = {
    owner: null,
    forSale: null,
    forRent: null,
    typology: null,
    rooms: null,
    city: null
  }

  ngOnInit(): void {
    this.filterApplied.emit(this.filter);
  }

  applyFilter() {
    const newFilter = { ...this.filter}
    this.filterApplied.emit(newFilter);
  }

  setPropertyType(event: any) {
    this.filter.typology = event.target.value;
  }

  setRooms(event: any) {
    this.filter.rooms = event.target.value;
  }

  setCity(event: any) {
    this.filter.city = event.target.value;
  }
}
