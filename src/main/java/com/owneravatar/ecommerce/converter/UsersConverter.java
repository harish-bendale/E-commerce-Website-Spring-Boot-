package com.owneravatar.ecommerce.converter;

import com.owneravatar.ecommerce.dto.UsersDTO;
import com.owneravatar.ecommerce.model.Users;

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
