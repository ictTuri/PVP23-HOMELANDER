import { Component } from '@angular/core';

@Component({
  selector: 'hl-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  filterData: any;

  onFilterApplied(data: any): void {
    this.filterData = data;
  }
}
