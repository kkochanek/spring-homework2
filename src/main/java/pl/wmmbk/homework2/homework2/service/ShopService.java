package pl.wmmbk.homework2.homework2.service;

import pl.wmmbk.homework2.homework2.model.Item;

import java.math.BigDecimal;

public interface ShopService {

    void findAll();
    long countItems();
    void addItem(Item item);
    BigDecimal sumUpPrices();
}
