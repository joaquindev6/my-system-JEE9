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
        long id = 0;
        if (req.getSession().getAttribute("idCategory") != null) {
            id = (Long) req.getSession().getAttribute("idCategory");
            req.setAttribute("id", id);
            req.getSession().removeAttribute("idCategory");
        }
        if (id > 0) {
            ProductCategory category = this.productService.findByIdCategory(id);
            req.setAttribute("category", category);
        } else {
            if (req.getSession().getAttribute("formErrors") != null) {
                Map<String, String> messageErrors = (Map<String, String>) req.getSession().getAttribute("formErrors");
                req.setAttribute("category", messageErrors.get("category"));
                req.setAttribute("categoryErrors", messageErrors.get("categoryErrors"));
                req.getSession().removeAttribute("formErrors");
            }
        }
        getServletContext().getRequestDispatcher("/form-product-category.jsp").forward(req, resp);
    }
}
