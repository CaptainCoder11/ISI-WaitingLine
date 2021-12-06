package com.isimtl.waitingline.dto;


import lombok.Data;

import java.sql.Time;
import java.util.Arrays;
import java.util.UUID;


@Data
public class StoreDTO {

    private UUID id;
    private String name;
    private String address;

    private int storeCapacity;

    private int waitingCapacity;
    private String category;
    private byte[] logo;
    private Time openingHour;
    private Time closingHour;

    @Override
    public String toString() {
        return "StoreDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", storeCapacity=" + storeCapacity +
                ", waitingCapacity=" + waitingCapacity +
                ", category='" + category + '\'' +
                ", logo=" + Arrays.toString(logo) +
                ", openingHour=" + openingHour +
                ", closingHour=" + closingHour +
                '}';
    }
}
