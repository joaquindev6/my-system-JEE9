package com.jfarro.app.controllers.views;

import com.jfarro.app.models.User;
import com.jfarro.app.services.UserService;
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
            u.setNames(formatData(u.getNames().split(" ")));
            u.setLastNames(formatData(u.getLastNames().split(" ")));
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
            req.getSession().removeAttribute("errorDelete");
        }
        getServletContext().getRequestDispatcher("/users.jsp").forward(req, resp);
    }

    private String formatData(String[] datas) {
        String data = "";
        for (String d: datas) {
            data += " " + (d.substring(0, 1).toUpperCase() + d.substring(1).toLowerCase());
        }
        return data.trim();
    }
}