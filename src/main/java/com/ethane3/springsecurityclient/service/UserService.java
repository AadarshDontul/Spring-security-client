package com.ethane3.springsecurityclient.service;

import com.ethane3.springsecurityclient.entity.User;
import com.ethane3.springsecurityclient.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);
}
