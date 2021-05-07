import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TrendService {

  private backendUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  getTypeOfUser() {
    return this.http.get(`${this.backendUrl}/trend/typeofuser`);
  }

  getCourseByLocation(){
    return this.http.get(`${this.backendUrl}/trend/courseByLocation`);
  }

  getCourseByYear(){
    return this.http.get(`${this.backendUrl}/trend/courseByYear`);
  }

  getStudentByLocation(){
    return this.http.get(`${this.backendUrl}/trend/studentByLocation`);
  }

  getFileByType(){
    return this.http.get(`${this.backendUrl}/trend/fileByType`);
  }
}
