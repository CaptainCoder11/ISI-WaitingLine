import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Store } from 'src/app/common/models/store.model';
import { StoreService } from 'src/app/common/services/store.service';
import * as Highcharts from "highcharts";

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

  store$: Observable<Store>;
  constructor(private storeService: StoreService) {
    this.store$ = this.storeService.getById(1);
  }

  ngOnInit(): void {
  }
}
