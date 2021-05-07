import {Component, Inject, OnInit, Optional, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {HttpEventType, HttpResponse} from '@angular/common/http';
import {TrainingMaterialService} from '../../ServiceProvider/TrainingMaterial/training-material.service';
import {apiResponse} from '../../models/apiResponse';
import {AddUserComponent} from '../add-user/add-user.component';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {AddMaterialComponent} from '../add-material/add-material.component';
import {TrainingComponent} from '../training/training.component';
import {UpdateTrainingComponent} from '../update-training/update-training.component';
import {UpdatematerialComponent} from '../updatematerial/updatematerial.component';
import {AssignedComponent} from '../assigned/assigned.component';

@Component({
  selector: 'app-training-material',
  templateUrl: './training-material.component.html',
  styleUrls: ['./training-material.component.css']
})
export class TrainingMaterialComponent implements OnInit {

  flag: boolean;
  role: any;
  material = [];
  displayedColumns: string[];
  selectedFiles: File;
  dataSource: MatTableDataSource<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  constructor(private materialService: TrainingMaterialService, @Optional() @Inject(MAT_DIALOG_DATA) public data: number,
              public dialogRef: MatDialogRef<TrainingComponent>,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.materialService.getAllTrainingMaterialById(this.data).subscribe((response: apiResponse ) => {
      this.material = response.data;
      this.dataSource = new MatTableDataSource(this.material);
      this.dataSource.paginator = this.paginator;
    });
    this.role = sessionStorage.getItem('Designation');
    if (this.role === 'Admin' || this.role === 'Instructor' || this.role === 'Creator')
    {
      this.displayedColumns = ['FileID', 'TrainingId', 'FileName', 'FileSize', 'download', 'update', 'delete'];
    }
    else if ( this.role === 'Student'){
      this.displayedColumns = ['FileID', 'TrainingId', 'FileName', 'FileSize'];
    }
  }

  applyFilter(event: Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
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

  deleteMaterial(id)
  {
    if (confirm('You Sure want to delete the Course?')){
      this.materialService.deleteMaterial(id).subscribe((response: apiResponse) => {
        if (response.result === 'Success')
        {
          this.ngOnInit();
        }
        else {
          alert('Error Occurred');
        }
      });
    }
  }

  updateMaterial(id)
  {
    const dialogRef = this.dialog.open(UpdatematerialComponent, {
      width: '50%',
      height: '50%',
      data: id
    });

    dialogRef.afterClosed().subscribe( result => {
      this.ngOnInit();
    });
  }

}
