package com.jfarro.app.services.impl;

import com.jfarro.app.annotations.ServiceMysql;
import com.jfarro.app.exceptions.ServiceJdbcException;
import com.jfarro.app.models.ItemShoppingCar;
import com.jfarro.app.models.ShoppingCar;
import com.jfarro.app.repositorys.ItemShoppingCarRepository;
import com.jfarro.app.repositorys.ShoppingCarRepository;
import com.jfarro.app.services.ShoppingService;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@ServiceMysql
public class ShoppingServiceImpl implements ShoppingService {

    @Inject
    private ShoppingCarRepository shoppingCarRepository;

    @Inject
    private ItemShoppingCarRepository itemShoppingCarRepository;

    @Override
    public List<ShoppingCar> findAllShoppingCar() {
        try {
            return this.shoppingCarRepository.findAll();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public ShoppingCar findByIdShoppingCar(Long id) {
        try {
            return this.shoppingCarRepository.findById(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<ShoppingCar> findAllByUserShoppingCar(Long idUser) {
        try {
            return this.shoppingCarRepository.findAllByUser(idUser);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void saveShoppingCar(ShoppingCar shoppingCar) {
        try {
            this.shoppingCarRepository.save(shoppingCar);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void deleteShoppingCar(Long id) {
        try {
            this.shoppingCarRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<ItemShoppingCar> findAllItemShoppingCar() {
        try {
            return this.itemShoppingCarRepository.findAll();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public ItemShoppingCar findByIdItemShoppingCar(Long id) {
        try {
            return this.itemShoppingCarRepository.findById(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void saveItemShoppingCar(ItemShoppingCar itemShoppingCar) {
        try {
            this.itemShoppingCarRepository.save(itemShoppingCar);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void deleteItemShoppingCar(Long id) {
        try {
            this.itemShoppingCarRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void updateAmountItemShoppingCar(int amount, Long idItem) {
        try {
            this.itemShoppingCarRepository.updateAmount(amount, idItem);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Long findByIdProductItemShoppingCar(Long idProduct) {
        try {
            return this.itemShoppingCarRepository.findByIdProduct(idProduct);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
