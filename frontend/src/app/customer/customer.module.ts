import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '../common/common.module';
import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerFooterComponent } from './pages/footer/footer.component';
import { CustomerHeaderComponent } from './pages/header/header.component';
import { CustomerHomeComponent } from './pages/home/home.component';
import { CustomerLoginComponent } from './pages/login/login.component';

@NgModule({
  declarations: [
    CustomerLoginComponent,
    CustomerHomeComponent,
    CustomerHeaderComponent,
    CustomerFooterComponent
  ],
  imports: [
    ReactiveFormsModule,
    CustomerRoutingModule,
    CommonModule
  ],
  providers: [],
})
export class CustomerModule { }