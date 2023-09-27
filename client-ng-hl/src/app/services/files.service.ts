import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class FilesService {

    constructor(private _http: HttpClient) { }

    upload(file: File): Observable<any> {
        const apiUrl = 'http://localhost:8081/files/user/property';
        const formData: FormData = new FormData();
        formData.append('pt_file', file);
        return this._http.post<any>(apiUrl, formData);
    }
}