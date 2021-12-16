import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { StoreUser } from '../models/store-user.model';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-store-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class StoreLoginComponent implements OnInit {
  form: FormGroup;
  submitted: boolean;
  employee: StoreUser;

  constructor(
    private authService: AuthenticationService,
    private spinner: NgxSpinnerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.form = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.form.invalid) return;

    this.spinner.show();
    this.authService.storeLogin(this.form.value).subscribe(
      (employee: StoreUser) => {
        this.spinner.hide();
        // NotyUtil.success('Successfully login');
        this.submitted = false;
        this.router.navigate(['/dashboard']);
      },
      (e) => {
        this.spinner.hide();
        const error = e.error.message || e.error.error;
        // NotyUtil.error(error);
      },
      () => {
        this.spinner.hide();
      }
    );
  }

  getControl(key: string) {
    return this.form.get(key);
  }
}
