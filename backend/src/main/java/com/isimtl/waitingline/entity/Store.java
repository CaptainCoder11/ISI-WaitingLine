package com.isimtl.waitingline.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="store")
public class Store {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column( name = "id", columnDefinition = "BINARY(16)" )
    private UUID id;

    private UUID storeOwnerId;

    private String name;

    private String address;

    private int store_capacity;

    private int waiting_capacity;

    private String category;

    private LocalDateTime opening_hour;

    private LocalDateTime closing_hour;



}
