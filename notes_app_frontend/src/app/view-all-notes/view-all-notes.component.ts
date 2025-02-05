import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services.service';
import { Router } from '@angular/router';
import { DialogComponent } from '../dialog/dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-view-all-notes',
  templateUrl: './view-all-notes.component.html',
  styleUrls: ['./view-all-notes.component.css']
})
export class ViewAllNotesComponent implements OnInit {

  notes: any[] = [];
  maxNotesToShow = 10;

  constructor(private service: ServicesService, private router: Router, private dialog: MatDialog) { }
  ngOnInit(): void {
    this.loadRecentNotes();
  }

  loadRecentNotes(): void {
    this.service.getAllLatestNotes().subscribe(
      (data: any) => {
        this.notes = Array.isArray(data) ? data.slice(0, this.maxNotesToShow) : [];
      },
      (error: any) => {
        console.log(error);
      }
   );
  }

  deleteNote(id: any): void {
    if (confirm('Are you sure you want to delete this note?')) {
      this.service.deleteNote(id).subscribe({
        next: () => {
          // Remove the deleted note from the array
          this.notes = this.notes.filter((note) => note.id !== id);
          // Check if there are fewer notes than the maximum count
          if (this.notes.length < this.maxNotesToShow) {
            // Fetch the latest note from the database
            this.service.getAllLatestNotes().subscribe({
              next: (latestNote) => {
                // Add the latest note to the beginning of the array
                this.notes.unshift(latestNote);
              },
              error: (err) => {
                console.log(err);
              }
            });
          }
          
          // Show a success message in a dialog
          this.openDialog('Success', 'Note deleted successfully!');
          this.refreshCurrentRoute(); // Refresh the current route
        },
        error: (err) => {
          console.log('Error deleting note:', err);
        }
      });
    }
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

  refreshCurrentRoute() {
    const currentUrl = this.router.url; // Get the current URL
    this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
      // Navigate back to the current URL without triggering a full page reload
      this.router.navigate([currentUrl]);
    });
  }

  
  loggedIn(){
    return this.service.isLogIn();
  }

  logout(){
    this.service.logout();
    this.router.navigate(['user-login']);
  }

  currentUser(){
    return this.service.getUser();
  }

}

