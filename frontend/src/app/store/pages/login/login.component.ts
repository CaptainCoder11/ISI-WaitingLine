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
  selector: 'app-store-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class StoreLoginComponent implements OnInit {
  form: FormGroup;
  submitted: boolean;

  ngOnInit(): void {
    this.initForm();
  }
  initForm() {
    this.form = new FormGroup({
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
  }

  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) return;

    alert('API Pending');
    console.warn(this.form.value);
    this.submitted = false;
  }

  getControl(key) {
    return this.form.get(key);
  }
}
