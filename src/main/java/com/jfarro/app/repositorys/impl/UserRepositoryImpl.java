package com.jfarro.app.repositorys.impl;

import com.jfarro.app.annotations.ConnectionMySQL;
import com.jfarro.app.annotations.Repository;
import com.jfarro.app.models.User;
import com.jfarro.app.repositorys.UserRepository;
import javax.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Inject
    @ConnectionMySQL
    private Connection conn;

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement stm = this.conn.createStatement()) {
            try (ResultSet rs = stm.executeQuery("SELECT * FROM users")) {
                while (rs.next()) {
                    users.add(getUser(rs));
                }
            }
        }
        return users;
    }

    @Override
    public User findById(Long id) throws SQLException {
        User user = null;
        try (PreparedStatement pstm = this.conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            pstm.setLong(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    user = getUser(rs);
                }
            }
        }
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) throws SQLException {
        User user = null;
        try (PreparedStatement pstm = this.conn.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            pstm.setString(1, username);
            try (ResultSet rs = pstm.executeQuery()) {
                user = getUser(rs);
            }
        }
        return Optional.of(user);
    }

    @Override
    public List<User> findAllWhere(String filter, String data) throws SQLException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement pstm = this.conn.prepareStatement("SELECT * FROM users WHERE " + filter + " LIKE CONCAT(LOWER(?), '%')")) {
            pstm.setString(1, data);
            try (ResultSet rs = pstm.executeQuery()) {
                users.add(getUser(rs));
            }
        }
        return users;
    }

    @Override
    public void save(User user) throws SQLException {
        String query;
        if (user.getId() != null && user.getId() > 0L) {
            query = "UPDATE users SET names = ?, last_names = ?, age = ?, country = ?, sex = ?, rol = ?, username = ?, password = ? WHERE id = ?";
        } else {
            query = "INSERT INTO users(names, last_names, age, country, sex, rol, username, password) VALUES(?,?,?,?,?,?,?,?)";
        }
        try (PreparedStatement pstm = this.conn.prepareStatement(query)) {
            pstm.setString(1, user.getNames());
            pstm.setString(2, user.getLastNames());
            pstm.setInt(3, user.getAge());
            pstm.setString(4, user.getCountry());
            pstm.setString(5, user.getSex());
            pstm.setString(6, user.getRole());
            pstm.setString(7, user.getUsername());
            pstm.setString(8, user.getPassword());

            if (user.getId() != null && user.getId() > 0L) {
                pstm.setLong(9, user.getId());
            }

            pstm.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement pstm = this.conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        }
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
