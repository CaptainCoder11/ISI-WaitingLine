import { Component, Input, OnInit } from '@angular/core';
import { DialogRef } from '@progress/kendo-angular-dialog';
import { Store } from 'src/app/common/models/store.model';

@Component({
  selector: 'app-customer-store-details',
  templateUrl: './store-details.component.html',
  styleUrls: ['./store-details.component.css']
})
export class CustomerStoreDetailsComponent implements OnInit {

  @Input() store: Store;
  constructor(private dialog: DialogRef) { }

  ngOnInit(): void {
  }

  onCancel(e: Event): void {
    this.closeForm();
  }

  /**
  * This function will invoke when user press "X" button on user form
  */
  closeForm(): void {
    this.dialog.close();
  }
}
