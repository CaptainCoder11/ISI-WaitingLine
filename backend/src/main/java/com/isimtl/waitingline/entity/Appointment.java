package com.isimtl.waitingline.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@Table(name = "appointment")
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "store_id")
    private int storeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppointmentStatus status;

    @Column(name = "appointment_number")
    private int appointmentNumber;

    @Column(name = "time_of_arrival")
    private LocalDateTime timeOfArrival;

    @Column(name = "time_of_departure")
    private LocalDateTime timeOfDeparture;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;


}
