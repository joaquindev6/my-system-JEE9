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

@WebServlet("/categoria-producto/data-show")
public class ProductCategoryServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/categoria-producto");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterValues("delete");
        if (ids != null && ids.length > 0) {
            Arrays.asList(ids).forEach(i -> {
                this.productService.deleteProductCategory(Long.parseLong(i));
            });
        } else {
            req.getSession().setAttribute("errorMessage", 0);
        }
        doGet(req, resp);
    }
}
