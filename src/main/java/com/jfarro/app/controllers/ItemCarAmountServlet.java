package com.jfarro.app.controllers;

import com.jfarro.app.models.ItemShoppingCar;
import com.jfarro.app.services.ShoppingService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/carro-compra/udpate-amount")
public class ItemCarAmountServlet extends HttpServlet {

    @Inject
    private ShoppingService shoppingCarService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idItem = Long.parseLong(req.getParameter("idItem"));
        int mas, menos;
        try {
            mas = Integer.parseInt(req.getParameter("mas"));
        } catch (Exception e) {
            mas = 0;
        }
        try {
            menos = Integer.parseInt(req.getParameter("menos"));
        } catch (Exception e) {
            menos = 0;
        }
        ItemShoppingCar item = this.shoppingCarService.findByIdItemShoppingCar(idItem);
        if (mas > 0) {
            int updateAmount = item.getAmount() + 1;
            this.shoppingCarService.updateAmountItemShoppingCar(updateAmount, idItem);
        }
        if (menos > 0) {
            int updateAmount = item.getAmount() - 1;
            this.shoppingCarService.updateAmountItemShoppingCar(updateAmount, idItem);
        }
        resp.sendRedirect(req.getContextPath() + "/carro-compra");
    }
}
