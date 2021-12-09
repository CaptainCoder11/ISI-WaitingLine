package com.isimtl.waitingline.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@Table(name = "appointment")
@AllArgsConstructor
public class Appoinment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "store_id")
    private int storeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppoinmentStatus status;

    @Column(name = "appointment_number")
    private int appoinmentNumber;

    @Column(name = "time_of_arrival")
    private LocalDateTime timeOfArrival;

    @Column(name = "time_of_departure")
    private LocalDateTime timeOfDeparture;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

}
