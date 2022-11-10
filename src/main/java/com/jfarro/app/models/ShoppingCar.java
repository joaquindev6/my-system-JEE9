package com.jfarro.app.models;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class ShoppingCar implements Serializable {

    private Long id;
    private User user;
    private ItemShoppingCar itemCar;
    private List<ItemShoppingCar> carItems;

    public List<ItemShoppingCar> getCarItems() {
        return carItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ItemShoppingCar getItemCar() {
        return itemCar;
    }

    public void setItemCar(ItemShoppingCar itemCar) {
        this.itemCar = itemCar;
    }
}
