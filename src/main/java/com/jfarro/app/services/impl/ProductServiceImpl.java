package com.jfarro.app.services.impl;

import com.jfarro.app.annotations.ServiceMysql;
import com.jfarro.app.exceptions.ServiceJdbcException;
import com.jfarro.app.models.Product;
import com.jfarro.app.models.ProductCategory;
import com.jfarro.app.repositorys.ProductCategoryRepository;
import com.jfarro.app.repositorys.ProductRepository;
import com.jfarro.app.services.ProductService;
import javax.inject.Inject;

import java.sql.SQLException;
import java.util.List;

@ServiceMysql
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductCategoryRepository categoryRepository;

    @Override
    public List<Product> findAllProduct() {
        try {
            return this.productRepository.findAll();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Product findByIdProduct(Long id) {
        try {
            return this.productRepository.findById(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void saveProduct(Product product) {
        try {
            this.productRepository.save(product);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            this.productRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<ProductCategory> findAllCategory() {
        try {
            return this.categoryRepository.findAll();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public ProductCategory findByIdCategory(Long id) {
        try {
            return this.categoryRepository.findById(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void saveProductCategory(ProductCategory category) {
        try {
            this.categoryRepository.save(category);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void deleteProductCategory(Long id) {
        try {
            this.categoryRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
