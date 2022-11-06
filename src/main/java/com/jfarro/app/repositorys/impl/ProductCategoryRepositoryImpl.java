package com.jfarro.app.repositorys.impl;

import com.jfarro.app.annotations.ConnectionMySQL;
import com.jfarro.app.annotations.Repository;
import com.jfarro.app.models.ProductCategory;
import com.jfarro.app.repositorys.ProductCategoryRepository;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {

    @Inject
    @ConnectionMySQL
    private Connection conn;

    @Override
    public List<ProductCategory> findAll() throws SQLException {
        List<ProductCategory> productCategories = new ArrayList<>();
        try (Statement stm = this.conn.createStatement()) {
            try (ResultSet rs = stm.executeQuery("SELECT * FROM product_category")) {
                while (rs.next()) {
                    productCategories.add(getCategory(rs));
                }
            }
        }
        return productCategories;
    }

    @Override
    public ProductCategory findById(Long id) throws SQLException {
        ProductCategory category = null;
        try (PreparedStatement pstm = this.conn.prepareStatement("SELECT * FROM product_category WHERE id = ?")) {
            pstm.setLong(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    category = getCategory(rs);
                }
            }
        }
        return category;
    }

    @Override
    public void save(ProductCategory productCategory) throws SQLException {
        String sql;
        if (productCategory.getId() != null && productCategory.getId() > 0) {
            sql = "UPDATE product_category SET name = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO product_category(name) VALUES(?)";
        }
        try (PreparedStatement pstm = this.conn.prepareStatement(sql)) {
            pstm.setString(1, productCategory.getName());
            if (productCategory.getId() != null && productCategory.getId() > 0) {
                pstm.setLong(2, productCategory.getId());
            }
            pstm.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement pstm = this.conn.prepareStatement("DELETE FROM product_category WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        }
    }

    private ProductCategory getCategory(ResultSet rs) throws SQLException {
        ProductCategory category = new ProductCategory();
        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));
        return category;
    }
}
