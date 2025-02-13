package com.example.latihanapi;

public class LoginResponse {
    private boolean status;
    private String message;
    private String token;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
