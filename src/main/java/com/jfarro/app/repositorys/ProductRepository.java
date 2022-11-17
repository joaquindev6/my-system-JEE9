package com.jfarro.app.repositorys;

import com.jfarro.app.models.Product;

import java.sql.SQLException;

public interface ProductRepository extends CrudRepository<Product> {
    void UpdateAmountProduct(Long idProduct, int amount) throws SQLException;
}
