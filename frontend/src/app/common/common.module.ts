import { NgModule } from '@angular/core';
import { StoreService } from './services/store.service';
import { CommonModule as AngularCommonModule } from '@angular/common';
import { DialogModule } from '@progress/kendo-angular-dialog';
import { WaitingListFirebaseService } from './services/waiting-list.firebase.service';
import { NgxSpinnerModule } from 'ngx-spinner';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from './services/authentication.service';


@NgModule({
  declarations: [
  ],
  imports: [AngularCommonModule, DialogModule, NgxSpinnerModule, FormsModule],
  providers: [StoreService, WaitingListFirebaseService, AuthenticationService],
  bootstrap: [],
  exports: [AngularCommonModule, DialogModule, NgxSpinnerModule, FormsModule],
})
export class CommonModule { }
