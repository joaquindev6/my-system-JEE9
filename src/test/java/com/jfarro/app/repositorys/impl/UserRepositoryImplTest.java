package com.jfarro.app.repositorys.impl;

import com.jfarro.app.annotations.ConnectionMySQL;
import com.jfarro.app.models.User;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

import java.sql.*;

class UserRepositoryImplTest {

    @Inject
    @ConnectionMySQL
    private Connection conn;

    @Test
    void findAll() throws SQLException {
//        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicio1?useTimezone=true?serverTimezone=UTC", "root", "farro123");
        System.out.println("Conexion mysql: " + conn.isClosed());
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setNames(rs.getString("names"));
        user.setLastNames(rs.getString("last_names"));
        user.setAge(rs.getInt("age"));
        user.setCountry(rs.getString("country"));
        user.setSex(rs.getString("sex"));
        user.setRole(rs.getString("rol"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}