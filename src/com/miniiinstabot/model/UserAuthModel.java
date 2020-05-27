package com.miniiinstabot.model;

public class UserAuthModel {
    
    String email;
    String password;

    public UserAuthModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    
}
