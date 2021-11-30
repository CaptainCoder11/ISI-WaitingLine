package com.isimtl.waitingline.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

import java.sql.Time;

@Entity
@Table(name="store")
public class Store {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column( name = "id", columnDefinition = "BINARY(16)" )
    private UUID id;


    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="store_capacity")
    private int storeCapacity;

    @Column(name="waiting_capacity")
    private int waitingCapacity;

    @Column(name="category")
    private String category;

    @Column(name="opening_hour")
    private Time openingHour;

    @Column(name="closing_hour")
    private Time closingHour;

    public Store(){}

    public Store(String name, String address, int storeCapacity, int waitingCapacity, String category, Time openingHour, Time closingHour) {
        this.name = name;
        this.address = address;
        this.storeCapacity = storeCapacity;
        this.waitingCapacity = waitingCapacity;
        this.category = category;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStoreCapacity() {
        return storeCapacity;
    }

    public void setStoreCapacity(int storeCapacity) {
        this.storeCapacity = storeCapacity;
    }

    public int getWaitingCapacity() {
        return waitingCapacity;
    }

    public void setWaitingCapacity(int waitingCapacity) {
        this.waitingCapacity = waitingCapacity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Time getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(Time openingHour) {
        this.openingHour = openingHour;
    }

    public Time getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(Time closingHour) {
        this.closingHour = closingHour;
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
                ", openingHour=" + openingHour +
                ", closingHour=" + closingHour +
                '}';
    }
}
