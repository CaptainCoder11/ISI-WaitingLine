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

@Component({
  selector: 'app-customer-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class CustomerLoginComponent implements OnInit {
  form: FormGroup;
  submitted: boolean;

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.form = new FormGroup({
      name: new FormControl('', Validators.required),
      email: new FormControl('', Validators.email),
      mobile: new FormControl(
        '',
        Validators.pattern('^((\\+91-?)|0)?[0-9]{10}$')
      ),
      otp: new FormControl(''),
    });
    this.form.setErrors({ emailOrPhoneRequired: false });
  }
  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) return;

    const email = this.getControl('email').value;
    const mobile = this.getControl('mobile').value;

    if (!email && !mobile) {
      this.form.setErrors({ emailOrPhoneRequired: true });
      return;
    } else {
      this.form.setErrors({ emailOrPhoneRequired: false });
    }

    alert('API Pending');
    console.warn(this.form.value);
    this.submitted = false;
  }

  getControl(key) {
    return this.form.get(key);
  }
}
