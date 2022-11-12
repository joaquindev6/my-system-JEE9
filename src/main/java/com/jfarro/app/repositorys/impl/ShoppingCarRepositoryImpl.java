package com.jfarro.app.repositorys.impl;

import com.jfarro.app.annotations.ConnectionMySQL;
import com.jfarro.app.annotations.Repository;
import com.jfarro.app.models.ItemShoppingCar;
import com.jfarro.app.models.ShoppingCar;
import com.jfarro.app.models.User;
import com.jfarro.app.repositorys.ShoppingCarRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShoppingCarRepositoryImpl implements ShoppingCarRepository {

    @Inject
    @ConnectionMySQL
    private Connection conn;

    @Override
    public List<ShoppingCar> findAll() throws SQLException {
        List<ShoppingCar> list = new ArrayList<>();
        try (Statement stm = this.conn.createStatement()) {
            try (ResultSet rs = stm.executeQuery("SELECT s.*, u.names, u.last_names, u.username, u.rol FROM shopping_car AS s " +
                    "INNER JOIN users AS u ON s.id_user = u.id " +
                    "INNER JOIN item_shopping_car AS i ON s.id_item_car = i.id;")) {
                while (rs.next()) {
                    list.add(getShoppingCar(rs));
                }
            }
        }
        return list;
    }

    @Override
    public ShoppingCar findById(Long id) throws SQLException {
        ShoppingCar car = null;
        try (PreparedStatement pstm = this.conn.prepareStatement("SELECT s.*, u.names, u.last_names, u.username, u.rol FROM shopping_car AS s " +
                "INNER JOIN users AS u ON s.id_user = u.id " +
                "INNER JOIN item_shopping_car AS i ON s.id_item_car = i.id WHERE id = ?")) {
            pstm.setLong(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    car = getShoppingCar(rs);
                }
            }
        }
        return car;
    }

    @Override
    public List<ShoppingCar> findAllByUser(Long idUser) throws SQLException {
        List<ShoppingCar> list = new ArrayList<>();
        try (PreparedStatement pstm = this.conn.prepareStatement("SELECT s.*, u.names, u.last_names, u.username, u.rol FROM shopping_car AS s " +
                "INNER JOIN users AS u ON s.id_user = u.id " +
                "INNER JOIN item_shopping_car AS i ON s.id_item_car = i.id WHERE id_user = ?")) {
            pstm.setLong(1, idUser);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    list.add(getShoppingCar(rs));
                }
            }
        }
        return list;
    }

    @Override
    public void save(ShoppingCar shoppingCar) throws SQLException {
        String sql;
        if (shoppingCar.getId() != null && shoppingCar.getId() > 0) {
            sql = "UPDATE shopping_car SET id_user = ?, id_item_car = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO shopping_car (id_user, id_item_car) VALUES(?,?)";
        }
        try (PreparedStatement pstm = this.conn.prepareStatement(sql)) {
            pstm.setLong(1, shoppingCar.getUser().getId());
            pstm.setLong(2, shoppingCar.getItemCar().getId());
            if (shoppingCar.getId() != null && shoppingCar.getId() > 0) {
                pstm.setLong(3, shoppingCar.getId());
            }
            pstm.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement pstm = this.conn.prepareStatement("DELETE FROM shopping_car WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        }
    }

    private ShoppingCar getShoppingCar(ResultSet rs) throws SQLException {
        ShoppingCar car = new ShoppingCar();
        car.setId(rs.getLong("id"));

        User user = new User();
        user.setId(rs.getLong("id_user"));
        car.setUser(user);

        ItemShoppingCar itemCar = new ItemShoppingCar(null);
        itemCar.setId(rs.getLong("id_item_car"));
        car.setItemCar(itemCar);

        return car;
    }
}
