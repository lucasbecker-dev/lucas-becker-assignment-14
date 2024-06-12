package com.coderscampus.assignment14.repository;

import com.coderscampus.assignment14.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByName(String name);
}
