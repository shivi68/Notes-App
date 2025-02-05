import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { User } from '../models/user';
import { DialogComponent } from '../dialog/dialog.component';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-add-new-notes',
  templateUrl: './add-new-notes.component.html',
  styleUrls: ['./add-new-notes.component.css']
})
export class AddNewNotesComponent implements OnInit {
  errorMessage: string = '';
  notes = { title: '', description: '' };


  constructor(private service: ServicesService, private router: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  createNote(addPostForm: NgForm) {
   
   // Additional check for Description length
   if (this.notes.description.length > 500) {
         this.errorMessage = 'Description cannot exceed 500 letters.';
    return;
  }

  if (!/^[a-zA-Z@,;&*+\- ]*$/.test(this.notes.description)) {
    this.errorMessage = 'Only alphabetical letters (A-Z, a-z), spaces, and [@, ; & * + -] special characters are allowed in Description.';
    return;
  }

    let newPost = {
      title: addPostForm.value.title,
      description: addPostForm.value.description,
      id: this.service.getUser().id
    };
  
    this.service.addNewNote(newPost).subscribe(() => {
      console.log('Notes created:', this.notes);
      addPostForm.resetForm();
         
      // Handle success or show a message to the user
      this.openDialog('Success', 'Note added Successfully!!');
      this.router.navigate(['view-all-notes']);
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

  logout(){
    this.service.logout();
    this.router.navigate(['user-login']);
  }

  loggedIn(){
    return this.service.isLogIn();
  }

  
  currentUser(){
    return this.service.getUser();
  }
  // Function to limit Description length to 500 characters
  limitDescriptionLength(event: Event) {
    const target = event.target as HTMLTextAreaElement;
    if (target.value.length > 500) {
      target.value = target.value.slice(0, 500);
    }
}
}