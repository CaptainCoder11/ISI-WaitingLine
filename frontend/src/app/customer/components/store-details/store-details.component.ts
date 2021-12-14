import { Component, Input, OnInit } from '@angular/core';
import { DialogRef } from '@progress/kendo-angular-dialog';
import { NgxSpinnerService } from 'ngx-spinner';
import { Store } from 'src/app/common/models/store.model';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { StoreService } from 'src/app/common/services/store.service';
import {
  DISPLAY_LOGO,
  DISPLAY_STORE_STATUS,
} from 'src/app/common/utils/functions';
import { NotyUtil } from 'src/app/common/utils/noty-util';

@Component({
  selector: 'app-customer-store-details',
  templateUrl: './store-details.component.html',
  styleUrls: ['./store-details.component.css'],
})
export class CustomerStoreDetailsComponent implements OnInit {
  DISPLAY_LOGO = DISPLAY_LOGO;
  DISPLAY_STORE_STATUS = DISPLAY_STORE_STATUS;

  @Input() customerWaiting: any;

  @Input() store: Store;
  constructor(
    private dialog: DialogRef,
    public authService: AuthenticationService,
    private spinner: NgxSpinnerService,
    private storeService: StoreService
  ) {}

  ngOnInit(): void {}

  onCancel(e: Event): void {
    this.closeForm();
  }

  closeForm(): void {
    this.dialog.close();
  }

  joinWaitingLine(storeId) {
    const userId = this.authService.getCustomer.id;
    this.spinner.show();
    this.storeService.joinWaitingLine(userId, storeId).subscribe(
      () => {
        this.spinner.hide();
        NotyUtil.success('You have joined waiting line!');
        this.closeForm();
      },
      (e) => {
        this.spinner.hide();
        const error = e.error.message || e.error.error;
        NotyUtil.error(error);
      },
      () => {
        this.spinner.hide();
      }
    );
  }
}
