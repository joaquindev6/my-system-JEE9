package com.jfarro.app.services;

import com.jfarro.app.models.Product;
import com.jfarro.app.models.ProductCategory;

import java.util.List;

public interface ProductService {
    List<Product> findAllProduct();
    Product findByIdProduct(Long id);
    Long saveProduct(Product product);
    void deleteProduct(Long id);
    void updateAmountProduct(Long idProduct, int amount);

    List<ProductCategory> findAllCategory();
    ProductCategory findByIdCategory(Long id);
    Long saveProductCategory(ProductCategory category);
    void deleteProductCategory(Long id);
}
