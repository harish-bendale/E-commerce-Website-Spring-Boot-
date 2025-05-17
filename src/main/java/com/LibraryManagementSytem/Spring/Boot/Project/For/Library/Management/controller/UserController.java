package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.controller;

import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.Service.EmailService;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.Service.OtpService;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.Service.UserService;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.CreateOtpDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.ForgotPasswordDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.ResetPasswordDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.UsersDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.enums.OtpPurpose;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.exception.ResourceNotFoundException;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.Otp;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.Users;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.repo.UserRepo;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    OtpService otpService;

    @Autowired
    EmailService emailService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        System.out.println("Otp Code" + user.getEmail() + user.getOtp());
        boolean valid = otpService.validateOtp(user.getEmail(), user.getOtp(), OtpPurpose.REGISTRATION);
        if(!valid) {
            return new ResponseEntity<>("Otp not matched", HttpStatus.NOT_ACCEPTABLE);
        }

        Users users = null;
        users = service.findByEmail(user.getEmail());
        if(users == null) {
            service.regiser(user);
            try {
                emailService.sendRegistrationMail(user.getEmail());
            }catch(Exception e) {
                System.out.println(e.getMessage());
            }
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Already registered , please go to login page", HttpStatus.CONFLICT);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO dto) {
        System.out.println(dto.getEmail() + " " + dto.getOtp());
        Users user = service.findByEmail(dto.getEmail());
        if(user == null) {
            return new ResponseEntity<>("User not exists", HttpStatus.NOT_FOUND);
        }

        boolean isValid = otpService.validateOtp(dto.getEmail(), dto.getOtp(), OtpPurpose.RESET_PASSWORD);
        if(isValid) {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            boolean isMatch = encoder.matches(dto.getOldPassword(), user.getPassword());

            if(isMatch) {
                user.setPassword(encoder.encode(dto.getNewPassword()));
                service.save(user);
                return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
            }

            return new ResponseEntity<>("Old password is not correct . Please enter correct old password", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("otp is not correct", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordDTO dto) {
        Users user = service.findByEmail(dto.getEmail());
        if(user == null) {
            return new ResponseEntity<>("User not exists", HttpStatus.NOT_FOUND);
        }

        boolean isValid = otpService.validateOtp(dto.getEmail(), dto.getOtp(), OtpPurpose.FORGOT_PASSWORD);
        if(isValid) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(dto.getNewPassword()));
            service.save(user);
            return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("otp is not correct", HttpStatus.BAD_REQUEST);
    }
    public boolean isValidPurpose(String input) {
        return Arrays.stream(OtpPurpose.values())
                .anyMatch(purpose -> purpose.name().equalsIgnoreCase(input));
    }

    @PostMapping("getOtp")
    public ResponseEntity<String> getOtp(@RequestBody CreateOtpDTO otp) {
        try{
            if(!isValidPurpose(otp.getPurpose())) {
                return new ResponseEntity<>("Purpose is invalid", HttpStatus.BAD_REQUEST);
            }
            String otps = otpService.generateOtp(otp.getEmail(), otp.getPurpose());
            try {
                emailService.sendOtpEmail(otp.getEmail(), otps);
            }catch(Exception e) {
                System.out.println(e.getMessage());
            }

            return new ResponseEntity<>(otps, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>("Error to generate Otp" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try{
            return new ResponseEntity<>(service.getUserByEmail(email), HttpStatus.OK);
        }catch(ResourceNotFoundException e) {
            return new ResponseEntity<>("User account with email id " + email + " is not found", HttpStatus.NOT_FOUND);
        }
    }
}
