import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { PagedContent, Property } from '../models/app.interface';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class PropertyService {
    constructor(private _http: HttpClient) { }

    // Search properties
    searchProperties(form: any, page, size) {
        const params = new HttpParams()
            .set('page', page.toString())
            .set('size', size.toString());
        const apiUrl = 'http://localhost:8081/properties/search';
        return this._http.post<PagedContent<Property>>(apiUrl, form, {params});
    }

    // Add new propertz
    addProperties(form: any): Observable<any> {
        const apiUrl = 'http://localhost:8081/properties/user';
        return this._http.post<Number>(apiUrl, form.value);
    }

}