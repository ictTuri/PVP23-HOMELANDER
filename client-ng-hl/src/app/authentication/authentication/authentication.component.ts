import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Store } from '@ngrx/store';
import { login } from '../../store/user/user.actions';
import { LoginUser } from '../../models/app.interface';
import { selectIsLoading } from '../../store/user/user.selectors';

@Component({
  selector: 'hl-authentication',
  templateUrl: './authentication.component.html',
  styleUrl: './authentication.component.css'
})
export class AuthenticationComponent implements OnInit {
  isLoading: boolean = false;
  isLogin: boolean = true;
  credential: string = '';
  password: string = '';


  constructor(private _userService: UserService,
    private _toastr: ToastrService,
    private _store: Store) {
    this._store.select(selectIsLoading).subscribe(isLoading => (this.isLoading = isLoading));
  }

  ngOnInit(): void {

  }

  authenticateUser(form: NgForm): void {
    const loginUser = buildUser(form.value.email, form.value.password);
    console.log('dispatching login');
    this._store.dispatch(login({ credentials: loginUser }));
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
function buildUser(email: any, password: any) {
  const user: LoginUser = { credential: email, password: password };
  return user;
}

