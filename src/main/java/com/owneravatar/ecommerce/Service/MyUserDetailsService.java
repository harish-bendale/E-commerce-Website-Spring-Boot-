package com.owneravatar.ecommerce.Service;

import com.owneravatar.ecommerce.model.UserPrincipal;
import com.owneravatar.ecommerce.model.Users;
import com.owneravatar.ecommerce.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("User not found");
        return new UserPrincipal(user);
    }
}
