package com.pahanasystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.pahanasystem.dao.impl.ItemDaoImpl;
import com.pahanasystem.model.Item;

public class ItemDaoTest {
	
	@Test
	void testAddNewItem() {
	    ItemDao itemDao = new ItemDaoImpl();


	    String itemTitle = "Pastel";
	    Item item = new Item(0, itemTitle, new BigDecimal("50.00"), 10);


	    itemDao.save(item);


	    Optional<Item> fetched = itemDao.findAll().stream()
	            .filter(i -> i.getTitle().equals(itemTitle))
	            .findFirst();

	  
	    assertTrue(fetched.isPresent(), "Item should be inserted successfully");
	}
	
	@Test
	void testUpdateItem() {
	    ItemDao itemDao = new ItemDaoImpl();

	    
	    String itemTitle = "Pastel";
	    Item item = new Item(0, itemTitle, new BigDecimal("50.00"), 10);
	    itemDao.save(item);

	  
	    Item savedItem = itemDao.findAll().stream()
	            .filter(i -> i.getTitle().equals(itemTitle))
	            .findFirst()
	            .orElseThrow(() -> new RuntimeException("Item not saved"));

	    
	    savedItem.setUnitPrice(new BigDecimal("75.00"));
	    savedItem.setStockQty(20);
	    itemDao.update(savedItem);

	    
	    Item updatedItem = itemDao.findById(savedItem.getId()).orElseThrow();

	   
	    assertTrue(updatedItem.getUnitPrice().equals(new BigDecimal("75.00")), "Unit price should be updated");
	    assertTrue(updatedItem.getStockQty() == 20, "Stock quantity should be updated");
	}
	
	
	@Test
	void testDeleteItem() {
	    ItemDao itemDao = new ItemDaoImpl();

	    
	    String itemTitle = "Pastel";
	    Item item = new Item(0, itemTitle, new BigDecimal("30.00"), 15);
	    itemDao.save(item);

	    
	    Item savedItem = itemDao.findAll().stream()
	            .filter(i -> i.getTitle().equals(itemTitle))
	            .findFirst()
	            .orElseThrow(() -> new RuntimeException("Item not saved"));

	    
	    itemDao.delete(savedItem.getId());

	    
	    Optional<Item> deletedItem = itemDao.findById(savedItem.getId());

	   
	    assertTrue(deletedItem.isEmpty(), "Item should be deleted successfully");
	}




}
