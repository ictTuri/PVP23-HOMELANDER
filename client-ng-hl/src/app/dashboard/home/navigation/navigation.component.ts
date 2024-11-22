import { Component, OnInit } from '@angular/core';
import { DetailedUser } from '../../../models/app.interface';
import { UserService } from '../../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'hl-navigation',
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css'
})
export class NavigationComponent implements OnInit {
  user: DetailedUser = null;

  constructor(private _userService: UserService,
    private _router: Router
  ) { }

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

  logout() {
    this._userService.logout();
    location.reload();
  }
}
