package com.pahanasystem.dao;

import com.pahanasystem.model.Item;
import java.util.List;
import java.util.Optional;

public interface ItemDao {
    void save(Item item);
    void update(Item item);
    void delete(int id);

    Optional<Item> findById(int id);
    List<Item> findAll();
}
