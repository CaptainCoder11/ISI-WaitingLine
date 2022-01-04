package com.isimtl.waitingline.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {
    private int employeeId;
    private String role;
    private String name;
    private String email;
    private String phone;

    private int storeId;
    private String logo;
    private String storeName;
    private String address;
    private int  storeCapacity;
    private String category;



}
