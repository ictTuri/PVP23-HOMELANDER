import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { NgForm } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DetailedUser } from '../../models/app.interface';

@Component({
  selector: 'hl-authentication',
  templateUrl: './authentication.component.html',
  styleUrl: './authentication.component.css'
})
export class AuthenticationComponent implements OnInit {
  user = new BehaviorSubject<DetailedUser>(null);
  isLoading: boolean = false;
  isLogin: boolean = true;
  credential: string = '';
  password: string = '';
  

  constructor(private _userService: UserService,
    private _cookie: CookieService,
    private _toastr: ToastrService,
    private _router: Router) { }

  ngOnInit(): void {

  }

  authenticateUser(form: NgForm): void {
    this.isLoading = true;
    this._userService.authenticate({ credential: form.value.email, password: form.value.password }).subscribe(
      {
        next: responseData => {
          form.reset();
          this.isLoading = false;
          this._toastr.success('Login successfully!');
          this._cookie.set('jwttoken', responseData.token);
          this._userService.getDetailedUserInfo(responseData.email).subscribe({
            next: response => {
              this.user.next(response);
            }
          });
          console.log(this.user);
          this._router.navigate(['/']);
        },
        error:() => {
          this.applyOnError(form);
        }
      });
  }

  registerUser(form: NgForm): void {
    this.isLoading = true;
    this._userService.register({ username: form.value.username, email: form.value.email, password: form.value.password }).subscribe(
      {
        next: () => {
          this._toastr.success('Register complete, proceed to login!');
          this.isLoading = false;
          this.switchToRegister();
        },
        error: () => {
          this.applyOnError(form);
        }
      });
  }

  switchToRegister(): void {
    this.isLogin = !this.isLogin;
  }

  private applyOnError(form: NgForm) {
    form.reset();
    this.isLoading = false;
  }

}
