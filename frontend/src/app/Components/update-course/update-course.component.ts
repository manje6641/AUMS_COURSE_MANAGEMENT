import {Component, Inject, OnInit, Optional} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {CourseService} from '../../ServiceProvider/CourseService/course.service';
import {apiResponse} from '../../models/apiResponse';
import {ViewCoursesComponent} from '../view-courses/view-courses.component';

@Component({
  selector: 'app-update-course',
  templateUrl: './update-course.component.html',
  styleUrls: ['./update-course.component.css']
})
export class UpdateCourseComponent implements OnInit {

  months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  years = ['2021', '2020', '2019', '2018', '2017' ];
  locations = ['Mumbai', 'Bengaluru', 'Hyderabad', 'Delhi', 'Chennai'];

  courseId: any;
  course: any;

  courseForm = new FormGroup({
    courseName: new FormControl('', Validators.required),
    courseDesc: new FormControl('', Validators.required),
    courseSkill: new FormControl('', Validators.required),
    coursePrerequisites: new FormControl('', Validators.required),
    courseLocation: new FormControl('', Validators.required),
    courseMonth: new FormControl('', Validators.required),
    courseYear: new FormControl('', Validators.required),
    courseId: new FormControl('', Validators.required)
  });

  constructor(public dialogRef: MatDialogRef<ViewCoursesComponent>,
              @Optional() @Inject(MAT_DIALOG_DATA) public data: number, private courseService: CourseService) { }

  ngOnInit(): void {
    this.courseId = this.data;
    this.courseService.getCourseById(this.courseId).subscribe((response: apiResponse) => {
      console.log(response);
      this.course = response.data;
      this.courseForm.setValue({
        courseName: response.data.courseName,
        courseDesc: response.data.courseDesc,
        courseSkill: response.data.courseSkill,
        coursePrerequisites: response.data.coursePrerequisites,
        courseLocation: response.data.courseLocation,
        courseMonth: response.data.courseMonth,
        courseYear: response.data.courseYear,
        courseId: this.courseId,
      });
    });
  }

  updateCourse(){
    console.log(this.courseForm.value);
    this.courseService.updateCourse(this.courseForm.value).subscribe((response: apiResponse) => {
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
