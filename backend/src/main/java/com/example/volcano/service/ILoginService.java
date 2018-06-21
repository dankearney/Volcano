package com.example.volcano.service;

import com.example.volcano.login.Login;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ILoginService {

    public List<Login> findAll();
}