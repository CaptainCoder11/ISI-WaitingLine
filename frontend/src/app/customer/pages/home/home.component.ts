import { Component, OnInit } from '@angular/core';
import { DialogRef, DialogService } from '@progress/kendo-angular-dialog';
import { Observable } from 'rxjs';
import { Store } from 'src/app/common/models/store.model';
import { StoreService } from 'src/app/common/services/store.service';
import { CustomerStoreDetailsComponent } from '../../components/store-details/store-details.component';

@Component({
  selector: 'app-customer-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class CustomerHomeComponent implements OnInit {

  stores$: Observable<Store[]>;
  constructor(private storeService: StoreService,
    private dialogService: DialogService) {
    this.stores$ = this.storeService.getAll();
  }

  ngOnInit(): void {
  }

  showStoreDetails(model) {
    const dialog: DialogRef = this.dialogService.open({
      content: CustomerStoreDetailsComponent,
      width: 400,
    });
    const inputData = dialog.content.instance;
    inputData.store = model;

    dialog.result.subscribe();
  }
}
