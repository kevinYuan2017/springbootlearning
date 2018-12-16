package com.kevin.springboot.learning.databasechapter5.dao;

import com.kevin.springboot.learning.databasechapter5.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    List<User> findByNameLikeAndNoteLike(String name, String note);
}
