package com.isimtl.waitingline.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"password_hash","password_salt","otp_expiry","date_added"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @JsonIgnore
    @Column(name = "password_hash")
    private String passwordHash;

    @JsonIgnore
    @Column(name = "password_salt")
    private String passwordSalt;

    @JsonIgnore
    @Column(name = "otp")
    private String otp;

    @JsonIgnore
    @Column(name = "otp_expiry")
    private LocalDateTime otpExpiry;

    @JsonIgnore
    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @JsonIgnore
    public String getVerificationData(){
        return  getName()+ "/" + getEmail() +"/"+ getPhone() +"/"+getOtp();
    }

}
