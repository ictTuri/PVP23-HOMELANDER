import { AddPropertyDialogComponent } from './pop-up/add-property-dialog/add-property-dialog.component';
import { DetailedUser, Property } from '../../models/app.interface';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'hl-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  user: DetailedUser = null;

  constructor(private _userService: UserService,
    private _dialog: MatDialog) { }

  data: Property[] = [];
  public totalElements: number[];
  public totalPages: number[];
  public currentPage: number;
  filterData: any;

  onFilterApplied(data: any) {
    this.filterData = data;
  }

  ngOnInit(): void {
    if (this._userService.existCookie()) {
      var email = this._userService.getEmailFromCookie();
      this._userService.getDetailedUserInfo(email).subscribe({
        next: responseData => {
          this.user = responseData;

        },
        error: (e) => {
          console.log(e);
        }
      })
    }
  }

  addNewProperty() {
    const dialogRef = this._dialog.open(AddPropertyDialogComponent, {
      width: '700px',
      height: '700px'
    });
    dialogRef.afterClosed();
  }
}
