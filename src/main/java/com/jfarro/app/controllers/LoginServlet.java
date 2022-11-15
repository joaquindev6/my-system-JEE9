package com.jfarro.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfarro.app.models.User;
import com.jfarro.app.services.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/sesion/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/sesion");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username != null && password != null) {
            Optional<User> user = this.userService.findByUsername(username, password);
            if (user.isPresent()) {
                req.getSession().setAttribute("idUser", user.get().getId());
                resp.sendRedirect(req.getContextPath() + "/inicio");
            } else {
                req.getSession().setAttribute("valiLogin", 0);
                doGet(req, resp);
            }
        }
    }
}
