import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  constructor(private http: HttpClient) { }

  searchByOptionalField(field: string, value: string) {
    return this.http.get('http://localhost:8080/api/search/' + field + '/' + value);
  }

  searchByMultipleFields(json: any) {
    return this.http.post('http://localhost:8080/api/search/multipleFields', json);
  }

  searchByMultipleOptionalFields(json: any) {
    return this.http.post('http://localhost:8080/api/search/multipleOptionalFields', json);
  }

  searchMoreLikeThis(id: any) {
    return this.http.get('http://localhost:8080/api/search/moreLikeThis/' + id);
  }

  searchGeoPoint(longitude: Number, latitude: Number) {
    return this.http.get('http://localhost:8080/api/search/geoPoint/' + longitude + "/" + latitude);
  }

  downloadPdf(title: string) {
    let titleStrippedOfHTML = title.replace(/<(?:.|\n)*?>/gm, '');
    let finalTitleWithUnderscoreInsteadOfSpace = titleStrippedOfHTML.replace(' ', '_');

    window.open('http://localhost:8080/api/downloadPdf/' + finalTitleWithUnderscoreInsteadOfSpace);
  }

}
