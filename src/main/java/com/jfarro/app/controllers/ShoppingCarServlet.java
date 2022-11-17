package com.jfarro.app.controllers;

import com.jfarro.app.models.*;
import com.jfarro.app.services.ProductService;
import com.jfarro.app.services.ShoppingService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/carro-compra/data-show")
public class ShoppingCarServlet extends HttpServlet {

    @Inject
    private ShoppingService shoppingCarService;

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idUser;
        try {
            idUser = (Long) req.getSession().getAttribute("idUser");
        } catch (Exception e) {
            idUser = 0;
        }
        //========== Agregando nuevo item al carrito ==========
        long idProduct;
        try {
            idProduct = Long.parseLong(req.getParameter("idProduct"));
        } catch (Exception e) {
            idProduct = 0;
        }
        if (idProduct > 0 && idUser > 0) {
            ItemShoppingCar item = new ItemShoppingCar(null);
            Product product = new Product();
            product.setId(idProduct);
            item.setProduct(product);
            item.setAmount(1);

            List<ItemShoppingCar> items = (List<ItemShoppingCar>) req.getSession().getAttribute("listItems");
            if (items == null) {
                items = new ArrayList<>();
            }
            ShoppingCar car;
            try {
                car = this.shoppingCarService.findByIdUserShoppingCar(idUser, idProduct);
            } catch (NullPointerException e) {
                car = null;
            }
            ItemShoppingCar itemCar;
            if (car == null) {
                itemCar = new ItemShoppingCar(null);
                itemCar.setId(0L);
            } else {
                itemCar = this.shoppingCarService.findByIdItemShoppingCar(car.getItemCar().getId());
            }

            // Validando si existe unidades disponibles del producto, utilizo otro servicio para recuperar exactamente el producto
            Product p = this.productService.findByIdProduct(idProduct);
            if (p.getAmount() > 0) {
                if (items.contains(itemCar)) { //Compara por el id del item
                    if (p.getAmount() > 1) {
                        this.shoppingCarService.updateAmountItemShoppingCar(itemCar.getAmount() + 1, itemCar.getId());
                    }
                } else {
                    Long idItem = this.shoppingCarService.saveItemShoppingCar(item);
                    if (idItem != null && idItem > 0) {
                        ShoppingCar shoppingCar = new ShoppingCar();
                        User user = new User();
                        user.setId(idUser);
                        shoppingCar.setUser(user);
                        item.setId(idItem);
                        shoppingCar.setItemCar(item);
                        this.shoppingCarService.saveShoppingCar(shoppingCar);
                    } else {
                        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al guardar el carrito, vuelva a intentarlo más tarde.");
                    }
                }
            } else {
                req.getSession().setAttribute("messageAmount", "No hay productos disponibles.");
                resp.sendRedirect(req.getContextPath() + "/productos");
                return;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/carro-compra");
    }
}
