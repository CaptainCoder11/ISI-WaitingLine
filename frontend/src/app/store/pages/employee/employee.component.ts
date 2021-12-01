import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from 'src/app/store/models/employee.model';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-store-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class StoreEmployeeComponent implements OnInit {

  employees$: Observable<Employee[]>;
  constructor(private employeeService: EmployeeService) {
    this.employees$ = this.employeeService.getAll();
  }

  ngOnInit(): void {
  }
}
