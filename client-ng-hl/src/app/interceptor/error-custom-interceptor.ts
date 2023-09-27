import { HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ToastrService } from "ngx-toastr";
import { catchError, throwError } from "rxjs";

@Injectable()
export class ErrorCustomInterceptor implements HttpInterceptor {

    constructor(private _toastr: ToastrService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler) {
        return next.handle(request).pipe(
            catchError((error) => {
                if (!error.error || !error.error.message) {
                    return throwError(() => this._toastr.error(error.error));
                } else {
                    return throwError(() => this._toastr.error(error.error.message));
                }
            })
        );
    }
}