import { Injectable } from '@angular/core';
import { Firestore, collectionData, collection } from '@angular/fire/firestore';
import { query, where } from '@firebase/firestore';
import { Observable } from 'rxjs';

@Injectable()
export class WaitingListFirebaseService {
  private WaitingList: string = 'waiting_list';
  constructor(private firestore: Firestore) { }

  getCustomerWaitingList(userId): Observable<any> {
    const waiting_list = collection(this.firestore, this.WaitingList);
    const data = query(waiting_list, where("id", "==", userId));
    return collectionData(data);
  }

  getStoreWaitingList(storeId): Observable<any> {
    const waiting_list = collection(this.firestore, this.WaitingList);
    const data = query(waiting_list, where("storeId", "==", storeId));
    return collectionData(data);
  }
}
