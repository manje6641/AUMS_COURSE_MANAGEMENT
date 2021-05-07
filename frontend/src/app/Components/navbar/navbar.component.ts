import { Component, OnInit } from '@angular/core';
import {GoogleLoginProvider, SocialAuthService, SocialUser} from 'angularx-social-login';
import {Router} from '@angular/router';
import {UserService} from '../../ServiceProvider/UserService/user.service';
import {apiResponse} from '../../models/apiResponse';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user: SocialUser;
  role: any;
  constructor(private authService: SocialAuthService, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.authService.authState.subscribe((user) => {
      this.user = user;
    });
  }

  loginWithGoogle(): any{
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then( users => {
      this.user = users;
      this.userService.getUserByEmail(users.email).subscribe((response: apiResponse) => {
        sessionStorage.setItem('userId', response.data.userId);
        sessionStorage.setItem('Designation', response.data.userDesignation);
        sessionStorage.setItem('userEmail', response.data.userEmail);
        this.role = response.data.userDesignation;
        console.log(sessionStorage);
      });
      this.router.navigate(['/viewCourse']);
    });
  }

  logout(): any{
    sessionStorage.clear();
    this.authService.signOut();
    this.router.navigate(['/home']);

  }


}
