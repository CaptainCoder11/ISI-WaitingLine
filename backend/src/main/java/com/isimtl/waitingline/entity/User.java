package com.isimtl.waitingline.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Getter
@Setter
@Table(name = "user")
@JsonIgnoreProperties(value = {"otp_expiry", "date_added"})
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "otp")
    private String otp;

    @JsonIgnore
    @Column(name = "otp_expiry")
    private LocalDateTime otpExpiry;

    @JsonIgnore
    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @JsonIgnore
    public String getVerificationData() {
        return getId()+ "/" +getName() + "/" + getEmail() + "/" + getPhone() + "/" + getOtp();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", otp='" + otp + '\'' +
                ", otpExpiry=" + otpExpiry +
                ", dateAdded=" + dateAdded +
                '}';
    }

}
