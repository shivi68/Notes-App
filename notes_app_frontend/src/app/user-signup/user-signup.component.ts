import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ServicesService } from '../services.service';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.css']
})
export class UserSignupComponent implements OnInit {

  msg: any;
  user = new User();
  
  constructor(private service: ServicesService, private router: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
  
  }
  registerUser(){
    this.service.registerUser(this.user).subscribe({
      next: (data) => {
        console.log("Success!");
        //this.service.setUser(data);
        this.service.isLoggedIn = true;
        this.openDialog("Successfully Registered", "Please check your email for further instructions.");
        this.router.navigate(['user-login']);
      },
      error: (err) => {
        console.log("Error!");
        this.msg = "Email Already Exists!!";
        this.openDialog("Registration Error", this.msg);
      }
    });
  }

  openDialog(title: string, message: string): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '250px',
      data: { title, message }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}

