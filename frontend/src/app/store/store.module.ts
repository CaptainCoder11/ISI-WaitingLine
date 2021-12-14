import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { StoreFooterComponent } from './components/footer/footer.component';
import { StoreHeaderComponent } from './components/header/header.component';
import { StoreInfoComponent } from './components/store-info/store-info.component';
import { StoreDashboardComponent } from './pages/dashboard/dashboard.component';
import { StoreLoginComponent } from './pages/login/login.component';
import { StoreRoutingModule } from './store-routing.module';
import { StoreComponent } from '../store/store.component';
import { StoreEmployeeComponent } from './pages/employee/employee.component';
import { EmployeeService } from './services/employee.service';
import { CommonModule } from '../common/common.module';
import { StoreService } from '../common/services/store.service';
import { StoreEmployeeFormComponent } from './components/employee-form/employee-form.component';
import { StoreEmployeeListComponent } from './components/employee-list/employee-list.component';
import { HighchartsChartModule } from 'highcharts-angular';
import { WaitingComponent } from './pages/waiting/waiting.component';

const DashboardComponents = [StoreDashboardComponent, StoreInfoComponent];

const EmployeeComponents = [
  StoreEmployeeComponent,
  StoreEmployeeListComponent,
  StoreEmployeeFormComponent,
  WaitingComponent,
];

@NgModule({
  declarations: [
    StoreLoginComponent,
    StoreComponent,
    StoreHeaderComponent,
    StoreFooterComponent,
    DashboardComponents,
    EmployeeComponents,
  ],
  imports: [
    ReactiveFormsModule,
    StoreRoutingModule,
    HighchartsChartModule,
    CommonModule,
  ],
  providers: [StoreService, EmployeeService],
})
export class StoreModule {}
