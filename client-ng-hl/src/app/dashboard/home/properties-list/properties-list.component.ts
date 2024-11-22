import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { PagedContent, Property } from '../../../models/app.interface';
import { PropertyService } from '../../../services/property.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'hl-properties-list',
  templateUrl: './properties-list.component.html',
  styleUrl: './properties-list.component.css'
})
export class PropertiesListComponent implements OnChanges, OnInit {
  @Input() filterData: FormGroup;
  data: Property[] = [];
  public totalElements: number[];
  public totalPages: number[];
  public currentPage: number;
  isLoading: boolean = false;
  hasMultiplePages: boolean = false;

  constructor(private _propertyService: PropertyService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes.filterData && changes.filterData.currentValue){
      this.searchProperties(0, 20);
    }
  }
  
  ngOnInit(): void {
    this.isLoading = true;
  }


  private searchProperties(page, size) {
    this._propertyService.searchProperties(this.filterData, page, size).subscribe(
      {
        next: responseData => {
          this.updatePageData(responseData);
        },
        error: error => {
          this.isLoading = false;
          console.error('Error fetching properties:', error);
        }
      });
  }

  private updatePageData(responseData: PagedContent<Property>) {
    this.data = responseData.content;
    this.totalPages = Array.from(Array(responseData.totalPages), (x, i) => i);
    this.totalElements = Array.from(Array(responseData.totalElements), (x, i) => i);
    this.hasMultiplePages = this.totalPages.length > 1;
    this.currentPage = responseData.pageable.pageNumber;
    this.isLoading = false;
  }

  getPage(page) {
    this._propertyService.searchProperties(this.filterData, page, 5);
  }
}
