import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StoreLoginComponent } from './login/login.component';
import { AuthenticationService } from './services/authentication.service';
import { WaitingListFirebaseService } from './services/waiting-list.firebase.service';
import { CommonModule as AngularCommonModule } from '@angular/common';
import { environment } from 'src/environments/environment';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxSpinnerModule } from 'ngx-spinner';
import { HttpClientModule } from '@angular/common/http';
import { provideFirebaseApp } from '@angular/fire/app';
import { provideFirestore } from '@angular/fire/firestore';
import { getFirestore } from '@firebase/firestore';
import { initializeApp } from '@firebase/app';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StoreDashboardComponent } from './dashboard/dashboard.component';


@NgModule({
  declarations: [
    AppComponent,
    StoreLoginComponent,
    StoreDashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularCommonModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgxSpinnerModule,
    provideFirebaseApp(() => initializeApp(environment.firebaseConfig)),
    provideFirestore(() => getFirestore()),
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [ WaitingListFirebaseService, AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
