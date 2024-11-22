import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { DetailedUser, LoginUser, RegisterUser, ResponseDto, Token } from "../models/app.interface";
import { environment } from "../../environments/environments";
import { CookieService } from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUserUrl = environment.restUrl + '/users';

  constructor(private _http: HttpClient,
    private _cookie: CookieService) { }

  // Authenticate User
  authenticate(user: LoginUser) {
    return this._http.post<Token>(this.baseUserUrl + '/auth/_login', user);
  }

  // Register User
  register(user: RegisterUser) {
    return this._http.post<ResponseDto>(this.baseUserUrl + '/auth/register', user);
  }

  // Detailed User
  getDetailedUserInfo(email: string) {
    return this._http.get<DetailedUser>(this.baseUserUrl + '/user/get-full-user-context/' + email);  
  }

  logout() {
    this._cookie.delete('jwttoken');
    this._cookie.delete('email');
  }

  getTokenFromCookie(): string {
    return this._cookie.get('jwttoken');
  }

  existCookie(): boolean {
    return !!this._cookie.get('jwttoken') && !!this._cookie.get('email');
  }

  getEmailFromCookie(): string {
    return this._cookie.get('email');
  }
}