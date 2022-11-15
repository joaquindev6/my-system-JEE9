package com.jfarro.app.repositorys;

import com.jfarro.app.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends CrudRepository<User> {
    User findByUsername(String username) throws SQLException;
    List<User> findAllWhere(String filter, String data) throws SQLException;
}
