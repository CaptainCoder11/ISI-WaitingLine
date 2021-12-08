import { PlatformLocation } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { User } from 'src/app/common/models/user.model';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { NotyUtil } from 'src/app/common/utils/noty-util';

@Component({
  selector: 'app-customer-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class CustomerLoginComponent implements OnInit {
  form: FormGroup;
  submitted: boolean;
  id: string;
  user: User;
  showOtp: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthenticationService,
    private spinner: NgxSpinnerService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.user = {
        id: params["id"],
        name: params["name"],
        email: params["email"],
        phone: params["phone"],
        otp: params["otp"],
      }
      if (this.user.email && this.user.phone && this.user.otp) {
        this.showOtp = true;
      }
    });
    this.initForm();
  }

  initForm() {
    this.form = new FormGroup({
      id: new FormControl(this.user.id),
      name: new FormControl(this.user.name, Validators.required),
      email: new FormControl(this.user.email, [Validators.required, Validators.email]),
      phone: new FormControl(this.user.phone),
      otp: new FormControl(this.user.otp),
      showOtp: new FormControl(this.showOtp)
    });

    if (this.showOtp)
      this.form.get('otp').setValidators(Validators.required);

    this.form.get('showOtp').valueChanges.subscribe((value) => {
      if (value) {
        this.form.get('otp').setValidators(Validators.required);
      } else {
        this.form.get('otp').reset();
        this.form.get('otp').clearValidators();
      }
      this.form.get('otp').updateValueAndValidity();
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.form.invalid) return;

    if (!this.isOtp) {
      this.spinner.show();
      this.authService.login(this.form.value).subscribe((user: User) => {
        this.spinner.hide();
        NotyUtil.success("Email has been sent for OTP");
        this.form.get('id').setValue(user.id);
        this.submitted = false;
        this.form.get('showOtp').setValue(true);
      }, (e) => {
        this.spinner.hide();
        const error = e.error.message || e.error.error;
        NotyUtil.error(error);
      }, () => {
        this.spinner.hide();
      });
    } else {
      this.spinner.show();
      this.authService.verifyOtp(this.form.value).subscribe(() => {
        this.spinner.hide();
        NotyUtil.success("OTP verification complete");
        this.submitted = false;
        this.form.get('showOtp').setValue(false);
        this.router.navigate(['/']);
      }, (e) => {
        this.spinner.hide();
        const error = e.error.message || e.error.error;
        NotyUtil.error(error);
      }, () => {
        this.spinner.hide();
      });
    }
  }

  getControl(key) {
    return this.form.get(key);
  }

  get isOtp() {
    return this.getControl('showOtp').value
  }
}
