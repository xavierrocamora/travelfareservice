import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Driver } from '../domain/driver';

@Injectable({
  providedIn: 'root'
})
export class DriverService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private httpClient: HttpClient) {}

  getDriversList(): Observable<Driver[]>{
    return this.httpClient.get<Driver[]>(`${this.apiServerUrl}/drivers`);
  }

  addDriver(driver: Driver): Observable<Object>{
    return this.httpClient.post(`${this.apiServerUrl}/drivers`, driver);
  }

  getDriverById(id: number): Observable<Driver>{
    return this.httpClient.get<Driver>(`${this.apiServerUrl}/drivers/${id}`);
  }

  updateDriver(id: number, driver: Driver): Observable<Object>{
    return this.httpClient.put(`${this.apiServerUrl}/drivers/${id}`, driver);
  }

  deleteDriver(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.apiServerUrl}/drivers/${id}`);
  }
}
