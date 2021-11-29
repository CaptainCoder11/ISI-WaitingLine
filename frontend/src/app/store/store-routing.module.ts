import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StoreDashboardComponent } from './pages/dashboard/dashboard.component';
import { StoreEmployeeComponent } from './pages/employee/employee.component';
import { StoreLoginComponent } from './pages/login/login.component';
import { StoreComponent } from './store.component';

const routes: Routes = [{
  path: '',
  component: StoreComponent,
  children: [
    {
      path: 'login',
      component: StoreLoginComponent,
    },
    {
      path: 'dashboard',
      component: StoreDashboardComponent
    },
    {
      path: 'employee',
      component: StoreEmployeeComponent
    }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StoreRoutingModule { }
