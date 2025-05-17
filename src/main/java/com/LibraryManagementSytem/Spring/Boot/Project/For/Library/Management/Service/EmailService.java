package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {
    @Autowired
    JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) throws MessagingException, IOException {
        String htmlTemplate = loadTemplate("templates/otp-email-template.html");
        String content = htmlTemplate.replace("{{otp}}", otp);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("Your otp code");
        helper.setText(content, true);

        mailSender.send(message);
    }

    public String loadTemplate(String path) {
       try{
           InputStream inputStream = new ClassPathResource(path).getInputStream();

           return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
       }catch(Exception e) {
           throw new RuntimeException("Failed to load template", e);
       }
    }

    public void sendRegistrationMail(String toEmail) throws MessagingException {
        String mailContent = loadTemplate("templates/registration-email-template.html");

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(toEmail);
        helper.setSubject("Registration Successful");
        helper.setText(mailContent, true);

        mailSender.send(mimeMessage);
    }

    public void sendResetPasswordOtp(String toEmail, String otp) throws MessagingException {
        String htmlTemplate = loadTemplate("templates/passwordReset-otp-email-template.html");
        String content = htmlTemplate.replace("{{otp}}", otp);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject("Otp for resetting password");
        helper.setText(content, true);

        mailSender.send(message);
    }
}
