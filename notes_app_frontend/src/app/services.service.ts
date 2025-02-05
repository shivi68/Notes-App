import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './models/user';
import { Notes } from './models/notes';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {

  url = "http://localhost:9595"

  isLoggedIn = false;
  
  constructor(private http: HttpClient) { }

    //loginUser
    public loginUserToken(token: any) {
      localStorage.setItem('token', token);
      return true;
    }

    //is login or not
    public isLogIn() {
      let tokenStr = localStorage.getItem('token');
      if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
        return false;
      } else {
        return true;
      }
    }

    
  //logout
  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.isLoggedIn = false;
    return true;
  }

    //get Token
    public getToken() {
      return localStorage.getItem('token');
    }

    
  public setUser(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }

    
  public getUser() {
    let userStr = localStorage.getItem('user');
    if (userStr != null) {
      return JSON.parse(userStr);
    }
    this.logout();
    return null;
  }

    //generate token
    public generateToken(loginData: any) {
      return this.http.post<any>(
        'http://localhost:9595/generate-token',
        loginData
      );
    }

  //current User
  public getCurrentUser() {
    return this.http.get('http://localhost:9595/current-user');
  }


   //register User
   public registerUser(user: User): Observable<any> {
    return this.http.post<any>('http://localhost:9595/user/signup', user);
  }


  //add Note

  public addNewNote(notes: any) {
    return this.http.post('http://localhost:9595/user/notes/add-new-notes', notes);
  }


  public getAllLatestNotes(){
    return this.http.get('http://localhost:9595/user/notes/viewAllNotes');
 }

  public getAllNotes(){
    return this.http.get('http://localhost:9595/user/notes/allNotes');
   }
  
   public deleteNote(id: any) {
    return this.http.delete('http://localhost:9595/user/notes/delete/' + id);
  }
  
  

 

}
