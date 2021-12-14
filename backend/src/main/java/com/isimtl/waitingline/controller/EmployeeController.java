package com.isimtl.waitingline.controller;


import com.isimtl.waitingline.dto.EmployeeDTO;
import com.isimtl.waitingline.entity.User;
import com.isimtl.waitingline.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<EmployeeDTO> loginCustomer(@RequestParam("email") String email, @RequestParam("password") String password) {
        EmployeeDTO employeeDTO = employeeService.login(email, password);
        return ResponseEntity.ok(employeeDTO);
    }

    @RequestMapping(value = "/user-arrival", method = RequestMethod.POST)
    public void userArrival() {

    }

    @RequestMapping(value = "/user-departure", method = RequestMethod.POST)
    public void userDeparture() {

    }


}
