package com.ecom.E.commerce_backend.service;

import com.ecom.E.commerce_backend.configuration.JwtProvider;
import com.ecom.E.commerce_backend.model.User;
import com.ecom.E.commerce_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepo;

    @Override
    public User getUserByJwtToken(String jwt) {
        String email=jwtProvider.getEmailJwtToken(jwt);
        User user=findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
