import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '../common/common.module';
import { CustomerFooterComponent } from './components/footer/footer.component';
import { CustomerHeaderComponent } from './components/header/header.component';
import { CustomerStoreDetailsComponent } from './components/store-details/store-details.component';
import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerHomeComponent } from './pages/home/home.component';
import { CustomerLoginComponent } from './pages/login/login.component';

@NgModule({
  declarations: [
    CustomerLoginComponent,
    CustomerHomeComponent,
    CustomerHeaderComponent,
    CustomerFooterComponent,
    CustomerStoreDetailsComponent
  ],
  imports: [
    ReactiveFormsModule,
    CustomerRoutingModule,
    CommonModule,
  ],
  providers: [],
})
export class CustomerModule { }
