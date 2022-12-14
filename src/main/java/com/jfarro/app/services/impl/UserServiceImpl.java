package com.jfarro.app.services.impl;

import com.jfarro.app.annotations.ServiceMysql;
import com.jfarro.app.exceptions.ServiceJdbcException;
import com.jfarro.app.models.User;
import com.jfarro.app.repositorys.UserRepository;
import com.jfarro.app.services.UserService;
import javax.inject.Inject;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ServiceMysql
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        try {
            return this.userRepository.findAll();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<User> findAllWhereUsers(String filter, String data) {
        try {
            return this.userRepository.findAllWhere(filter, data);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public User findByIdUser(Long id) {
        try {
            return this.userRepository.findById(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<User> findByUsername(String username, String password) {
        try {
            return Optional.ofNullable(this.userRepository.findByUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Long saveUser(User product) {
        try {
            return this.userRepository.save(product);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            this.userRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
