package com.isimtl.waitingline.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String password_hash;
    private String password_salt;
    private String otp;
    private LocalDateTime otp_expiry;
    private LocalDateTime dateTime;


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", password_salt='" + password_salt + '\'' +
                ", otp='" + otp + '\'' +
                ", otp_expiry=" + otp_expiry +
                ", dateTime=" + dateTime +
                '}';
    }
}
