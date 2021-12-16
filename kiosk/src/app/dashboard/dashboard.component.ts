import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { AuthenticationService } from '../services/authentication.service';
import { WaitingListFirebaseService } from '../services/waiting-list.firebase.service';

@Component({
  selector: 'app-store-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class StoreDashboardComponent implements OnInit {
  waitingLine: any;
  constructor(
    private waitingListFirebaseService: WaitingListFirebaseService,
    private authService: AuthenticationService,
    private spinner: NgxSpinnerService
  ) {
    this.getStoreWaitingList();
  }

  ngOnInit(): void {}

  getStoreWaitingList() {
    this.waitingListFirebaseService
      .getStoreWaitingList(this.authService.getStoreUser.storeId)
      .subscribe((response) => {
        this.waitingLine = response;
      });
  }
}
