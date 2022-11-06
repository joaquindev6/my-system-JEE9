package com.jfarro.app.repositorys.impl;

import com.jfarro.app.annotations.ConnectionMySQL;
import com.jfarro.app.annotations.Repository;
import com.jfarro.app.models.Product;
import com.jfarro.app.models.ProductCategory;
import com.jfarro.app.repositorys.ProductRepository;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Inject
    @ConnectionMySQL
    private Connection conn;

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Statement stmt = this.conn.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT .*, c.name FROM products as p INNER JOIN product_category as c ON p.id_category = c.id")) {
                while (rs.next()) {
                    products.add(getProduct(rs));
                }
            }
        }
        return products;
    }

    @Override
    public Product findById(Long id) throws SQLException {
        Product product = null;
        try (PreparedStatement pstm = this.conn.prepareStatement("SELECT p.*, c.name FROM products as p INNER JOIN product_category as c " +
                "ON p.id_category = c.id WHERE id = ?")) {
            pstm.setLong(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    product = getProduct(rs);
                }
            }
        }
        return product;
    }

    @Override
    public void save(Product product) throws SQLException {
        String sql;
        if (product.getId() != null && product.getId() > 0) {
            sql = "UPDATE products SET name = ?, id_category = ?, price = ?, amount = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO products(name, id_category, price, amount) VALUES(?,?,?,?)";
        }
        try (PreparedStatement pstm = this.conn.prepareStatement(sql)) {
            pstm.setString(1, product.getName());
            pstm.setLong(2, product.getCategory().getId());
            pstm.setDouble(3, product.getPrice());
            pstm.setInt(4, product.getAmount());

            if (product.getId() != null && product.getId() > 0) {
                pstm.setLong(5, product.getId());
            }

            pstm.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement pstm = this.conn.prepareStatement("DELETE FROM products WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        }
    }

    private Product getProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        product.setAmount(rs.getInt("amount"));

        ProductCategory category = new ProductCategory();
        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));
        product.setCategory(category);

        return product;
    }
}
