package com.paul.courierappandroid.API;

/**
 * Created by pawel on 04.06.2017.
 */

public class IUser {
    private String email;
    private String authentication_token;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public IUser(String email, String auth_token, int userId) {

        this.email = email;
        this.authentication_token = auth_token;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuth_token() {
        return authentication_token;
    }

    public void setAuth_token(String auth_token) {
        this.authentication_token = auth_token;
    }
}


