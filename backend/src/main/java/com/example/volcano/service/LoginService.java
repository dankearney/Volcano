package com.example.volcano.service;

import com.example.volcano.login.Login;
import com.example.volcano.repository.LoginRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    private LoginRepository repository;

    @Override
    public List<Login> findAll() {

        List<Login> logins = (List<Login>) repository.findAll();
        
        return logins;
    }
}