package com.ethane3.springsecurityclient.controller;

import com.ethane3.springsecurityclient.entity.User;
import com.ethane3.springsecurityclient.event.RegistrationCompleteEvent;
import com.ethane3.springsecurityclient.model.UserModel;
import com.ethane3.springsecurityclient.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, HttpServletRequest request) {
        logger.info("Received registration request for email: {}", userModel.getEmail());
        try {
            User user = userService.registerUser(userModel);
            String applicationUrl = getApplicationUrl(request);
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl));
            logger.info("User registered successfully: {}", user.getEmail());
            return "Success! Please check your email to verify your account.";
        } catch (Exception e) {
            logger.error("Error during user registration", e);
            return "Registration failed: " + e.getMessage();
        }
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){

        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User verifies successfully";
        }
        return "Bad user";
    }

    private String getApplicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }


}