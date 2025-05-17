package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private Long id;
    private String username;
    private String email;
    private BigDecimal mobileNo;

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


}
