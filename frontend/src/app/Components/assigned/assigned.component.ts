import {Component, OnInit, ViewChild} from '@angular/core';
import {TrainingService} from '../../ServiceProvider/TrainingService/training.service';
import {apiResponse} from '../../models/apiResponse';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {TrainingMaterialService} from '../../ServiceProvider/TrainingMaterial/training-material.service';
import {TrainingMaterialComponent} from '../training-material/training-material.component';
import {MatDialog} from '@angular/material/dialog';
import {Course} from '../../models/Course';
import {AssignedMaterialComponent} from '../assigned-material/assigned-material.component';
import {FeedbackComponent} from '../feedback/feedback.component';

@Component({
  selector: 'app-assigned',
  templateUrl: './assigned.component.html',
  styleUrls: ['./assigned.component.css']
})
export class AssignedComponent implements OnInit {

  trainingList = [];
  id: any;
  list = [];
  displayedColumns: string[];
  dataSource: MatTableDataSource<any>;

  constructor(private materialService: TrainingMaterialService, private trainingService: TrainingService, public dialog: MatDialog) { }

  ngOnInit(): void {
    if (sessionStorage.getItem('Designation') === 'Student')
    {
      this.id = sessionStorage.getItem('userId');
      this.trainingService.getAssignedTraining(this.id).subscribe((response: apiResponse) => {
        if (response.result === 'Success')
        {
          for (let i = 0; i < response.data.length; i++)
          {
            this.trainingList.push(response.data[i].trainingId);
          }
          for (let i = 0; i < this.trainingList.length; i++)
          {
            this.trainingService.getTrainingById(this.trainingList[i]).subscribe((newresponse: apiResponse) => {
              this.list.push(newresponse.data);
            });
          }
        }
        else {
          alert('error occurred try again later');
        }
      });
      console.log(this.list);
      this.displayedColumns = ['trainingId', 'courseId', 'instructorId', 'feedback'];
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

  viewMaterial(id){
    const dialogRef = this.dialog.open(AssignedMaterialComponent, {
      width: '90%',
      height: '55%',
      data: id
    });

    dialogRef.afterClosed().subscribe( result => {
      // this.ngOnInit();
    });
  }

  giveFeedback(id){
    const dialogRef = this.dialog.open(FeedbackComponent, {
      width: '90%',
      height: '55%',
      data: id
    });

    dialogRef.afterClosed().subscribe( result => {
      alert('feedback submitted');
    });
  }

}
