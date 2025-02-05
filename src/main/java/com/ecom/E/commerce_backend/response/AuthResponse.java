package com.ecom.E.commerce_backend.response;

import com.ecom.E.commerce_backend.model.User_Role;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;

    private String message;

    private User_Role role;
}
