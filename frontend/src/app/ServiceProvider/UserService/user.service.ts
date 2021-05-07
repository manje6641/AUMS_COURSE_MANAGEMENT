import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../../models/User';
import {apiResponse} from '../../models/apiResponse';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private backendUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getAllUser() {
    return this.http.get(`${this.backendUrl}/user/all`);
  }

  getUserById(id: number) {
    return this.http.get(`${this.backendUrl}/user/find/${id}`);
  }

  getAllInstructor()
  {
    return this.http.get(`${this.backendUrl}/user/allInstructor`);
  }

  addUser(user) {
    return this.http.post(`${this.backendUrl}/user/add`, user);
  }

  updateUser(user: User) {
    return this.http.put(`${this.backendUrl}/user/update`, user);
  }

  deleteUser(id: number) {
    return this.http.delete(`${this.backendUrl}/user/delete/${id}`);
  }

  getUserByEmail(email)
  {
    return this.http.get(`${this.backendUrl}/user/findByEmail/${email}`);
  }
}
