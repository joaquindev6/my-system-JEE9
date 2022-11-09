package com.jfarro.app.controllers.views;

import com.jfarro.app.models.User;
import com.jfarro.app.services.UserService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            req.setAttribute("id", id);
            req.getSession().removeAttribute("idUser");
        }
        if (id > 0) {
            User user = this.userService.findByIdUser(id);
            req.setAttribute("user", user);
        } else {
            if (req.getSession().getAttribute("errorsForm") != null) {
                Map<String, Object> messageErrors = (Map<String, Object>) req.getSession().getAttribute("errorsForm");
                req.setAttribute("user", messageErrors.get("user"));
                req.setAttribute("userErrors", messageErrors.get("userErrors"));
                req.getSession().removeAttribute("errorsForm"); //Eliminando toda los datos de session
            }
        }
        getServletContext().getRequestDispatcher("/form-user.jsp").forward(req, resp);
    }
}
