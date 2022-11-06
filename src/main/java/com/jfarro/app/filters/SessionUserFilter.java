package com.jfarro.app.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter({"/usuarios", "/usuarios/show-data", "/", "/inicio", "/productos/*"})
public class SessionUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        req.getSession().removeAttribute("idUser"); //Eliminando toda los datos de session
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
