package com.jfarro.app.filters;

import com.jfarro.app.models.ItemShoppingCar;
import com.jfarro.app.models.ShoppingCar;
import com.jfarro.app.services.ShoppingService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class ShoppingCarSessionFilter implements Filter {

    @Inject
    private ShoppingService shoppingCarService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //========== Mostrando datos al carrito ==========
        long idUser;
        try {
            idUser = (Long) req.getSession().getAttribute("idUser");
        } catch (Exception e) {
            idUser = 0;
        }
        if (idUser > 0) {
            List<ShoppingCar> listCar = this.shoppingCarService.findAllByUserShoppingCar(idUser);
            if (listCar.size() > 0) {
                List<ItemShoppingCar> items = new ArrayList<>();
                listCar.forEach(c -> {
                    items.add(this.shoppingCarService.findByIdItemShoppingCar(c.getItemCar().getId()));
                });
                req.getSession().setAttribute("listItems", items);
            } else {
                //Importante eliminar la lista de la session si no existe, si no se mostraran datos fantasmas
                req.getSession().removeAttribute("listItems");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
