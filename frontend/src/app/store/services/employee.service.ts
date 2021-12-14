import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, of } from 'rxjs';
import { Employee } from 'src/app/store/models/employee.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private _url: string = `${environment.api_url}/store/employees`;

  constructor(private httpClient: HttpClient) {}

  getAll(): Observable<Array<Employee>> {
    return this.httpClient.get<Array<Employee>>(this._url);
  }

  getById(id: number): Observable<Employee> {
    return this.httpClient.get<Employee>(this._url).pipe(map((x: any) => x[0]));
  }
}
