package com.pahanasystem.model;

import java.math.BigDecimal;

public class Item {
	private int id;                  
    private String title;
    private BigDecimal unitPrice;
    private int stockQty;

   

    public Item() { }

    public Item(int id, String title,
                BigDecimal unitPrice, int stockQty) {
        this.id        = id;
        this.title     = title;
        this.unitPrice = unitPrice;
        this.stockQty  = stockQty;
    }

  

    public int getId()                         { return id; }
    public void setId(int id)                  { this.id = id; }

    public String getTitle()                   { return title; }
    public void setTitle(String title)         { this.title = title; }

    public BigDecimal getUnitPrice()           { return unitPrice; }
    public void setUnitPrice(BigDecimal price) { this.unitPrice = price; }

    public int getStockQty()                   { return stockQty; }
    public void setStockQty(int qty)           { this.stockQty = qty; }

}
