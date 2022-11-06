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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/categoria-producto/formulario")
public class FormProductCategoryServletView extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("form-product-category.jsp").forward(req, resp);
    }
}
