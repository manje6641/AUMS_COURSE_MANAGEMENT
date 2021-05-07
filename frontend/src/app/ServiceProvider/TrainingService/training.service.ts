import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Course} from '../../models/Course';

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  private backendUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  getAllTraining()
  {
    return this.http.get(`${this.backendUrl}/training/all`);
  }

  addTraining(training)
  {
    return this.http.post(`${this.backendUrl}/training/add`, training);
  }

  deleteTraining(id: number)
  {
    return this.http.delete(`${this.backendUrl}/training/delete/${id}`);
  }

  getTrainingById(id: number)
  {
    return this.http.get(`${this.backendUrl}/training/find/${id}`);
  }

  updateTraining(training)
  {
    return this.http.put(`${this.backendUrl}/training/update`, training);
  }

  assignTraining(session)
  {
    return this.http.post(`${this.backendUrl}/training/assign`, session);
  }

  getAssignedTraining(id)
  {
    return this.http.get(`${this.backendUrl}/training/getAssignedTraining/${id}`);
  }

  givefeedback(id, rating)
  {
    return this.http.get(`${this.backendUrl}/training/feedback/${id}/${rating}`);
  }
}
