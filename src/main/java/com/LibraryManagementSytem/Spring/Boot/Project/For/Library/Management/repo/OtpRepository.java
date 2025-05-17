package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.repo;

import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.enums.OtpPurpose;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.Otp;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByEmailAndCode(String email, String code);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM otp WHERE created_at < NOW() - INTERVAL '5 minutes'", nativeQuery = true)
    void deleteExpiredOtp();

    @Modifying
    @Transactional
    void deleteByEmail(String email);

    Optional<Otp> findByEmailAndCodeAndPurpose(String email, String code, OtpPurpose purpose);
}
