import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Store } from 'src/app/common/models/store.model';
import { StoreService } from 'src/app/common/services/store.service';

@Component({
  selector: 'app-store-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class StoreDashboardComponent implements OnInit {

  store$: Observable<Store>;
  constructor(private storeService: StoreService) {
    this.store$ = this.storeService.getById(1);
  }

  ngOnInit(): void {
  }
}
