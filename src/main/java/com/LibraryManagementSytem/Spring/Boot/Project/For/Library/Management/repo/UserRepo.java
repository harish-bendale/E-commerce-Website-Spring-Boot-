package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.repo;

import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Users findByEmail(String email);
}
