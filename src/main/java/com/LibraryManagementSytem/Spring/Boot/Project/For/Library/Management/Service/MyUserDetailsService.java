package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.Service;

import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.UserPrincipal;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.Users;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.repo.UserRepo;
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
