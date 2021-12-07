package com.isimtl.waitingline.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Arrays;


@Data
@Entity
@Table(name = "store")
@AllArgsConstructor
@JsonIgnoreProperties(value = {""})
public class Store implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "store_capacity")
    private int storeCapacity;

    @Column(name = "waiting_capacity")
    private int waitingCapacity;

    @Column(name = "category")
    private String category;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "opening_hour")
    private LocalTime openingHour;

    @Column(name = "closing_hour")
    private LocalTime closingHour;

    public Store() {
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
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
