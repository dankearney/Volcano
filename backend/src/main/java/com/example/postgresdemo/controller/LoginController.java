package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Login;
import com.example.postgresdemo.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.io.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Random;

@RestController
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/login")
    @ResponseBody
    public List<Login> login() {
        List<Login> logins = loginRepository.findAll();
        return logins;
    }

    @GetMapping("/register")
    public List<Login> setLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);
        login.setUserid(getRandomUserId());
        loginRepository.save(login);
        List<Login> logins = loginRepository.findAll();
        return logins;        
    }

    private Long getRandomUserId() {
         Random rand = new Random();    
         return Long.valueOf(rand.nextInt(1000000));   
    }

}
