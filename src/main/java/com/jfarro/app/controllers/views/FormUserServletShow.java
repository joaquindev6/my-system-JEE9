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
import java.util.Map;

@WebServlet("/usuarios/formulario")
public class FormUserServletShow extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = 0;
        if (req.getSession().getAttribute("idUser") != null) {
            id = (Long) req.getSession().getAttribute("idUser");
        }
        if (id > 0) {
            User user = this.userService.findByIdUser(id);
            req.setAttribute("user", user);
        } else {
            if (req.getSession().getAttribute("errorsForm") != null) {
                Map<String, Object> messages = (Map<String, Object>) req.getSession().getAttribute("errorsForm");
                req.setAttribute("user", messages.get("user"));
                req.setAttribute("userErrors", messages.get("userErrors"));
                req.getSession().removeAttribute("errorsForm"); //Eliminando toda los datos de session
            }
        }
        getServletContext().getRequestDispatcher("/form-user.jsp").forward(req, resp);
    }
}
