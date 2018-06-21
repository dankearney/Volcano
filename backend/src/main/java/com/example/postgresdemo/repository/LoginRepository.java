package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

}
