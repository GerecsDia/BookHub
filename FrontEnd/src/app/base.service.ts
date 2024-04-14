import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BaseService {
private host="http://localhost:8080/";
private bookSubject = new Subject();
private userSubject = new Subject();
  constructor(private http : HttpClient) {
    this.loadBooks();
    this.getBooks();
    this.loadUsers();
    this.getUsers();
   }

   loadBooks(){
    let endpoint = "guest/books";
    let url=this.host+endpoint;
    return this.http.get(url).subscribe({
      next:(data)=>this.bookSubject.next(data),
      error:(err)=>console.log("Hiba a könyvek lekérésekor: ", err)
    });
   }

   getBooks(){
    return this.bookSubject;
   }
   
   getThisBook(id:number){
    this.loadThisBook(id);
    return this.bookSubject;
   }

   loadThisBook(id:number){
    let endpoint = "guest/books";
    let url=this.host+endpoint;
    return this.http.get(url+"/"+id).subscribe({
      next:(data)=>this.bookSubject.next(data),
      error:(err)=>console.log("Hiba a kijelölt könyv lekérésekor: ", err)
    });
   }

   postBook(body:any){
    let endpoint = "guest/books";
    let url=this.host+endpoint;
    const result = this.http.post(url,body);
    return result;
   }

   updateBook(id:number, body:any){
    let endpoint = "guest/books/";
    let url=this.host+endpoint+id;
    const result = this.http.put(url,body);
    return result;
   }

   deleteBook(id:number){
    let endpoint = "guest/books";
    let url=this.host+endpoint;
    const result = this.http.delete(url+"/"+id);
    return result;
   }

   loadUsers(){
    let endpoint = "users";
    let url=this.host+endpoint;
    return this.http.get(url).subscribe({
      next:(data)=>this.userSubject.next(data),
      error:(err)=>console.log("Hiba a felhasználók lekérésekor: ", err)
    });
   }

   loadThisUser(id:number){
    let endpoint = "users";
    let url=this.host+endpoint;
    return this.http.get(url+"/"+id).subscribe({
      next:(data)=>this.bookSubject.next(data),
      error:(err)=>console.log("Hiba a kijelölt felhasználó lekérésekor: ", err)
    });
   }

   getUsers(){
    return this.userSubject;
   }

   getThisUser(){
    return this.userSubject;
   }

   postUser(body:any){
    let endpoint = "users";
    let url=this.host+endpoint;
    const result = this.http.post(url,body);
    return result;
   }

   updateUser(id:number, body:any){
    let endpoint = "users/";
    let url=this.host+endpoint+id;
    const result = this.http.put(url,body);
    return result;
   }

   deleteUser(id:number){
    let endpoint = "users";
    let url=this.host+endpoint;
    const result = this.http.delete(url+"/"+id);
    return result;
   }
}
