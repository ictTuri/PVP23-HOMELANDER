import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { DetailedUser } from '../models/app.interface';
import { FormControl, FormGroup } from '@angular/forms';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { TokenState } from '../store/user/user.reducer';
import { selectToken } from '../store/user/user.selectors';
import { MatDialog } from '@angular/material/dialog';
import { UploadimageComponent } from './upload-image/uploadimage/uploadimage.component';

@Component({
  selector: 'hl-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
  userForm: FormGroup;
  user$: Observable<TokenState>;
  editMode: boolean = false;
  user: DetailedUser = null;

  constructor(
    private _dialog: MatDialog,
    private _userService: UserService,
    private _store: Store<{user: TokenState}>) {
      this.user$ = _store.select(selectToken);
    }



  ngOnInit(): void {
    var email = this._userService.getEmailFromCookie();
    this._userService.getDetailedUserInfo(email).subscribe({
      next: responseData => {
        this.user = responseData;
        this.userForm = this.preloadFormData(responseData);
      },
      error: (e) => {
        console.log(e);
      }
    })
  }

  onSubmit() {
    console.log(this.userForm.value);
  }

  toggleEditMode(): void {
    this.editMode = !this.editMode;
    this.userForm = this.preloadFormData(this.user);
  }

  preloadFormData(x: any): FormGroup {
    return new FormGroup({
      email: new FormControl(x.email),
      phone: new FormControl(x.phone),
      country: new FormControl(x.country),
      city: new FormControl(x.city)
    });
  }

  addProfileImage() {
    const dialogRef = this._dialog.open(UploadimageComponent, {
      width: '500px',
      height: '500px',
      data: {
        image: this.user.profileImage
      }
    });
    dialogRef.afterClosed();
  }

}
