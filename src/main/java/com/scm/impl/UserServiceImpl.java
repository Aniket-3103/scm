package com.scm.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;
import com.scm.helpers.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId=UUID.randomUUID().toString();

        user.setUserId(userId);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserbyId(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User existingUser=userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        //updating the user
        existingUser.setName(user.getName());
        existingUser.setAbout(user.getAbout());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmailVerified(user.isEmailVerified());
        existingUser.setProfilePic(user.getProfilePic());
        existingUser.setEnabled(user.isEnabled());
        existingUser.setPhoneVerified(user.isPhoneVerified());
        existingUser.setProvider(user.getProvider());
        existingUser.setProviderUserId(user.getProviderUserId());

        //saved the updated user to db
        User savedUser = userRepo.save(existingUser);
        return Optional.ofNullable(savedUser);
    }

    @Override
    public void deleteUser(String id) {
        User existingUser=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepo.delete(existingUser);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user=userRepo.findById(userId).orElse(null);

        return user!=null? true: false;

    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user=userRepo.findByEmail(email).orElse(null);
        return user!=null? true: false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    
}
