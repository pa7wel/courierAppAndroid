package com.paul.courierappandroid;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{
    private static final String LOGIN_REQUEST_URL = "http://192.168.0.2:3000/v1/sessions";
    private Map<String, String> params;

    public LoginRequest(String login, String password, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", login);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
