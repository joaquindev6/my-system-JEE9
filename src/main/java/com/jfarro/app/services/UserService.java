package com.jfarro.app.services;

import com.jfarro.app.models.Product;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<Product> findAllUsers();
    List<Product> findAllWhereUsers(String filter, String data);
    Optional<Product> findByIdUser(Long id);
    Optional<Product> findByUsername(String username);
    void saveUser(Product product);
    void deleteUser(Long id);
}
