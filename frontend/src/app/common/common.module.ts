import { NgModule } from '@angular/core';
import { StoreService } from './services/store.service';
import { CommonModule as AngularCommonModule } from '@angular/common';

@NgModule({
  declarations: [
  ],
  imports: [
    AngularCommonModule,
  ],
  providers: [StoreService],
  bootstrap: [],
  exports: [
    AngularCommonModule,
  ]
})
export class CommonModule { }
