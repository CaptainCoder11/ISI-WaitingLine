import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validator,
  Validators,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { User } from 'src/app/common/models/user.model';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { NotyUtil } from 'src/app/common/utils/noty-util';
import { Employee } from '../../models/employee.model';

@Component({
  selector: 'app-store-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class StoreLoginComponent implements OnInit {
  form: FormGroup;
  submitted: boolean;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthenticationService,
    private spinner: NgxSpinnerService
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
    console.warn(this.form.value);
    this.authService.loginEmp(this.form.value).subscribe(
      (employee: Employee) => {
        this.spinner.hide();
        NotyUtil.success('Successfully login');
        this.form.get('id').setValue(employee.id);
        this.submitted = false;
      },
      (e) => {
        this.spinner.hide();
        const error = e.error.message || e.error.error;
        NotyUtil.error(error);
      },
      () => {
        this.spinner.hide();
      }
    );
  }

  getControl(key) {
    return this.form.get(key);
  }
}
