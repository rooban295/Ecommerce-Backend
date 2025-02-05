package com.ecom.E.commerce_backend.request;


import lombok.Data;

@Data
public class SignInRequest {

    private String userName;

    private String password;
}
