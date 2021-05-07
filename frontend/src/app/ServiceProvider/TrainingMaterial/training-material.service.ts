import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaderResponse, HttpHeaders} from '@angular/common/http';
import {Course} from '../../models/Course';

@Injectable({
  providedIn: 'root'
})
export class TrainingMaterialService {

  private backendUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }


  addTrainingMaterial(file: File, id)
  {
    const formData: FormData = new FormData();
    formData.append('file', file);
    const headers =  {headers: new  HttpHeaders({ enctype: 'multipart/form-data'})};
    return this.http.post(`${this.backendUrl}/trainingMaterial/add/${id}`, formData);
  }

  getAllTrainingMaterialById(id)
  {
    return this.http.get(`${this.backendUrl}/trainingMaterial/find/${id}`);
  }

  deleteMaterial(id)
  {
    return this.http.delete(`${this.backendUrl}/trainingMaterial/delete/${id}`);
  }

  updateMaterial(file, id)
  {
    const formData: FormData = new FormData();
    formData.append('file', file);
    const headers =  {headers: new  HttpHeaders({ enctype: 'multipart/form-data'})};
    return this.http.put(`${this.backendUrl}/trainingMaterial/update/${id}`, formData);
  }


}
