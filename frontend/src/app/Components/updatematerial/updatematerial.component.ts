import {Component, Inject, OnInit, Optional} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {TrainingComponent} from '../training/training.component';
import {TrainingService} from '../../ServiceProvider/TrainingService/training.service';
import {TrainingMaterialService} from '../../ServiceProvider/TrainingMaterial/training-material.service';
import {apiResponse} from '../../models/apiResponse';

@Component({
  selector: 'app-updatematerial',
  templateUrl: './updatematerial.component.html',
  styleUrls: ['./updatematerial.component.css']
})
export class UpdatematerialComponent implements OnInit {

  selectedFiles: File;
  constructor(public dialogRef: MatDialogRef<TrainingComponent>,
              @Optional() @Inject(MAT_DIALOG_DATA) public data: number, private materialService: TrainingMaterialService) { }

  ngOnInit(): void {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files[0];
  }

  updateMaterial(){
    this.materialService.updateMaterial(this.selectedFiles, this.data).subscribe((response: apiResponse) => {
      if (response.result === 'Success')
      {
        this.dialogRef.close();
      }
      else{
        alert('Error Occurred');
      }
    });
  }
}
