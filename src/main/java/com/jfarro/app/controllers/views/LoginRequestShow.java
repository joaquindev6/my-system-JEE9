package com.jfarro.app.controllers.views;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sesion")
public class LoginRequestShow extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("valiLogin") != null) {
            req.setAttribute("valiLogin", "Usuario o contraseña incorrecta, vuelve a intentarlo.");
            req.getSession().removeAttribute("valiLogin");
        }
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
