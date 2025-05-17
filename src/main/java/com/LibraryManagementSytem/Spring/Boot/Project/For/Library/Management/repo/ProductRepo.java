package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.repo;

import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
