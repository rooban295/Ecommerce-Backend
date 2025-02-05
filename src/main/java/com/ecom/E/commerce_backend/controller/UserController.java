package com.ecom.E.commerce_backend.controller;

import com.ecom.E.commerce_backend.model.User;
import com.ecom.E.commerce_backend.response.UserResponse;
import com.ecom.E.commerce_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/userinfo")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public UserResponse userInfo(@RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.getUserByJwtToken(jwt);
        if(user==null){
            throw new Exception("user not found");
        }
        UserResponse res= new UserResponse();
        res.setFullName(user.getFullName());
        res.setEmail(user.getEmail());
        res.setRole(user.getRole());
        res.setMsg("Login Successfully");
        return res;
    }
}
