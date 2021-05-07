import {Component, Inject, OnInit, Optional, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {TrainingService} from '../../ServiceProvider/TrainingService/training.service';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material/dialog';
import {apiResponse} from '../../models/apiResponse';
import {AddTrainingComponent} from '../add-training/add-training.component';
import {UpdateCourseComponent} from '../update-course/update-course.component';
import {UpdateTrainingComponent} from '../update-training/update-training.component';
import {CourseService} from '../../ServiceProvider/CourseService/course.service';
import {EmailService} from '../../ServiceProvider/EmailService/email.service';
import {Email} from '../../models/Email';
import {TrainingMaterialService} from '../../ServiceProvider/TrainingMaterial/training-material.service';
import {AddMaterialComponent} from '../add-material/add-material.component';
import {TrainingMaterialComponent} from '../training-material/training-material.component';
import {Session} from '../../models/Session';

@Component({
  selector: 'app-training',
  templateUrl: './training.component.html',
  styleUrls: ['./training.component.css']
})
export class TrainingComponent implements OnInit {

  email = new Email();
  role: any;
  training = [];
  flag: boolean;
  session = new Session();
  displayedColumns: string[] = ['TrainingId', 'CourseId', 'InstructorId', 'TrainingFeedback', 'update', 'delete'];
  dataSource: MatTableDataSource<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(private trainingService: TrainingService, public dialog: MatDialog, private emailService: EmailService) { }

  ngOnInit(): void {
    this.role = sessionStorage.getItem('Designation');
    this.trainingService.getAllTraining().subscribe((response: apiResponse) => {
      this.training = response.data;
      this.dataSource = new MatTableDataSource(this.training);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
    if (this.role === 'Admin')
    {
      this.displayedColumns = ['TrainingId', 'CourseId', 'InstructorId', 'TrainingFeedback', 'ViewMaterial', 'update', 'delete'];
    }
    else if (this.role === 'Student') {
      this.displayedColumns = ['TrainingId', 'CourseId', 'InstructorId', 'TrainingFeedback', 'ViewMaterial', 'Enroll'];
    }
    else if (this.role === 'Instructor' || this.role === 'Creator')
    {
      this.displayedColumns = ['TrainingId', 'CourseId', 'InstructorId', 'TrainingFeedback', 'ViewMaterial'];
    }
    else{
      this.displayedColumns = ['TrainingId', 'CourseId', 'InstructorId', 'TrainingFeedback'];
    }

    if (this.role === 'Instructor' || this.role === 'Creator' || this.role === 'Admin')
    {
      this.flag = true;
    }
    else
    {
      this.flag = false;
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  addTraining()
  {
    const dialogRef = this.dialog.open(AddTrainingComponent, {
      width: '50%',
      height: '50%'
    });

    dialogRef.afterClosed().subscribe( result => {
      this.ngOnInit();
    });
  }

  deleteTraining(id: number){
    console.log(id);
    if (confirm('You Sure want to delete the Course?')){
      this.trainingService.deleteTraining(id).subscribe((response: apiResponse) => {
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

  updateTraining(id: number)
  {
    const dialogRef = this.dialog.open(UpdateTrainingComponent, {
      width: '50%',
      height: '50%',
      data: id
    });

    dialogRef.afterClosed().subscribe( result => {
      this.ngOnInit();
    });
  }

  enrollTraining(id: number){
    this.session.trainingId = id;
    this.session.userId = Number(sessionStorage.getItem('userId'));
    this.trainingService.assignTraining(this.session).subscribe((response: apiResponse) => {
      if (response.result === 'Success')
      {
       this.email.trainingId = id;
       this.email.recipient = sessionStorage.getItem('userEmail');
       this.email.subject = 'Thanks for enrolling into our training';
       this.emailService.sendEmail(this.email).subscribe((newresponse: apiResponse) => {
          if (newresponse.result === 'Success')
          {
            alert('Check Your Email and Assigned section for more details');
          }
          else {
            alert('error occurred try again later');
          }
        });
      }
      else {
        console.log(response);
      }
    });
  }

  viewMaterial(id){
    const dialogRef = this.dialog.open(TrainingMaterialComponent, {
      width: '90%',
      height: '55%',
      data: id
    });

    dialogRef.afterClosed().subscribe( result => {
      this.ngOnInit();
    });
  }

  addMaterial()
  {
    const dialogRef = this.dialog.open(AddMaterialComponent, {
      width: '50%',
      height: '50%'
    });

    dialogRef.afterClosed().subscribe( result => {
      this.ngOnInit();
    });
  }
}
