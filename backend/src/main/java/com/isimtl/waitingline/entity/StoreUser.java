package com.isimtl.waitingline.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "store_user")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {""})
public class StoreUser {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @OneToOne
    @JoinColumn(name="store_id")
    private Store store;

    @Column(name = "role")
    private String role;

    @Override
    public String toString() {
        return "StoreUser{" +
                "userId=" + userId +
                ", store=" + store +
                ", role='" + role + '\'' + '}';
    }
}
