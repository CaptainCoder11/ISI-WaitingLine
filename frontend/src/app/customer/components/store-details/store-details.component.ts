import { Component, Input, OnInit } from '@angular/core';
import { DialogRef } from '@progress/kendo-angular-dialog';
import * as moment from 'moment';
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

  public get calc(){
    var openingHourStr=this.store.openingHour.split(':');
    var openingHourDate=new Date().setHours(parseInt(openingHourStr[0]),parseInt(openingHourStr[1]),parseInt(openingHourStr[2]));
    var openingTime=moment(openingHourDate).format("HH:mm:ss");

    var closingHourStr=this.store.closingHour.split(':');
    var closingHourDate=new Date().setHours(parseInt(closingHourStr[0]),parseInt(closingHourStr[1]),parseInt(closingHourStr[2]));
    var closingTime=moment(closingHourDate).format("HH:mm:ss");

    var todayDate=new Date();
    var todayTime=moment(todayDate).format("HH:mm:ss");

    if(todayTime>=openingTime && todayTime<closingTime){
      return "Store is open";
    }
    return "Store is closed";
  }
}
