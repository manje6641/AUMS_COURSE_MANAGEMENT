import { Component, OnInit } from '@angular/core';
import {apiResponse} from '../../models/apiResponse';
import {TrainingMaterialService} from '../../ServiceProvider/TrainingMaterial/training-material.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDialogRef} from '@angular/material/dialog';
import {TrainingComponent} from '../training/training.component';

@Component({
  selector: 'app-add-material',
  templateUrl: './add-material.component.html',
  styleUrls: ['./add-material.component.css']
})
export class AddMaterialComponent implements OnInit {

  selectedFiles: File;
  id: any;
  materialForm = new FormGroup({
    trainingId: new FormControl('', Validators.required),
  });
  constructor(private materialService: TrainingMaterialService, public dialogRef: MatDialogRef<TrainingComponent>) { }

  ngOnInit(): void {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files[0];
  }

  addMaterial(){
    this.id = this.materialForm.value.trainingId;
    console.log(this.id);
    this.materialService.addTrainingMaterial(this.selectedFiles, this.id).subscribe((response: apiResponse) => {
      console.log(response);
      if (response.result === 'Success')
      {
        this.dialogRef.close();
      }
      else {
        alert('Error Occurred! Check whether training Id is present');
        this.dialogRef.close();
      }
    });
  }
}
