import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Course} from '../../models/Course';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private backendUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  getAllCourse() {
    return this.http.get(`${this.backendUrl}/course/all`);
  }

  // tslint:disable-next-line:typedef
  getCourseById(id: number)
  {
    return this.http.get(`${this.backendUrl}/course/find/${id}`);
  }

  addCourse(course)
  {
    return this.http.post(`${this.backendUrl}/course/add`, course);
  }

  updateCourse(course: Course)
  {
    return this.http.put(`${this.backendUrl}/course/update`, course);
  }

  deleteCourse(id: number)
  {
    return this.http.delete(`${this.backendUrl}/course/delete/${id}`);
  }
}
