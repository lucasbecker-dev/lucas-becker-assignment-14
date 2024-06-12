package com.coderscampus.assignment14.service;

import com.coderscampus.assignment14.domain.User;
import com.coderscampus.assignment14.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User save(User user) {
        try {
            return userRepo.save(user);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: user cannot be null\n" + e);
            return null;
        } catch (OptimisticLockingFailureException e) {
            System.err.println(e);
            return null;
        }
    }

    public List<User> findByName(String name) {
        return userRepo.findByName(name);
    }
}
