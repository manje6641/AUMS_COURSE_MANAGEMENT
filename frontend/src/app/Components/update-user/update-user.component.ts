import {Component, Inject, OnInit, Optional} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ViewCoursesComponent} from '../view-courses/view-courses.component';
import {CourseService} from '../../ServiceProvider/CourseService/course.service';
import {ViewUsersComponent} from '../view-users/view-users.component';
import {UserService} from '../../ServiceProvider/UserService/user.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {apiResponse} from '../../models/apiResponse';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  locations = ['Mumbai', 'Bengaluru', 'Hyderabad', 'Delhi', 'Chennai'];
  designation = ['Student', 'Creator', 'Admin', 'Instructor'];

  userId: any;
  user: any;

  userForm = new FormGroup({
    userName: new FormControl('', Validators.required),
    userEmail: new FormControl('', Validators.required),
    userDesignation: new FormControl('', Validators.required),
    userLocation: new FormControl('', Validators.required),
    userId: new FormControl('', Validators.required)
  });

  constructor(public dialogRef: MatDialogRef<ViewUsersComponent>,
              @Optional() @Inject(MAT_DIALOG_DATA) public data: number, private userService: UserService) {
  }

  ngOnInit(): void {
    this.userId = this.data;
    this.userService.getUserById(this.userId).subscribe((response: apiResponse) => {
      console.log(response);
      this.user = response.data;
      this.userForm.setValue({
        userName: response.data.userName,
        userEmail: response.data.userEmail,
        userDesignation: response.data.userDesignation,
        userLocation: response.data.userLocation,
        userId: this.userId,
      });
    });
  }

  updateUser() {
    console.log(this.userForm.value);
    this.userService.updateUser(this.userForm.value).subscribe((response: apiResponse) => {
      console.log(response);
      if (response.result === 'Success') {
        this.dialogRef.close();
      } else {
        alert('Error Occurred');
      }
    });
  }
}

