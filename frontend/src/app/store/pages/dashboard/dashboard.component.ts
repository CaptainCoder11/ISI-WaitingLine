import { Component, OnInit } from '@angular/core';
import * as Highcharts from "highcharts";
import { WaitingListFirebaseService } from 'src/app/common/services/waiting-list.firebase.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { CustomerWaiting } from 'src/app/common/models/customer-waiting.model';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { StoreService } from 'src/app/common/services/store.service';
import { NotyUtil } from 'src/app/common/utils/noty-util';

@Component({
  selector: 'app-store-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class StoreDashboardComponent implements OnInit {

  Highcharts: typeof Highcharts = Highcharts;

  totalVisitsChartOptions: Highcharts.Options = {
    title: {
      text: ''
    },
    credits: {
      enabled: false,
    },
    yAxis: {
      title: {
        text: 'No. Of Visits'
      }
    },
    xAxis: {
      title: {
        text: "Date/Time"
      }
    },
    series: [
      {
        type: "line",
        data: [10, 50, 30, 40, 70, 110, 24, 52, 35, 95]
      }
    ],
  };

  chartOptions: Highcharts.Options = {
    chart: {
      type: 'column'
    },
    title: {
      text: ''
    },
    xAxis: {
      categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas']
    },
    yAxis: {
      min: 0,
      title: {
        text: 'Total fruit consumption'
      },
      stackLabels: {
        enabled: true,
        style: {
          fontWeight: 'bold',
          color: ( // theme
            Highcharts.defaultOptions.title.style && Highcharts.defaultOptions.title.style.color) || 'gray'
        }
      }
    },
    legend: {
      align: 'right',
      x: -30,
      verticalAlign: 'top',
      y: 25,
      floating: true,
      backgroundColor:
        Highcharts.defaultOptions.legend.backgroundColor || 'white',
      borderColor: '#CCC',
      borderWidth: 1,
      shadow: false
    },
    tooltip: {
      headerFormat: '<b>{point.x}</b><br/>',
      pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
    },
    plotOptions: {
      column: {
        stacking: 'normal',
        dataLabels: {
          enabled: true
        }
      }
    },
    series: [{
      name: 'John',
      type: 'column',
      data: [5, 3, 4, 7, 2]
    }, {
      name: 'Jane',
      type: 'column',
      data: [2, 2, 3, 2, 1]
    }, {
      name: 'Joe',
      type: 'column',
      data: [3, 4, 4, 2, 5]
    }]
  };

  masterWaitingLine: CustomerWaiting[];
  waitingLine: CustomerWaiting[];
  stats: any = {
    waitingListCount: 0,
    insideStoreCount: 0,
    employeesCount: 0
  };

  constructor(
    private waitingListFirebaseService: WaitingListFirebaseService,
    private authService: AuthenticationService,
    private storeService: StoreService,
    private spinner: NgxSpinnerService,
  ) {
    this.getStoreWaitingList();

  }

  ngOnInit(): void {
  }

  getStoreWaitingList() {
    this.waitingListFirebaseService.getStoreWaitingList(this.authService.getStoreUser.storeId).subscribe((response) => {
      this.masterWaitingLine = response;
      this.waitingLine = response;
      this.stats.waitingListCount = response.length;
    });
  }

  onSearchInWaitingLine(event) {
    const val: string = event.target.value;

    if (!val) {
      this.waitingLine = this.masterWaitingLine;
    } else {
      this.waitingLine = this.masterWaitingLine.filter((x) =>
        x.name.toLowerCase().includes(val.toLocaleLowerCase())
      );
    }
  }

  customerArrivalInStore(userId) {
    this.spinner.show();
    this.storeService.customerArrival(userId, this.authService.getStoreUser.storeId).subscribe(
      (response) => {
        this.spinner.hide();
        NotyUtil.success("Customer moved into store");
        this.getStoreWaitingList();
      },
      (e) => {
        this.spinner.hide();
        const error = e.error.message || e.error.error;
        NotyUtil.error(error);
      },
      () => this.spinner.hide()
    );
  }

  customerDepartureFromStore(userId) {
    this.spinner.show();
    this.storeService.customerDeparture(userId, this.authService.getStoreUser.storeId).subscribe(
      (response) => {
        this.spinner.hide();
        NotyUtil.success("Customer departed from store");
        this.getStoreWaitingList();
      },
      (e) => {
        this.spinner.hide();
        const error = e.error.message || e.error.error;
        NotyUtil.error(error);
      },
      () => this.spinner.hide()
    );
  }
}
