import { Component, OnInit } from '@angular/core';
import {TrainingService} from '../../ServiceProvider/TrainingService/training.service';
import {TrainingComponent} from '../training/training.component';
import {MatDialogRef} from '@angular/material/dialog';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {apiResponse} from '../../models/apiResponse';
import {CourseService} from '../../ServiceProvider/CourseService/course.service';
import {UserService} from '../../ServiceProvider/UserService/user.service';

@Component({
  selector: 'app-add-training',
  templateUrl: './add-training.component.html',
  styleUrls: ['./add-training.component.css']
})
export class AddTrainingComponent implements OnInit {

  trainingForm = new FormGroup({
    courseId: new FormControl('', Validators.required),
    instructorId: new FormControl('', Validators.required),
  });
  constructor(private trainingService: TrainingService, public dialogRef: MatDialogRef<TrainingComponent>) { }

  ngOnInit(): void {

  }

  addTraining() {
    console.log(this.trainingForm.value);
    this.trainingService.addTraining(this.trainingForm.value).subscribe((response: apiResponse) => {
      if (response.result === 'Success')
      {
        this.dialogRef.close();
      }
      else {
        alert('Error Occurred! Check whether course id and instructor id is present');
        this.dialogRef.close();
      }
    });
  }
}
