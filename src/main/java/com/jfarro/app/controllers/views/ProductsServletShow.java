package com.jfarro.app.controllers.views;

import com.jfarro.app.models.Product;
import com.jfarro.app.services.ProductService;
import com.jfarro.app.util.DataFormat;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/productos")
public class ProductsServletShow extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = this.productService.findAllProduct();
        products.forEach(p -> {
            p.getCategory().setName(p.getCategory().getName().toUpperCase());
        });
        req.setAttribute("products", products);
        if (req.getSession().getAttribute("sessionError") != null) {
            req.setAttribute("errorDelete", "Debe seleccionar el producto que desea eliminar.");
            req.getSession().removeAttribute("sessionError");
        }
        getServletContext().getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}
