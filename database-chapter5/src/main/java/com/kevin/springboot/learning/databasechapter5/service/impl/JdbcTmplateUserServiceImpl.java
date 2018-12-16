package com.kevin.springboot.learning.databasechapter5.service.impl;

import com.kevin.springboot.learning.databasechapter5.enumeration.Gender;
import com.kevin.springboot.learning.databasechapter5.pojo.User;
import com.kevin.springboot.learning.databasechapter5.service.JdbcTmplateUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Service(value = "jdbcTmplateUserService")
public class JdbcTmplateUserServiceImpl implements JdbcTmplateUserService {
    private static Logger logger = LoggerFactory.getLogger(JdbcTmplateUserServiceImpl.class);
    @Resource
    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> getUserRowMapper(){
        return (ResultSet rs, int column) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setNote(rs.getString("note"));
            user.setGender(Gender.getGenderById(rs.getInt("gender")));
            return user;
        };
    }
    @Override
    public User getUser(long id) {
        String sql = "SELECT * FROM t_user WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.queryForObject(sql, args, getUserRowMapper());
    }

    @Override
    public User getUser2(long id) {
        // use statementCallback by lambda
        return this.jdbcTemplate.execute((Statement stmt) -> {
            String sql1 = "SELECT count(*) FROM t_user WHERE id = " + id;
            ResultSet resultSet1 = stmt.executeQuery(sql1);
            while (resultSet1.next()) {
                int total = resultSet1.getInt("total");
                logger.info("stmt.total = " + total);
            }

            // exec sql
            String sql2 = "";
            ResultSet resultSet2 = stmt.executeQuery(sql2);
            User user = null;
            while (resultSet2.next()) {
                int rowNum = resultSet2.getRow();
                user = getUserRowMapper().mapRow(resultSet2, rowNum);
            }
            return user;
        });
    }

    @Override
    public User getUser3(long id) {
        // use ConnectionCallback by lambda
        return this.jdbcTemplate.execute((Connection conn) -> {
            String sql1 = "SELECT count(*) FROM t_user WHERE id = " + id;
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setLong(1, id);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()){
                logger.info("conn.total: " + resultSet1.getInt("total"));
            }
            String sql2 = "SELECT id, name, gender, note FROM t_user WHERE id = ?";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setLong(1, id);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            User user = null;
            while (resultSet2.next()){
                int rowNum = resultSet2.getRow();
                user = getUserRowMapper().mapRow(resultSet2, rowNum);
            }
            return user;
        });
    }

    @Override
    public List<User> findUsers(String name, String note) {
        Object[] args = new Object[]{name, note};
        String sql = "SELECT * FROM t_user WHERE name LIKE concat('%', ?, '%')" +
                "AND note LIKE concat('%', ?, '%')";
        return jdbcTemplate.query(sql, args, getUserRowMapper());
    }

    @Override
    public int insertUser(User user) {
        String sql = "INSERT INTO t_user(name, gender, note) VALUES (?, ?, ?)";
        Object[] args = new Object[]{user.getName(), user.getGender().getId(), user.getNote()};
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int updateUser(User user) {
        String sql = "UPDATE t_user SET name = ?, gender = ?, note = ? WHERE id = ?";
        Object[] args = new Object[]{user.getName(), user.getGender().getId(), user.getNote(), user.getId()};
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int deleteUser(long id) {
        String sql = "DELETE FROM t_user WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
