package com.kevin.springboot.learning.databasechapter5.dao;

import com.kevin.springboot.learning.databasechapter5.pojo.User;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    @Query(value = " from user  where name like concat('%', ?1, '%') and note like concat('%', ?2, '%') ")
    List<User> findByNameLikeAndNoteLike(String name, String note);
}
