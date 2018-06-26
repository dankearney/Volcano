package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.UserRepository;
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
import java.util.*;
import java.nio.charset.Charset;
import org.springframework.http.*;
import org.springframework.security.access.method.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bc = new BCryptPasswordEncoder(11);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public List<User> User() {
        List<User> Users = userRepository.findAll();
        return Users;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User register(@RequestBody User user) {
        user.setPassword( bc.encode( user.getPassword() ) );
        userRepository.save(user);
        return user;       
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test!";       
    }

}
