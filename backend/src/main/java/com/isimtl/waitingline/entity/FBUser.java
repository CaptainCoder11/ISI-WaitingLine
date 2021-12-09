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


    public FBUser(Store store, User user) {
        email = user.getEmail();
        estimatedTime = "20";
        id = user.getId();
        name = user.getName();
        queueNumber = 5;
        storeId = store.getId();
        storeLogo = store.getLogo();
        storeName = store.getName();

    }
}
