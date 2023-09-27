import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Property } from '../../models/app.interface';
import { PropertyService } from '../../services/property.service';

@Component({
  selector: 'hl-properties-list',
  templateUrl: './properties-list.component.html',
  styleUrl: './properties-list.component.css'
})
export class PropertiesListComponent implements OnChanges {
  @Input() filterData: any;
  data: Property[] = [];
  public totalElements: number[];
  public totalPages: number[];
  public currentPage: number;

  constructor(private _propertyService: PropertyService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.filterData && changes.filterData.currentValue) {
      this.searchProperties(0, 20);
    }
  }

  private searchProperties(page, size) {
    this._propertyService.searchProperties(this.filterData, page, size).subscribe(
      {
        next: responseData => {
          this.data = responseData.content;
          this.totalPages = Array.from(Array(responseData.totalPages), (x, i) => i);
          this.totalElements = Array.from(Array(responseData.totalElements), (x, i) => i);
          this.currentPage = responseData.pageable.pageNumber;
        },
        error: error => {
          console.error('Error fetching properties:', error);
        }
      });
  }

  getPage(page) {
    this.searchProperties(page, 20);
  }
}
