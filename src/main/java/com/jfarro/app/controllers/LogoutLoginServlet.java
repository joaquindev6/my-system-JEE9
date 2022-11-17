package com.jfarro.app.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sesion/logout")
public class LogoutLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idUser = (Long) req.getSession().getAttribute("idUser");
        if (idUser != null) {
            req.getSession().removeAttribute("idUser");
            req.getSession().removeAttribute("user");
            resp.sendRedirect(req.getContextPath() + "/inicio");
        } else {
            resp.sendRedirect(req.getContextPath() + "/sesion");
        }
    }
}
