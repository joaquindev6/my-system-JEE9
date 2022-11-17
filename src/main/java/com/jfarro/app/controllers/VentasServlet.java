package com.jfarro.app.controllers;

import com.jfarro.app.models.*;
import com.jfarro.app.services.ProductService;
import com.jfarro.app.services.ShoppingService;
import com.jfarro.app.services.VentasService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/productos/ventas")
public class VentasServlet extends HttpServlet {

    @Inject
    private VentasService ventasService;

    @Inject
    private ShoppingService shoppingCarService;

    @Inject
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idUser;
        try {
            idUser = (Long) req.getSession().getAttribute("idUser");
        } catch (Exception e) {
            idUser = 0;
        }
        if (idUser > 0) {
            List<ShoppingCar> car = this.shoppingCarService.findAllByUserShoppingCar(idUser);
            List<ItemShoppingCar> items = new ArrayList<>();
            if (car != null) {
                car.forEach(c -> {
                    items.add(this.shoppingCarService.findByIdItemShoppingCar(c.getItemCar().getId()));
                });
            }
            if (!items.isEmpty()) {
                // Guarda los datos en la cabecera de la venta
                ItemShoppingCar item = new ItemShoppingCar(req);
                VentasCab ventasCab = new VentasCab();
                ventasCab.setPriceTotal(item.calSubTotal());
                ventasCab.setDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                User user = new User();
                user.setId(idUser);
                ventasCab.setUser(user);

                // Recupera el id de la nueva venta cabecera
                Long idVentasCab = this.ventasService.saveVentasCab(ventasCab);
                if (idVentasCab != null && idVentasCab > 0) {
                    VentasDet ventasDet = new VentasDet();
                    ventasCab.setId(idVentasCab);

                    // Recorre los items y obtiene sus valaores para guardarlo en el detalle de la venta
                    items.forEach(i -> {
                        ventasDet.setVentasCab(ventasCab);
                        ventasDet.setProduct(i.getProduct());
                        ventasDet.setPrice(i.getTotal());
                        ventasDet.setAmount(i.getAmount());
                        this.ventasService.saveVentasDet(ventasDet);
                    });

                    // Recorre los items, edita la cantidad de los productos y elimina los items del carrito en cascada
                    items.forEach(i -> {
                        int amountProduct = i.getProduct().getAmount() - i.getAmount();
                        this.productService.updateAmountProduct(i.getProduct().getId(), amountProduct);
                        this.shoppingCarService.deleteItemShoppingCar(i.getId());
                    });
                } else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al realizar la venta, vuelva a intentarlo más tarde.");
                }

                req.getSession().setAttribute("ventaFinish", "Compra realizada con éxito, dentro de 2 días sus productos llegaran a sus manos. " +
                        "Puede hacer seguimiento de sus productos comprados.");
            } else {
                req.getSession().setAttribute("sinDatosItems", "No hay productos en el carrito.");
                resp.sendRedirect(req.getContextPath() + "/carro-compra");
                return;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/productos");
    }
}
