package com.jfarro.app.controllers;

import com.jfarro.app.models.User;
import com.jfarro.app.services.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet({"/usuarios/formulario/save"})
public class FormUserServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }
        if (id > 0) {
            req.getSession().setAttribute("idUser", id);
        }
        resp.sendRedirect(req.getContextPath() + "/usuarios/formulario");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String names = req.getParameter("names");
        String lastNames = req.getParameter("lastNames");
        int age;
        try {
            age = Integer.parseInt(req.getParameter("age"));
        } catch (NumberFormatException e) {
            age = 0;
        }
        String sex = req.getParameter("sex");
        String country = req.getParameter("country");
        String role = req.getParameter("role");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Map<String, String> errors = new HashMap<>();
        if (names == null || names.isBlank()) {
            errors.put("namesError", "El campo nombres no puede estar vacío");
        }
        if (lastNames == null || lastNames.isBlank()) {
            errors.put("lastNamesError", "El campo apellidos no puede estar vacío");
        }
        if (age <= 0) {
            errors.put("ageError", "Debe ingresar una edad correcta");
        }
        if (sex == null || sex.isBlank()) {
            errors.put("sexError", "Seleccione el genero correspondiente");
        }
        if (country == null || country.isBlank()) {
            errors.put("countryError", "Seleccione el país de origen");
        }
        if (role == null || role.isBlank()) {
            errors.put("roleError", "Seleccione el rol para el usuario");
        }
        if (username == null || username.isBlank()) {
            errors.put("usernameError", "El campo nombre de usuario no puede estar vacío");
        }
        if (password == null || password.isBlank()) {
            errors.put("passwordError", "El campo contraseña no puede estar vacío");
        }

        long id;
        try {
            id = (Long) req.getSession().getAttribute("idUser");
        } catch (Exception e) {
            id = 0;
        }

        User user = new User();
        user.setId(id);
        user.setNames(names.trim());
        user.setLastNames(lastNames.trim());
        user.setAge(age);
        user.setSex(sex);
        user.setCountry(country);
        user.setRole(role);
        user.setUsername(username.trim());
        user.setPassword(password.trim());

        if (errors.isEmpty()) {
            this.userService.saveUser(user);
            if (id > 0) {
                req.getSession().removeAttribute("idUser");
            }
            resp.sendRedirect(req.getContextPath() + "/usuarios/data-show");
        } else {
            Map<String, Object> errorsRedirect = new HashMap<>();
            errorsRedirect.put("user", user);
            errorsRedirect.put("userErrors", errors);
            req.getSession().setAttribute("errorsForm", errorsRedirect);
            resp.sendRedirect(req.getContextPath() + "/usuarios/formulario");
        }
    }
}
