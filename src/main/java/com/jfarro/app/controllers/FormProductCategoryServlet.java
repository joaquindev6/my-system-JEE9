package com.jfarro.app.controllers;

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

@WebServlet("/categoria-producto/formulario/save")
public class FormProductCategoryServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/categoria-producto/formulario");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        Map<String, String> errors = new HashMap<>();
        if (name == null || name.isBlank()) {
            errors.put("nameError", "El campo nombre de la categoría no puede estar vacío");
        }

        ProductCategory category = new ProductCategory();
        category.setName(name.trim());

        if (errors.isEmpty()) {
            this.productService.saveProductCategory(category);
            resp.sendRedirect(req.getContextPath() + "/categoria-producto");
        } else {
            Map<String, Object> errorRedirect = new HashMap<>();
            errorRedirect.put("product", category);
            errorRedirect.put("categoryErrors", errors);
            req.getSession().setAttribute("formErrors", errorRedirect);
            resp.sendRedirect(req.getContextPath() + "/categoria-producto/formulario");
        }
    }
}