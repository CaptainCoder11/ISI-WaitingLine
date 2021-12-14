package com.isimtl.waitingline.service;

import com.isimtl.waitingline.Utils.Utils;
import com.isimtl.waitingline.dto.EmployeeDTO;
import com.isimtl.waitingline.entity.Employee;
import com.isimtl.waitingline.entity.User;
import com.isimtl.waitingline.mapper.EmployeeMapper;
import com.isimtl.waitingline.repository.EmployeeRepository;
import com.isimtl.waitingline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO login(String email, String password) {
        System.out.println("hello");
        Optional<Employee> tempUser = employeeRepository.findByEmail(email);
        if (tempUser.isPresent()) {
            if (Utils.checkPass(password, tempUser.get().getPassword())) {
                EmployeeDTO employeeDTO = new EmployeeMapper(tempUser.get()).getEmployee();
                return employeeDTO;

            } else
                throw new RuntimeException("Invalid password");
        } else
            throw new RuntimeException("Email not exist.");
    }
}
