import { Component, OnInit } from '@angular/core';
import { DialogRef, DialogService } from '@progress/kendo-angular-dialog';
import { Observable } from 'rxjs';
import { StoreUser } from 'src/app/store/models/store-user.model';
import { EmployeeService } from '../../services/employee.service';
import { EmployeeFormComponent } from '../employee-form/employee-form.component';

@Component({
  selector: 'app-store-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css'],
})
export class StoreEmployeeComponent implements OnInit {
  employees$: Observable<StoreUser[]>;
  employees: () => {
    email: string;
    id: number;
    fullName: string;
    phone: number;
    storeId: number;
  }[];
  constructor(
    private employeeService: EmployeeService,
    private dialogService: DialogService
  ) {
    this.employees$ = this.employeeService.getAll();
  }

  ngOnInit(): void {}

  addEmployee() {
    const dialog: DialogRef = this.dialogService.open({
      content: EmployeeFormComponent,
      width: 600,
    });
    const inputData = dialog.content.instance;
    dialog.result.subscribe();
  }
}
