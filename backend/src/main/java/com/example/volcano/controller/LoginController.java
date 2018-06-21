package com.example.volcano.controller;

import com.example.volcano.login.Login;
import com.example.volcano.service.ILoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    
    @Autowired
    ILoginService loginService;

    @RequestMapping("/showLogins")
    @ResponseBody
    public String findLogins(Model model) {
        
        System.out.println(loginService.findAll());
        
        // model.addAttribute("login", logins);
        
        return "showLogins";
    }
}