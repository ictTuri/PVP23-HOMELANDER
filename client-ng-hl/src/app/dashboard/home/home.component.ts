import { Component, Input, OnInit } from '@angular/core';
import { PropertyService } from '../../services/property.service';
import { AddPropertyDialogComponent } from './pop-up/add-property-dialog/add-property-dialog.component';
import { Property } from '../../models/app.interface';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'hl-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  data: Property[] = [];
  public totalElements: number[];
  public totalPages: number[];
  public currentPage: number;
  @Input() filterData: any;

  constructor(private _propertyService: PropertyService,
    private _dialog: MatDialog) { }

  ngOnInit(): void {

  }

  addNewProperty() {
    const dialogRef = this._dialog.open(AddPropertyDialogComponent, {
      width: '700px',
      height: '700px'
    });
    dialogRef.afterClosed();
  }
}
