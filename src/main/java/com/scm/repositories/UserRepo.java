package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;
// repositories actually contains methods for interacting with db

@Repository
public interface UserRepo extends JpaRepository<User, String>{

    
    // custom methods which spring jpa will implement
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
} 


