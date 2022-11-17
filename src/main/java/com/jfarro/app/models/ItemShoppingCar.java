package com.jfarro.app.models;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class ItemShoppingCar {

    private Long id;
    private Product product;
    private int amount;
    private HttpServletRequest req;
    private List<ItemShoppingCar> items;

    public ItemShoppingCar(HttpServletRequest req) {
        this.req = req;
        if (req != null) {
            this.items = (List<ItemShoppingCar>) req.getSession().getAttribute("listItems");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        if (this.product.getPrice() != null && this.amount > 0)  {
            return this.product.getPrice() * this.amount;
        }
        return 0.00;
    }

    public double calSubTotal() {
        if (this.items != null) {
            return this.items.stream().mapToDouble(p -> p.getTotal()).sum();
        }
        return 0.00;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemShoppingCar that = (ItemShoppingCar) o;
        return Objects.equals(this.getId(), that.getId());
    }
}
