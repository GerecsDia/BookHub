import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  host ="http://localhost:8080/";
  constructor(private http: HttpClient) {

   }

   login(user: string, pass: string, email:string) {
    const authData = {
      name: user,
      password: pass,
      email: email
    }
    let httpHeaders = new HttpHeaders();
    httpHeaders.set('Content-Type', 'application/json');
    const httpOptions = {
      headers: httpHeaders
    }
    let endpoint = 'login';
    let url = this.host + endpoint;
    return this.http.post<any>(url, authData, httpOptions);
  }

}
