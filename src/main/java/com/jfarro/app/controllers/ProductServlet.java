package com.jfarro.app.controllers;

import com.jfarro.app.services.ProductService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/productos/data-show")
public class ProductServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/productos");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] idProducts = req.getParameterValues("delete");
        if (idProducts != null && idProducts.length > 0) {
            Arrays.asList(idProducts).forEach(p -> this.productService.deleteProduct(Long.parseLong(p)));
        } else {
            req.getSession().setAttribute("sessionError", 0);
        }
        doGet(req, resp);
    }
}
