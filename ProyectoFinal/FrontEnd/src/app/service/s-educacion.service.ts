import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Educacion } from '../model/educacion';

@Injectable({
  providedIn: 'root'
})
export class SEducacionService {
  edURL = 'http://localhost:8080/educa/'
  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Educacion[]>{
    return this.httpClient.get<Educacion[]>(this.edURL + 'lista');

  }

  public detail(id: number): Observable<Educacion>{
   return this.httpClient.get<Educacion>(this.edURL + `detail/${id}`);
  }

  public save(educacion: Educacion): Observable<any>{
    return this.httpClient.post<any>(this.edURL + `create`, educacion);
  }

  public update(id: number, educacion: Educacion): Observable<any>{
    return this.httpClient.put<any>(this.edURL + `update/${id}`, educacion);
  }

  public delete(id: number): Observable<any>{
    return this.httpClient.delete<any>(this.edURL + `delete/${id}`);
  }
}
