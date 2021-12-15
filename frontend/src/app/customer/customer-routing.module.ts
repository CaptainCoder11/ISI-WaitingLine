import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IsAuthenticatedCustomerGuard } from './guards/is-authenticated-customer.guard';
import { IsNotAuthenticatedStoreGuard } from './guards/is-not-authenticated-store.guard';
import { CustomerHomeComponent } from './pages/home/home.component';
import { CustomerLoginComponent } from './pages/login/login.component';

const routes: Routes = [
  {
    path: '',
    component: CustomerHomeComponent,
    canActivate: [IsAuthenticatedCustomerGuard],
    canActivateChild: [IsAuthenticatedCustomerGuard],
  }, {
    path: 'login',
    component: CustomerLoginComponent,
    canActivate: [IsNotAuthenticatedStoreGuard],
    canActivateChild: [IsNotAuthenticatedStoreGuard],
  },
  {
    path: 'login/:id/:name/:email/:phone/:otp',
    component: CustomerLoginComponent,
    canActivate: [IsNotAuthenticatedStoreGuard],
    canActivateChild: [IsNotAuthenticatedStoreGuard],
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
