package com.jfarro.app.services;

import com.jfarro.app.models.Product;
import com.jfarro.app.models.ProductCategory;

import java.util.List;

public interface ProductService {
    List<Product> findAllProduct();
    Product findByIdProduct(Long id);
    void saveProduct(Product product);
    void deleteProduct(Long id);

    List<ProductCategory> findAllCategory();
    ProductCategory findByIdCategory(Long id);
    void saveProductCategory(ProductCategory category);
    void deleteProductCategory(Long id);
}
