import { Component, Input, OnInit } from '@angular/core';
import { Store } from 'src/app/common/models/store.model';

@Component({
  selector: 'app-store-info',
  templateUrl: './store-info.component.html',
  styleUrls: ['./store-info.component.css']
})
export class StoreInfoComponent implements OnInit {

  @Input() store: Store;
  constructor() {
  }

  ngOnInit(): void {
  }

}
