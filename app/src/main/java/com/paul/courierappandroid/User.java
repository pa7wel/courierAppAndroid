package com.paul.courierappandroid;

/**
 * Created by pawel on 01.06.2017.
 */

public class User {

    private String email;
    private String auth_token;

    public User(String email, String auth_token) {
        this.email = email;
        this.auth_token = auth_token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }
}
