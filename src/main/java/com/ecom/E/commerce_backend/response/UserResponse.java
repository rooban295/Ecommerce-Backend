package com.ecom.E.commerce_backend.response;


import com.ecom.E.commerce_backend.model.User_Role;
import lombok.Data;

@Data
public class UserResponse {

    private String fullName;

    private String email;

    private User_Role role;

    private String  msg;
}
