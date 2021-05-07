import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './Components/login/login.component';
import {ViewCoursesComponent} from './Components/view-courses/view-courses.component';
import {HomeComponent} from './Components/home/home.component';
import {ViewUsersComponent} from './Components/view-users/view-users.component';
import {TrainingMaterialComponent} from './Components/training-material/training-material.component';
import {TrainingComponent} from './Components/training/training.component';
import {AuthGuardServiceGuard} from './ServiceProvider/GuardService/auth-guard-service.guard';
import {AssignedComponent} from './Components/assigned/assigned.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'viewCourse', component: ViewCoursesComponent, canActivate: [AuthGuardServiceGuard]},
  { path: 'viewUser', component: ViewUsersComponent, canActivate: [AuthGuardServiceGuard]},
  { path: 'viewTrainingMaterial', component: TrainingMaterialComponent, canActivate: [AuthGuardServiceGuard]},
  { path: 'viewTraining', component: TrainingComponent, canActivate: [AuthGuardServiceGuard]},
  { path: 'assigned', component: AssignedComponent, canActivate: [AuthGuardServiceGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
