package com.isimtl.waitingline.service;

import com.isimtl.waitingline.dto.EmployeeDTO;
import com.isimtl.waitingline.entity.User;

public interface IEmployeeService {

    public EmployeeDTO login(String email, String password);
}
