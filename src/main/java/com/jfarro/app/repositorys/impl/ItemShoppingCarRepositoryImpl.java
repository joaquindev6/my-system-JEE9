package com.jfarro.app.repositorys.impl;

import com.jfarro.app.annotations.ConnectionMySQL;
import com.jfarro.app.annotations.Repository;
import com.jfarro.app.models.ItemShoppingCar;
import com.jfarro.app.models.Product;
import com.jfarro.app.models.ProductCategory;
import com.jfarro.app.repositorys.ItemShoppingCarRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemShoppingCarRepositoryImpl implements ItemShoppingCarRepository {

    @Inject
    @ConnectionMySQL
    private Connection conn;

    @Override
    public List<ItemShoppingCar> findAll() throws SQLException {
        List<ItemShoppingCar> items = new ArrayList<>();
        try (Statement stm = this.conn.createStatement()) {
            try (ResultSet rs = stm.executeQuery("SELECT i.*, p.name, p.price, p.amount, p.id_category, c.name FROM item_shopping_car AS i " +
                    "INNER JOIN products AS p ON i.id_product = p.id " +
                    "INNER JOIN product_category AS c ON p.id_category = c.id")) {
                items.add(getItemShoppingCar(rs));
            }
        }
        return items;
    }

    @Override
    public ItemShoppingCar findById(Long id) throws SQLException {
        ItemShoppingCar itemShoppingCar = new ItemShoppingCar(null);
        try (PreparedStatement pstm = this.conn.prepareStatement("SELECT i.*, p.name, p.price, p.amount, p.id_category, c.name FROM item_shopping_car AS i " +
                "INNER JOIN products AS p ON i.id_product = p.id " +
                "INNER JOIN product_category AS c ON p.id_category = c.id WHERE i.id = ?")) {
            pstm.setLong(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    itemShoppingCar = getItemShoppingCar(rs);
                }
            }
        }
        return itemShoppingCar;
    }

    @Override
    public Long save(ItemShoppingCar itemShoppingCar) throws SQLException {
        long idItem = 0L;
        String sql;
        if (itemShoppingCar.getId() != null && itemShoppingCar.getId() > 0) {
            sql = "UPDATE item_shopping_car SET id_product = ?, amount = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO item_shopping_car(id_product, amount) VALUES(?,?)";
        }
        try (PreparedStatement pstm = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setLong(1, itemShoppingCar.getProduct().getId());
            pstm.setInt(2, itemShoppingCar.getAmount());
            if (itemShoppingCar.getId() != null && itemShoppingCar.getId() > 0) {
                pstm.setLong(3, itemShoppingCar.getId());
            }
            pstm.executeUpdate();
            ResultSet result = pstm.getGeneratedKeys();
            if (result.next()) {
                idItem = result.getLong(1);
                System.out.println("******************** ID ITEM: " + idItem);
            }
        }
        return idItem;
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement pstm = this.conn.prepareStatement("DELETE FROM item_shopping_car WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        }
    }

    @Override
    public void updateAmount(int amount, Long idItem) throws SQLException {
        try (PreparedStatement pstm = this.conn.prepareStatement("UPDATE item_shopping_car SET amount = ? WHERE id = ?")) {
            pstm.setInt(1, amount);
            pstm.setLong(2, idItem);
            pstm.executeUpdate();
        }
    }

    @Override
    public Long findByIdProduct(Long idProduct) throws SQLException {
        long idItem = 0;
        try (PreparedStatement pstm = this.conn.prepareStatement("SELECT i.id FROM item_shopping_car AS i WHERE id_product = ?")) {
            pstm.setLong(1, idProduct);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    idItem = rs.getLong("i.id");
                }
            }
        }
        return idItem;
    }

    private ItemShoppingCar getItemShoppingCar(ResultSet rs) throws SQLException {
        ItemShoppingCar items = new ItemShoppingCar(null);
        items.setId(rs.getLong("id"));

        Product product = new Product();
        product.setId(rs.getLong("id_product"));
        product.setName(rs.getString("p.name"));
        product.setPrice(rs.getDouble("p.price"));
        product.setAmount(rs.getInt("p.amount"));
        ProductCategory category = new ProductCategory();
        category.setId(rs.getLong("p.id_category"));
        category.setName(rs.getString("c.name"));
        product.setCategory(category);

        items.setProduct(product);
        items.setAmount(rs.getInt("amount"));

        return items;
    }
}
