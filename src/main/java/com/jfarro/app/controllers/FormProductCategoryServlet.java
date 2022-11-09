package com.jfarro.app.controllers;

import com.jfarro.app.models.ProductCategory;
import com.jfarro.app.services.ProductService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/categoria-producto/formulario/save")
public class FormProductCategoryServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
            req.getSession().setAttribute("idCategory", id);
        } catch (NumberFormatException ex) {
            id = 0;
        }
        resp.sendRedirect(req.getContextPath() + "/categoria-producto/formulario");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        Map<String, String> errors = new HashMap<>();
        if (name == null || name.isBlank()) {
            errors.put("nameError", "El campo nombre de la categoría no puede estar vacío.");
        }

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            id = 0;
        }

        ProductCategory category = new ProductCategory();
        category.setId(id);
        category.setName(name.trim().toUpperCase());

        if (errors.isEmpty()) {
            this.productService.saveProductCategory(category);
            resp.sendRedirect(req.getContextPath() + "/categoria-producto");
        } else {
            Map<String, Object> errorRedirect = new HashMap<>();
            errorRedirect.put("category", category);
            errorRedirect.put("categoryErrors", errors);
            req.getSession().setAttribute("formErrors", errorRedirect);
            resp.sendRedirect(req.getContextPath() + "/categoria-producto/formulario");
        }
    }
}
