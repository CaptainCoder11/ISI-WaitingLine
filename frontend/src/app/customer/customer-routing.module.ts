import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerHomeComponent } from './pages/home/home.component';
import { CustomerLoginComponent } from './pages/login/login.component';

const routes: Routes = [
  {
    path: '',
    component: CustomerHomeComponent,
  }, {
    path: 'login',
    component: CustomerLoginComponent,
  },
  {
    path: 'login/:id/:name/:email/:phone/:otp',
    component: CustomerLoginComponent,
  },
  {
    path: 'dashboard',
    component: CustomerHomeComponent
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
