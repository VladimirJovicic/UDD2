import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  constructor(private http: HttpClient) { }

  searchByOptionalField(field: string, value: string) {
    return this.http.get('http://localhost:8080/api/search/' + field + '/' + value)
  }

  searchByMultipleFields(json: any) {
    return this.http.post('http://localhost:8080/api/search/multipleFields', json)
  }

}
