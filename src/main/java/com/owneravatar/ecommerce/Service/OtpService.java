package com.owneravatar.ecommerce.Service;

import com.owneravatar.ecommerce.enums.OtpPurpose;
import com.owneravatar.ecommerce.model.Otp;
import com.owneravatar.ecommerce.repo.OtpRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {
    @Autowired
    private OtpRepository otpRepository;

    @Transactional
    public String generateOtp(String email, String purpose) {
        System.out.println(email + " " + purpose);
        String otpCode = String.format("%06d", new Random().nextInt(999999));
        Otp otp = new Otp();
        otp.setEmail(email);
        otp.setCode(otpCode);
        otp.setPurpose(OtpPurpose.valueOf(purpose));

        otpRepository.deleteByEmail(email);
        otpRepository.save(otp);

        return otpCode;
    }

    public boolean validateOtp(String email, String code, OtpPurpose purpose) {
        
        Optional<Otp> otp = otpRepository.findByEmailAndCodeAndPurpose(email, code, purpose);
        return otp.isPresent();
    }

    @Transactional
    @Scheduled(fixedRate = 30000)
    public void deleteExpiredOtp() {
        System.out.println("deleteExpiredOtp() called");
        otpRepository.deleteExpiredOtp();
    }
}
