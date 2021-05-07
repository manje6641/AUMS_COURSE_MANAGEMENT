import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CourseService} from '../../ServiceProvider/CourseService/course.service';
import {MatDialogRef} from '@angular/material/dialog';
import {ViewCoursesComponent} from '../view-courses/view-courses.component';
import {UserService} from '../../ServiceProvider/UserService/user.service';
import {ViewUsersComponent} from '../view-users/view-users.component';
import {apiResponse} from '../../models/apiResponse';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  locations = ['Mumbai', 'Bengaluru', 'Hyderabad', 'Delhi', 'Chennai'];
  Designation = ['Student', 'Creator', 'Admin', 'Instructor'];

  userForm = new FormGroup({
    userName: new FormControl('', Validators.required),
    userEmail: new FormControl('', Validators.required),
    userDesignation: new FormControl('', Validators.required),
    userLocation: new FormControl('', Validators.required),
  });
  constructor(private userService: UserService, public dialogRef: MatDialogRef<ViewUsersComponent>) { }

  ngOnInit(): void {
  }

  addUser() {
    console.log(this.userForm.value);
    this.userService.addUser(this.userForm.value).subscribe((response: apiResponse) => {
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
