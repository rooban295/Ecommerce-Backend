package com.ecom.E.commerce_backend.adminController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin") //This page can access only admin
public class AdminProductController {

    @GetMapping
    public String testAdminController(){

        return "Admin Controller";
    }
}
