package com.jfarro.app.repositorys;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll() throws SQLException;
    T findById(Long id) throws SQLException;
    Long save(T t) throws SQLException;
    void delete(Long id) throws SQLException;
}
