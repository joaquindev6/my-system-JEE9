package com.jfarro.app.controllers;

import com.jfarro.app.models.ItemShoppingCar;
import com.jfarro.app.models.Product;
import com.jfarro.app.models.ShoppingCar;
import com.jfarro.app.models.User;
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
            if (items.contains(item)) { //Compara por el id del producto
                long idItem = this.shoppingCarService.findByIdProductItemShoppingCar(idProduct);
                ItemShoppingCar itemCar = this.shoppingCarService.findByIdItemShoppingCar(idItem);
                System.out.println("*************** cantidad " + itemCar.getAmount());
                this.shoppingCarService.updateAmountItemShoppingCar(itemCar.getAmount() + 1, idItem);
            } else {
                this.shoppingCarService.saveItemShoppingCar(item);
                ShoppingCar shoppingCar = new ShoppingCar();
                User user = new User();
                user.setId(idUser);
                shoppingCar.setUser(user);
                item.setId(this.shoppingCarService.findByIdProductItemShoppingCar(idProduct));
                shoppingCar.setItemCar(item);
                this.shoppingCarService.saveShoppingCar(shoppingCar);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/carro-compra");
    }
}
