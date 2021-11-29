import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { filter, first, map, Observable, take } from "rxjs";
import { Store } from '../models/store.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class StoreService {
  private _url: string = environment.api_url;

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Array<Store>> {
    return this.httpClient.get<Array<Store>>(this._url);
  }

  getById(id: number): Observable<Store> {
    return this.httpClient.get<Store>(this._url).pipe(
      map((x: any) => x.stores[0]),
    );
  }
}
