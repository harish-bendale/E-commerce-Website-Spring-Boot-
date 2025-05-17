package com.owneravatar.ecommerce.Service;

import com.owneravatar.ecommerce.converter.UsersConverter;
import com.owneravatar.ecommerce.dto.UsersDTO;
import com.owneravatar.ecommerce.model.Users;
import com.owneravatar.ecommerce.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepo repo;

    public Users regiser(Users user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    public Users findByEmail(String email) {

        return repo.findByEmail(email);
    }

    public List<UsersDTO> getAllUsers(){

        return repo.findAll().stream()
                .map(UsersConverter::toDTO)
                .collect(Collectors.toList());
    }

    public UsersDTO getUserByEmail(String email) {

        return UsersConverter.toDTO(repo.findByEmail(email));
    }

    public void save(Users user) {
        repo.save(user);
    }


}
