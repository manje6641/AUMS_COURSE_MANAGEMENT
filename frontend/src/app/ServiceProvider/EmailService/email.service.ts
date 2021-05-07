import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Email} from '../../models/Email';

@Injectable({
  providedIn: 'root'
})
export class EmailService {
  private backendUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  sendEmail(email)
  {
    return this.http.post(`${this.backendUrl}/training/sendmail`, email);
  }

}
