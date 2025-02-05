import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services.service';
import { User } from '../models/user';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  msg: any;
  user = new User();

  
  constructor(private service: ServicesService, private router: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
  
  }

  userLogin(){
    console.log(this.user);
    localStorage.clear();
    const loginData = {
      "userName": this.user.email,
      "password": this.user.password
    }
   
    this.service.generateToken(loginData).subscribe({
      next: (data: any) => {
        this.service.loginUserToken(data.token);
        this.service.getCurrentUser().subscribe({
          next: (user: any) => {
             this.service.setUser(user);

            //  const userId = user.id
            //  const redirectUrl = '/user/${user.id}/view-all-notes';

              this.openDialog("Success", "Logged in successfully!", '/view-all-notes');
               this.router.navigate(['/view-all-notes']);
          },
          error: (error) => {
            console.log("Error");
            this.openDialog("Error", "Please check your email and password.", "/user-login");
          }
        });
      },
      error: (error) => {
        console.log("Error");
        this.openDialog("Error", "Please check your email and password.","/user-login");
      }
    });
  }

  openDialog(title: string, message: string, redirectUrl: string): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '250px',
      data: { title, message }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if (result === 'success') {
        console.log('Logged in successfully'); 
      }
      this.router.navigate([redirectUrl]);
    });
  }

}
