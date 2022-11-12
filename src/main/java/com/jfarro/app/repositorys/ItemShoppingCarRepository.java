package com.jfarro.app.repositorys;

import com.jfarro.app.models.ItemShoppingCar;

import java.sql.SQLException;

public interface ItemShoppingCarRepository extends CrudRepository<ItemShoppingCar> {
    void updateAmount(int amount, Long id) throws SQLException;
    Long findByIdProduct(Long idProduct) throws SQLException;
}
