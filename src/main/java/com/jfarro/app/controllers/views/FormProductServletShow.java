package com.jfarro.app.controllers.views;

import com.jfarro.app.models.Product;
import com.jfarro.app.models.ProductCategory;
import com.jfarro.app.services.ProductService;
import com.jfarro.app.util.DataFormat;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/productos/formulario")
public class FormProductServletShow extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductCategory> categories = this.productService.findAllCategory();
        categories.forEach(c -> {
            c.setName(DataFormat.formatTextMayusMinus(c.getName().split(" ")));
        });
        req.setAttribute("categories", categories);
        long id = 0;
        if (req.getSession().getAttribute("idProduct") != null) {
            id = (Long) req.getSession().getAttribute("idProduct");
            req.setAttribute("id", id);
            req.getSession().removeAttribute("idProduct");
        }
        if (id > 0) {
            Product product = this.productService.findByIdProduct(id);
            req.setAttribute("product", product);
        } else {
            if (req.getSession().getAttribute("formErrors") != null) {
                Map<String, Object> messageErrors = (Map<String, Object>) req.getSession().getAttribute("formErrors");
                req.setAttribute("product", messageErrors.get("product"));
                req.setAttribute("productErrors", messageErrors.get("productErrors"));
                req.getSession().removeAttribute("formErrors");
            }
        }
        getServletContext().getRequestDispatcher("/form-product.jsp").forward(req, resp);
    }
}
