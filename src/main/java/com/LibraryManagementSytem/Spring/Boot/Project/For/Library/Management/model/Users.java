package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private BigDecimal mobileNo;

    @Transient
    private String otp;

    public Users(Long userId) {
        this.id = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(BigDecimal mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    //    public String getPassword() {
//        return this.password;
//    }
//
//    public String getUsername() {
//        return this.username;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return this.email;
//    }
//
//    public String getOtp() {
//        return this.otp;
//    }
}
