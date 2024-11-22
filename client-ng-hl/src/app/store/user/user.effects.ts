import { UserService } from "../../services/user.service";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { exhaustMap, map, tap } from "rxjs";
import { ToastrService } from "ngx-toastr";
import { CookieService } from "ngx-cookie-service";
import { Router } from "@angular/router";
import { Injectable } from "@angular/core";
import { login, loginSuccess } from "./user.actions";

@Injectable()
export class UserEffect {
    constructor(
        private _userService: UserService,
        private _toastr: ToastrService,
        private _cookie: CookieService,
        private _router: Router,
        private _action$: Actions
    ) { }

    login$ = createEffect(() => 
        this._action$.pipe(
            ofType(login), 
            exhaustMap((action) => {
                return this._userService.authenticate(action.credentials).pipe(
                    map((data) => {
                        return loginSuccess({user: data});
                    })
                )
            })
        ), { useEffectsErrorHandler: false, dispatch: true });



    loginSuccess$ = createEffect(() =>
        this._action$.pipe(
            ofType(loginSuccess),
            tap((action) => {
                this._toastr.success('Login successfully!');
                this._cookie.set('jwttoken', action.user.token);
                this._cookie.set('email', action.user.email);
                this._router.navigate(['/']);
            })
        ),{ useEffectsErrorHandler: false, dispatch: false });

}