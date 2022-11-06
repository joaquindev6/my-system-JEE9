package com.jfarro.app.controllers.views;

import com.jfarro.app.models.User;
import com.jfarro.app.services.UserService;
import com.jfarro.app.util.DataFormat;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/usuarios")
public class UserServletShow extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = this.userService.findAllUsers();
        users.forEach(u -> {
            u.setNames(DataFormat.formatTextMayusMinus(u.getNames().split(" ")));
            u.setLastNames(DataFormat.formatTextMayusMinus(u.getLastNames().split(" ")));
            if (u.getSex().equalsIgnoreCase("m")) {
                u.setSex("Masculino");
            }
            if (u.getSex().equalsIgnoreCase("f")) {
                u.setSex("Femenino");
            }
            if (u.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                u.setRole("Administrador");
            }
            if (u.getRole().equalsIgnoreCase("ROLE_USER")) {
                u.setRole("Usuario");
            }
        });
        req.setAttribute("users", users);
        if (req.getSession().getAttribute("sessionError") != null) {
            req.setAttribute("errorDelete", "Debe seleccionar el usuario que desea eliminar.");
            req.getSession().removeAttribute("sessionError");
        }
        getServletContext().getRequestDispatcher("/users.jsp").forward(req, resp);
    }
}