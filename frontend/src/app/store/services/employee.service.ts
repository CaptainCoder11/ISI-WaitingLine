import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, of } from 'rxjs';
import { StoreUser } from 'src/app/store/models/store-user.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private _url: string = `${environment.api_url}/store/employees`;

  constructor(private httpClient: HttpClient) {}

  getAll(): Observable<Array<StoreUser>> {
    return this.httpClient.get<Array<StoreUser>>(this._url);
  }

  getById(id: number): Observable<StoreUser> {
    return this.httpClient
      .get<StoreUser>(this._url)
      .pipe(map((x: any) => x[0]));
  }
}
