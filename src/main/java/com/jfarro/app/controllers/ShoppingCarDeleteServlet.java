package com.jfarro.app.controllers;

import com.jfarro.app.services.ShoppingService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/carro-compra/delete")
public class ShoppingCarDeleteServlet extends HttpServlet {

    @Inject
    private ShoppingService shoppingCarService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //========== Eliminando item del carrito ==========
        long idItemDelete;
        try {
            idItemDelete = Long.parseLong(req.getParameter("idItemDelete"));
        } catch (Exception e) {
            idItemDelete = 0;
        }
        if (idItemDelete > 0) {
            this.shoppingCarService.deleteItemShoppingCar(idItemDelete);
        }
        resp.sendRedirect(req.getContextPath() + "/carro-compra");
    }
}
