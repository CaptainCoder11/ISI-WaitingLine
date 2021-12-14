import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { StoreUser } from 'src/app/store/models/store-user.model';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-store-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css'],
})
export class StoreEmployeeComponent implements OnInit {
  employees$: Observable<StoreUser[]>;
  constructor(private employeeService: EmployeeService) {
    this.employees$ = this.employeeService.getAll();
  }

  ngOnInit(): void {}
}
