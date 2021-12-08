import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ErrorsComponent } from './common/pages/errors/errors.component';

const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    children: [
      {
        path: '',
        loadChildren: () =>
          import('../app/customer/customer.module').then(
            (x) => x.CustomerModule
          ),
      },
      {
        path: 'store',
        loadChildren: () =>
          import('../app/store/store.module').then((x) => x.StoreModule),
      },
    ],
  },
  {
    path: 'erorr',
    component: ErrorsComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
