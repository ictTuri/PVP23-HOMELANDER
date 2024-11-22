import { Injectable } from '@angular/core';
import { Router, RouterStateSnapshot } from '@angular/router';
import { UserService } from '../../services/user.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuardService {

    constructor(
        private _route: Router,
        private _userService: UserService
    ) { }

    canActivate(state: RouterStateSnapshot): boolean {
        if (this._userService.existCookie()) {
            return true;
        }
        this._route.navigate(['/auth'], { queryParams: { requested: state.url } });
        return false;
    }
}