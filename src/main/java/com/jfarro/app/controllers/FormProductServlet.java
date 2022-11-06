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

@WebServlet("/productos/formulario/save")
public class FormProductServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
            req.getSession().setAttribute("idProduct", id);
        } catch (NumberFormatException ex) {
            id = 0;
        }
        resp.sendRedirect(req.getContextPath() + "/productos/formulario");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        long idCategory;
        try {
            idCategory = Long.parseLong(req.getParameter("category"));
        } catch (NumberFormatException ex) {
            idCategory = 0;
        }
        int amount;
        try {
            amount = Integer.parseInt(req.getParameter("amount"));
        } catch (NumberFormatException ex) {
            amount = 0;
        }
        double price;
        try {
            price = Double.parseDouble(req.getParameter("price"));
        } catch (NumberFormatException ex) {
            price = 0;
        }

        Map<String, String> errors = new HashMap<>();
        if (name == null || name.isBlank()) {
            errors.put("nameError", "El campo nombre de producto no puede estar vacío");
        }
        if (idCategory == 0) {
            errors.put("categoryError", "Seleccione la categoría para el producto");
        }
        if (amount <= 0) {
            errors.put("amountError", "Ingrese la cantidad de stock del producto");
        }
        if (price <= 0) {
            errors.put("priceError", "Ingrese el precio del producto con IGV");
        }

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            id = 0;
        }

        Product product = new Product();
        product.setId(id);
        product.setName(name.trim().toUpperCase());
        product.setAmount(amount);
        product.setPrice(price);
        ProductCategory category = new ProductCategory();
        category.setId(idCategory);
        product.setCategory(category);

        if (errors.isEmpty()) {
            this.productService.saveProduct(product);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            Map<String, Object> errorRedirect = new HashMap<>();
            errorRedirect.put("product", product);
            errorRedirect.put("productErrors", errors);
            req.getSession().setAttribute("formErrors", errorRedirect);
            resp.sendRedirect(req.getContextPath() + "/productos/formulario");
        }
    }
}
