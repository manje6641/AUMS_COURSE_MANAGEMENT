import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AddCourseComponent } from './Components/add-course/add-course.component';
import { ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { AddUserComponent } from './Components/add-user/add-user.component';
import { ViewCoursesComponent } from './Components/view-courses/view-courses.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSortModule} from '@angular/material/sort';
import {MatPaginatorModule} from '@angular/material/paginator';
import { ViewUsersComponent } from './Components/view-users/view-users.component';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {FlexLayoutModule} from '@angular/flex-layout';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import { UpdateCourseComponent } from './Components/update-course/update-course.component';
import { UpdateUserComponent } from './Components/update-user/update-user.component';
import {RouterModule} from '@angular/router';
import {LoginComponent} from './Components/login/login.component';
import {SocialLoginModule, SocialAuthServiceConfig, GoogleLoginProvider, SocialAuthService} from 'angularx-social-login';
import {MatToolbarModule} from '@angular/material/toolbar';
import {AppRoutingModule} from './app-routing.module';
import { NavbarComponent } from './Components/navbar/navbar.component';
import { HomeComponent } from './Components/home/home.component';
import { TrainingMaterialComponent } from './Components/training-material/training-material.component';
import { TrainingComponent } from './Components/training/training.component';
import { AddTrainingComponent } from './Components/add-training/add-training.component';
import { UpdateTrainingComponent } from './Components/update-training/update-training.component';
import {AuthGuardServiceGuard} from './ServiceProvider/GuardService/auth-guard-service.guard';
import {ChartsModule} from 'ng2-charts';
import { AssignedComponent } from './Components/assigned/assigned.component';
import { AddMaterialComponent } from './Components/add-material/add-material.component';
import { UpdatematerialComponent } from './Components/updatematerial/updatematerial.component';
import { AssignedMaterialComponent } from './Components/assigned-material/assigned-material.component';
import { FeedbackComponent } from './Components/feedback/feedback.component';


@NgModule({
  declarations: [
    AppComponent,
    AddCourseComponent,
    AddUserComponent,
    ViewCoursesComponent,
    ViewUsersComponent,
    UpdateCourseComponent,
    UpdateUserComponent,
    LoginComponent,
    NavbarComponent,
    HomeComponent,
    TrainingMaterialComponent,
    TrainingComponent,
    AddTrainingComponent,
    UpdateTrainingComponent,
    AssignedComponent,
    AddMaterialComponent,
    UpdatematerialComponent,
    AssignedMaterialComponent,
    FeedbackComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSortModule,
    MatPaginatorModule,
    MatIconModule,
    MatButtonModule,
    FlexLayoutModule,
    MatSelectModule,
    RouterModule,
    SocialLoginModule,
    MatToolbarModule,
    AppRoutingModule,
    ChartsModule,
  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider('509741401645-lr2durajb7o7r7kj7ggv8iukpmmcr04t.apps.googleusercontent.com')
          },
        ]
      } as SocialAuthServiceConfig,
    },
  AuthGuardServiceGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
