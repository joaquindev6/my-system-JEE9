package com.jfarro.app.services;

import com.jfarro.app.models.ItemShoppingCar;
import com.jfarro.app.models.ShoppingCar;

import java.util.List;

public interface ShoppingService {
    List<ShoppingCar> findAllShoppingCar();
    ShoppingCar findByIdShoppingCar(Long id);
    List<ShoppingCar> findAllByUserShoppingCar(Long idUser);
    ShoppingCar findByIdUserShoppingCar(Long idUser, Long idProduct);
    Long saveShoppingCar(ShoppingCar shoppingCar);
    void deleteShoppingCar(Long id);

    List<ItemShoppingCar> findAllItemShoppingCar();
    ItemShoppingCar findByIdItemShoppingCar(Long id);
    Long saveItemShoppingCar(ItemShoppingCar itemShoppingCar);
    void deleteItemShoppingCar(Long id);
    void updateAmountItemShoppingCar(int amount, Long idItem);
    Long findByIdProductItemShoppingCar(Long idProduct);
}
