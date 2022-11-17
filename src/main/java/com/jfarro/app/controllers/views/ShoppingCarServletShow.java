package com.jfarro.app.controllers.views;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/carro-compra")
public class ShoppingCarServletShow extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Muestra un mensaje por realizar la compra sin productos
        if (req.getSession().getAttribute("sinDatosItems") != null) {
            req.setAttribute("sinDatosItems", req.getSession().getAttribute("sinDatosItems"));
            req.getSession().removeAttribute("sinDatosItems");
        }
        getServletContext().getRequestDispatcher("/shoppingcar.jsp").forward(req, resp);
    }
}
