package com.isimtl.waitingline.mapper;

import com.isimtl.waitingline.dto.EmployeeDTO;
import com.isimtl.waitingline.entity.Employee;
import com.isimtl.waitingline.entity.User;

public class EmployeeMapper {

    private Employee employee;

    public EmployeeMapper(Employee employee) {
        this.employee = employee;
    }

    public EmployeeDTO getEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getId());
        employeeDTO.setRole(employee.getStoreUser().getRole());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setStoreId(employee.getStoreUser().getStore().getId());
        employeeDTO.setLogo(employee.getStoreUser().getStore().getLogo());
        employeeDTO.setStoreName(employee.getStoreUser().getStore().getName());
        employeeDTO.setAddress(employee.getStoreUser().getStore().getAddress());
        employeeDTO.setStoreCapacity(employee.getStoreUser().getStore().getStoreCapacity());
        employeeDTO.setCategory(employee.getStoreUser().getStore().getCategory());
        return employeeDTO;
    }
}
