package com.ethane3.springsecurityclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String firstName;
    private String lastName; // Ensure this matches the JSON field
    private String email;
    private String password;
    private String matchingPassword;
}
