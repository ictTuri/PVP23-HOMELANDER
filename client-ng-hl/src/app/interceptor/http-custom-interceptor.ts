import { HttpEvent, HttpHandler, HttpInterceptor, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, } from 'rxjs';
import { UserService } from '../services/user.service';


@Injectable()
export class HttpCustomInterceptor implements HttpInterceptor {

  constructor(private _userService: UserService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    var token = this._userService.getTokenFromCookie();
    req = req.clone({
      setHeaders: { Authorization: `Bearer ${token}` },
      withCredentials: true
    });
    return next.handle(req);
  }
}