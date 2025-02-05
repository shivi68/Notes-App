import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserSignupComponent } from './user-signup/user-signup.component';
import { HomeComponent } from './home/home.component';

import { ViewAllNotesComponent } from './view-all-notes/view-all-notes.component';
import { AddNewNotesComponent } from './add-new-notes/add-new-notes.component';


const routes: Routes = [
  {
    path: 'user-login',
    component: UserLoginComponent
  },

  {
    path: 'user-signup',
    component: UserSignupComponent
  },

  {
    path: 'add-new-notes',
    component: AddNewNotesComponent
  },

  {
    path: 'view-all-notes',
    component: ViewAllNotesComponent
  },

  {
    path: '**',
    component: HomeComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
