import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CourseService} from '../../ServiceProvider/CourseService/course.service';
import {Course} from '../../models/Course';
import {apiResponse} from '../../models/apiResponse';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialogRef} from '@angular/material/dialog';
import {ViewCoursesComponent} from '../view-courses/view-courses.component';


@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {
  result: string;

  months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  years = ['2021', '2020', '2019', '2018', '2017'];
  locations = ['Mumbai', 'Bengaluru', 'Hyderabad', 'Delhi', 'Chennai'];

  courseForm = new FormGroup({
    courseName: new FormControl('', Validators.required),
    courseDesc: new FormControl('', Validators.required),
    courseSkill: new FormControl('', Validators.required),
    coursePrerequisites: new FormControl('', Validators.required),
    courseLocation: new FormControl('', Validators.required),
    courseMonth: new FormControl('', Validators.required),
    courseYear: new FormControl('', Validators.required),
    creatorId: new FormControl('', Validators.required)
  });
  constructor(private courseService: CourseService, public dialogRef: MatDialogRef<ViewCoursesComponent>) { }

  ngOnInit(): void {
  }

  addCourse() {
    console.log(this.courseForm.value);
    this.courseService.addCourse(this.courseForm.value).subscribe((response: apiResponse) => {
      this.result = response.result;
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
