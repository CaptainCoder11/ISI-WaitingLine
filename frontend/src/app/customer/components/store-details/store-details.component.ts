import { Component, Input, OnInit } from '@angular/core';
import { DialogRef } from '@progress/kendo-angular-dialog';
import { Store } from 'src/app/common/models/store.model';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { DISPLAY_LOGO, DISPLAY_STORE_STATUS } from 'src/app/common/utils/functions';

@Component({
  selector: 'app-customer-store-details',
  templateUrl: './store-details.component.html',
  styleUrls: ['./store-details.component.css']
})
export class CustomerStoreDetailsComponent implements OnInit {
  DISPLAY_LOGO = DISPLAY_LOGO;
  DISPLAY_STORE_STATUS = DISPLAY_STORE_STATUS;

  @Input() store: Store;
  constructor(private dialog: DialogRef,
    public authService: AuthenticationService
  ) { }

  ngOnInit(): void {
  }

  onCancel(e: Event): void {
    this.closeForm();
  }

  closeForm(): void {
    this.dialog.close();
  }
}
