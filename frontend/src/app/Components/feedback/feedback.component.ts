import {Component, Inject, OnInit, Optional} from '@angular/core';
import {TrainingMaterialService} from '../../ServiceProvider/TrainingMaterial/training-material.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {AssignedComponent} from '../assigned/assigned.component';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {TrainingService} from '../../ServiceProvider/TrainingService/training.service';
import {apiResponse} from '../../models/apiResponse';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {


  rating = new FormControl('', Validators.required);
  constructor(private trainingService: TrainingService, @Optional() @Inject(MAT_DIALOG_DATA) public data: number,
              public dialogRef: MatDialogRef<AssignedComponent>) { }

  ngOnInit(): void {
  }

  feedback()
  {
    this.trainingService.givefeedback(this.data, Number(this.rating.value)).subscribe((response: apiResponse) => {
      if (response.result === 'Success')
      {
        this.dialogRef.close();
      }
      else {
        alert('error occurred');
      }
    });
  }
}
