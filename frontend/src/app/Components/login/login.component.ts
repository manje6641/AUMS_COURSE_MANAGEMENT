import { Component, OnInit } from '@angular/core';
import {SocialAuthService, GoogleLoginProvider, SocialUser} from 'angularx-social-login';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: SocialUser;
  constructor(private authService: SocialAuthService, private router: Router) { }

  ngOnInit(): void {
    this.authService.authState.subscribe((user) => {
      this.user = user;
    });
  }

  loginWithGoogle(): any{
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then( users => {
      this.user = users;
      sessionStorage.setItem('UserEmail', users.email);
      this.router.navigate(['/home']);
    });
  }

  logout(): any{
    sessionStorage.clear();
    this.authService.signOut();
    this.router.navigate(['/home']);
  }
}
