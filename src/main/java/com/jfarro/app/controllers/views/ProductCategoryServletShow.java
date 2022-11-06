package com.jfarro.app.controllers.views;

import com.jfarro.app.models.Product;
import com.jfarro.app.models.ProductCategory;
import com.jfarro.app.services.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Category;

import java.io.IOException;
import java.util.List;

@WebServlet("/categoria-producto")
public class ProductCategoryServletShow extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductCategory> categories = this.productService.findAllCategory();
        req.setAttribute("categories", categories);
        getServletContext().getRequestDispatcher("/product-category.jsp").forward(req, resp);
    }
}
