package com.pahanasystem.service;

import com.pahanasystem.model.Item;
import java.util.List;
import java.util.Optional;

public interface ItemService {
    void addItem(Item item);
    void updateItem(Item item);
    void deleteItem(int id);
    Optional<Item> findItemById(int id);
    List<Item> getAllItems();
}
