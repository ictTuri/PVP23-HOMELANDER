import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Area, City, Country, PagedContent, Property } from '../models/app.interface';
import { Observable } from 'rxjs';
import { FormGroup } from '@angular/forms';

@Injectable({
    providedIn: 'root'
})
export class PropertyService {
    constructor(private _http: HttpClient) { }

    // Search properties
    searchProperties(form: any, page: any, size: any) {
        const params = new HttpParams()
            .set('page', page.toString())
            .set('size', size.toString());
        const apiUrl = 'http://localhost:8081/properties/search';
        return this._http.post<PagedContent<Property>>(apiUrl, form, { params });
    }

    // Add new propertz
    addProperties(form: any): Observable<any> {
        const apiUrl = 'http://localhost:8081/properties/user';
        return this._http.post<Number>(apiUrl, form.value);
    }

    getCountries(): Observable<Country[]> {
        const apiUrl = 'http://localhost:8081/properties/search/locations';
        return this._http.get<Country[]>(apiUrl);
    }

    getCitiesByCountry(country: string): Observable<City[]> {
        const apiUrl = 'http://localhost:8081/properties/search/locations/country/';
        return this._http.get<City[]>(apiUrl + country + '/cities');
    }

    getAreasByCity(city: string): Observable<Area[]> {
        const apiUrl = 'http://localhost:8081/properties/search/locations/city/';
        return this._http.get<Area[]>(apiUrl + city + '/areas');
    }
}