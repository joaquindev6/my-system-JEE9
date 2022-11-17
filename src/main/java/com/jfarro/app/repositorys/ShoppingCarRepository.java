package com.jfarro.app.repositorys;

import com.jfarro.app.models.ShoppingCar;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingCarRepository extends CrudRepository<ShoppingCar> {
    List<ShoppingCar> findAllByUser(Long idUser) throws SQLException;
    ShoppingCar findByIdUser(Long idUser, Long idProduct) throws SQLException;
}
