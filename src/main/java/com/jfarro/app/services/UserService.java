package com.jfarro.app.services;

import com.jfarro.app.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    List<User> findAllWhereUsers(String filter, String data);
    Optional<User> findByIdUser(Long id);
    Optional<User> findByUsername(String username);
    void saveUser(User product);
    void deleteUser(Long id);
}
