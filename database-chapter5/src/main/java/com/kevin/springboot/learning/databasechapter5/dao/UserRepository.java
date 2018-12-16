package com.kevin.springboot.learning.databasechapter5.dao;

import com.kevin.springboot.learning.databasechapter5.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
}
