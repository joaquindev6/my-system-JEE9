package com.jfarro.app.controllers.views;

import com.jfarro.app.models.ProductCategory;
import com.jfarro.app.services.ProductService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        if (req.getSession().getAttribute("errorMessage") != null) {
            req.setAttribute("errorDelete", "Debe seleccionar la categoría que desea eliminar.");
            req.getSession().removeAttribute("errorMessage");
        }
        getServletContext().getRequestDispatcher("/product-category.jsp").forward(req, resp);
    }
}
