package com.jfarro.app.services.impl;

import com.jfarro.app.annotations.ServiceMysql;
import com.jfarro.app.exceptions.ServiceJdbcException;
import com.jfarro.app.models.User;
import com.jfarro.app.repositorys.UserRepository;
import com.jfarro.app.services.UserService;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ServiceMysql
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository repository;

    @Override
    public List<User> findAllUsers() {
        try {
            return this.repository.findAll();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<User> findAllWhereUsers(String filter, String data) {
        return null;
    }

    @Override
    public Optional<User> findByIdUser(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void saveUser(User product) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
