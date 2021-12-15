import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ROLES } from '../common/models/roles.model';
import { CanVisitStoreGuard } from './guards/can-visit-store.guard';
import { IsAuthenticatedStoreGuard } from './guards/is-authenticated-store.guard';
import { IsNotAuthenticatedCustomerGuard } from './guards/is-not-authenticated-customer.guard';
import { StoreDashboardComponent } from './pages/dashboard/dashboard.component';
import { StoreEmployeeComponent } from './pages/employee/employee.component';
import { StoreLoginComponent } from './pages/login/login.component';
import { StoreComponent } from './store.component';
const routes: Routes = [
  {
    path: 'login',
    component: StoreLoginComponent,
    canActivate: [IsNotAuthenticatedCustomerGuard],
    canActivateChild: [IsNotAuthenticatedCustomerGuard],
  },
  {
    path: '',
    component: StoreComponent,
    canActivate: [IsAuthenticatedStoreGuard],
    canActivateChild: [IsAuthenticatedStoreGuard],
    children: [
      {
        path: 'dashboard',
        component: StoreDashboardComponent,
        canActivate: [CanVisitStoreGuard],
        canActivateChild: [CanVisitStoreGuard],
        data: {
          title: 'Store dashboard',
          roles: [ROLES.EMPLOYEE, ROLES.STOREOWNER]
        },
      },
      {
        path: 'employees',
        component: StoreEmployeeComponent,
        canActivate: [CanVisitStoreGuard],
        canActivateChild: [CanVisitStoreGuard],
        data: {
          title: 'Store employees',
          roles: [ROLES.EMPLOYEE, ROLES.STOREOWNER]
        },
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StoreRoutingModule { }
