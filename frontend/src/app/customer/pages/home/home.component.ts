import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Store } from 'src/app/common/models/store.model';
import { StoreService } from 'src/app/common/services/store.service';

@Component({
  selector: 'app-customer-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class CustomerHomeComponent implements OnInit {

  stores$: Observable<Store[]>;
  constructor(private storeService: StoreService) {
    this.stores$ = this.storeService.getAll();
  }

  ngOnInit(): void {
  }
}
