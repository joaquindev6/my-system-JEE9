package com.jfarro.app.filters;

import com.jfarro.app.models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/productos"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("user");
        if (user != null && user.getId() > 0) {
            if (user.getRole().equals("ROLE_ADMIN") || user.getRole().equals("ROLE_USER")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/inicio");
        }
    }
}
