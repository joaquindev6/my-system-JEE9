package com.jfarro.app.controllers.views;

import com.jfarro.app.models.User;
import com.jfarro.app.services.UserService;
import com.jfarro.app.util.DataFormat;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            if ("m".equalsIgnoreCase(u.getSex())) {
                u.setSex("Masculino");
            }
            if ("f".equalsIgnoreCase(u.getSex())) {
                u.setSex("Femenino");
            }
            if ("ROLE_ADMIN".equalsIgnoreCase(u.getRole())) {
                u.setRole("Administrador");
            }
            if ("ROLE_USER".equalsIgnoreCase(u.getRole())) {
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