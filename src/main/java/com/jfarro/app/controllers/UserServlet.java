package com.jfarro.app.controllers;

import com.jfarro.app.services.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/usuarios/data-show")
public class UserServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/usuarios"); //clase UserServletShow
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] idsTable = req.getParameterValues("delete");
        if (idsTable != null && idsTable.length > 0) {
            Arrays.asList(idsTable).forEach(u -> {
                this.userService.deleteUser(Long.parseLong(u));
            });
        } else {
            req.getSession().setAttribute("sessionError", 0);
        }
        resp.sendRedirect(req.getContextPath() + "/usuarios");
    }
}
