package com.jfarro.app.repositorys;

import com.jfarro.app.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User> {
    Optional<User> findByUsername(String username) throws SQLException;
    List<User> findAllWhere(String filter, String data) throws SQLException;
}
