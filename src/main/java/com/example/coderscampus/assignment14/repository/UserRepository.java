package com.example.coderscampus.assignment14.repository;

import com.example.coderscampus.assignment14.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
