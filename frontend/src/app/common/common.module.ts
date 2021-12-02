import { NgModule } from '@angular/core';
import { StoreService } from './services/store.service';
import { CommonModule as AngularCommonModule } from '@angular/common';
import { DialogModule } from '@progress/kendo-angular-dialog';

@NgModule({
  declarations: [
  ],
  imports: [
    AngularCommonModule,
    DialogModule
  ],
  providers: [StoreService],
  bootstrap: [],
  exports: [
    AngularCommonModule,
    DialogModule
  ]
})
export class CommonModule { }
