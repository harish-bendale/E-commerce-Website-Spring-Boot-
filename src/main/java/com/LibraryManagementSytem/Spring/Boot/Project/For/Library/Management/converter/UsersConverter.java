package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.converter;

import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.UsersDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.Users;

public class UsersConverter {
    public static UsersDTO toDTO(Users user) {
        UsersDTO dto = new UsersDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setMobileNo(user.getMobileNo());

        return dto;
    }
}
