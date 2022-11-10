package com.jfarro.app.services;

import com.jfarro.app.models.ItemShoppingCar;
import com.jfarro.app.models.ShoppingCar;

import java.util.List;

public interface ShoppingService {
    List<ShoppingCar> findAllShoppingCar();
    ShoppingCar findByIdShoppingCar(Long id);
    List<ShoppingCar> findAllByUserShoppingCar(Long idUser);
    void saveShoppingCar(ShoppingCar shoppingCar);
    void deleteShoppingCar(Long id);

    List<ItemShoppingCar> findAllItemShoppingCar();
    ItemShoppingCar findByIdItemShoppingCar(Long id);
    void saveItemShoppingCar(ItemShoppingCar itemShoppingCar);
    void deleteItemShoppingCar(Long id);
    void updateAmountItemShoppingCar(int amount, Long id);
}
