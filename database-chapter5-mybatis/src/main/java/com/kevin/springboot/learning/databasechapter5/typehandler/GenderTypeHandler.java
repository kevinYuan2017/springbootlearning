package com.kevin.springboot.learning.databasechapter5.typehandler;

import com.kevin.springboot.learning.databasechapter5.enumeration.Gender;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(value = JdbcType.TINYINT)
@MappedTypes(value = Gender.class)
public class GenderTypeHandler extends BaseTypeHandler<Gender> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Gender gender, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, gender.getId());
    }

    @Override
    public Gender getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return Gender.getGenderById(resultSet.getInt(s));
    }

    @Override
    public Gender getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return Gender.getGenderById(resultSet.getInt(i));
    }

    @Override
    public Gender getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return Gender.getGenderById(callableStatement.getInt(i));
    }
}
