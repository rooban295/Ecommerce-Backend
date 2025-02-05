package com.ecom.E.commerce_backend.service;


import com.ecom.E.commerce_backend.model.User;
import org.springframework.stereotype.Service;


public interface UserService {


    public User getUserByJwtToken(String jwt);


    public User findUserByEmail(String email);

}
