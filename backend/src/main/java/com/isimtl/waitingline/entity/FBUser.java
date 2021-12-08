package com.isimtl.waitingline.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class FBUser {
    private String email;
    private String estimatedTime;
    private int id;
    private String name;
    private int queueNumber;
    private int storeId;
    private String storeLogo;
    private String storeName;
}
