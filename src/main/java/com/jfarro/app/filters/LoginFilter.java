package com.jfarro.app.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/productos/*", "/usuarios/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Long idUser = (Long) req.getSession().getAttribute("idUser");
        if (idUser != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.sendRedirect(req.getContextPath() + "/inicio");
        }
    }
}
