package com.ethane3.springsecurityclient.event.listener;

import com.ethane3.springsecurityclient.entity.User;
import com.ethane3.springsecurityclient.event.RegistrationCompleteEvent;
import com.ethane3.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender; // Inject the mail sender

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // Create verification Token for user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);

        // Send email to user
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;

        // Call method to send email
        sendVerificationEmail(user, url);
    }

    private void sendVerificationEmail(User user, String url) {
        // Create email content
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration");
        mailMessage.setFrom("ethanepro03@gmail.com");
        mailMessage.setText("To confirm your account, please click the following link: " + url);

        // Send the email
        mailSender.send(mailMessage);
        log.info("Verification email sent to: {}", user.getEmail());
    }
}
