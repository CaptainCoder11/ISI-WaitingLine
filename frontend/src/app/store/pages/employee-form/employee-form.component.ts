import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DialogRef } from '@progress/kendo-angular-dialog';
import { NotyUtil } from 'src/app/common/utils/noty-util';
import { Router } from '@angular/router';
@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css'],
})
export class EmployeeFormComponent implements OnInit {
  form: FormGroup;
  submitted: boolean;
  constructor(private dialog: DialogRef, private router: Router) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      name: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
      phone: new FormControl('', Validators.required),
    });
  }
  onSubmit() {
    this.submitted = true;
    console.log(this.form);
    if (this.form.invalid) return;

    NotyUtil.success('Employee Added');
    this.submitted = false;
    this.router.navigate(['store', 'employees']);
  }
  onCancel(e: Event): void {
    this.closeForm();
  }

  closeForm(): void {
    this.dialog.close();
  }
  getControl(key) {
    return this.form.get(key);
  }
}
