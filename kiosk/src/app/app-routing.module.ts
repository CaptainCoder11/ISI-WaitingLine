import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StoreDashboardComponent } from './dashboard/dashboard.component';
import { StoreLoginComponent } from './login/login.component';

const routes: Routes = [
  {
    path: '',
    component: StoreLoginComponent,
  },
  {
    path: 'dashboard',
    component: StoreDashboardComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
