import {Component, Inject, OnInit, Optional} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ViewCoursesComponent} from '../view-courses/view-courses.component';
import {CourseService} from '../../ServiceProvider/CourseService/course.service';
import {TrainingComponent} from '../training/training.component';
import {TrainingService} from '../../ServiceProvider/TrainingService/training.service';
import {apiResponse} from '../../models/apiResponse';
import {any} from 'codelyzer/util/function';

@Component({
  selector: 'app-update-training',
  templateUrl: './update-training.component.html',
  styleUrls: ['./update-training.component.css']
})
export class UpdateTrainingComponent implements OnInit {

  trainingId: any;
  training: any;
  trainingForm = new FormGroup({
    courseId: new FormControl('', Validators.required),
    instructorId: new FormControl('', Validators.required),
    trainingId: new FormControl('', Validators.required),
  });
  constructor(public dialogRef: MatDialogRef<TrainingComponent>,
              @Optional() @Inject(MAT_DIALOG_DATA) public data: number, private trainingService: TrainingService) { }

  ngOnInit(): void {
    this.trainingId = this.data;
    this.trainingService.getTrainingById(this.trainingId).subscribe((response: apiResponse) => {
      console.log(response);
      this.training = response.data;
      this.trainingForm.setValue({
        courseId: response.data.courseId,
        instructorId: response.data.instructorId,
        trainingId: this.trainingId,
      });
    });
  }

  updateTraining(){
    console.log(this.trainingForm.value);
    this.trainingService.updateTraining(this.trainingForm.value).subscribe((response: apiResponse) => {
      console.log(response);
      if (response.result === 'Success')
      {
        this.dialogRef.close();
      }
      else {
        alert('Error Occurred');
      }
    });
  }

}
