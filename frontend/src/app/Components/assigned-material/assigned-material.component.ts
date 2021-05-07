import {Component, Inject, OnInit, Optional} from '@angular/core';
import {TrainingMaterialService} from '../../ServiceProvider/TrainingMaterial/training-material.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {TrainingComponent} from '../training/training.component';
import {AssignedComponent} from '../assigned/assigned.component';
import {apiResponse} from '../../models/apiResponse';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-assigned-material',
  templateUrl: './assigned-material.component.html',
  styleUrls: ['./assigned-material.component.css']
})
export class AssignedMaterialComponent implements OnInit {

  flag: boolean;
  role: any;
  material = [];
  displayedColumns: string[] = ['FileID', 'TrainingId', 'FileName', 'FileSize', 'download'];
  selectedFiles: File;
  dataSource: MatTableDataSource<any>;
  constructor(private materialService: TrainingMaterialService, @Optional() @Inject(MAT_DIALOG_DATA) public data: number,
              public dialogRef: MatDialogRef<AssignedComponent>,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.materialService.getAllTrainingMaterialById(this.data).subscribe((response: apiResponse ) => {
      this.material = response.data;
      this.dataSource = new MatTableDataSource(this.material);
    });
  }
  base64ToArrayBuffer(base64) {
    const binary_string = window.atob(base64);
    const len = binary_string.length;
    const bytes = new Uint8Array(len);
    for (let i = 0; i < len; i++) {
      bytes[i] = binary_string.charCodeAt(i);
    }
    return bytes.buffer;
  }

  downloadMaterial(data, type){
    console.log(typeof data);
    const byteArray = this.base64ToArrayBuffer(data);
    const blob = new Blob([byteArray], { type });
    const url = window.URL.createObjectURL(blob);
    window.open(url);
  }

}
