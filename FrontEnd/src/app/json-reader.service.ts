import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class JsonReaderService {
  constructor(private http: HttpClient) {}

  // Függvény a JSON beolvasására és Observable visszaadására
  readJsonFile(filePath: string): Observable<any> {
    return this.http.get(filePath);
  }
}
