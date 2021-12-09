import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from "rxjs";
import { Store } from '../models/store.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class StoreService {
  private _url: string = `${environment.api_url}/store`;

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Store[]> {
    return this.httpClient.get<Store[]>(this._url);
  }

  getById(id: number): Observable<Store> {
    return this.httpClient.get<Store>(this._url).pipe(
      map((x: any) => x[0]),
    );
  }

  joinWaitingLine(userId: number, storeId: number): Observable<any> {
    const url = `${environment.api_url}/customer/join-waiting-line/${userId}/${storeId}`;
    return this.httpClient.get<any>(url);
  }

  removeWaitingLine(userId: number, storeId: number): Observable<any> {
    const url = `${environment.api_url}/customer/remove-waiting-line/${userId}/${storeId}`;
    return this.httpClient.get<any>(url);
  }
}
