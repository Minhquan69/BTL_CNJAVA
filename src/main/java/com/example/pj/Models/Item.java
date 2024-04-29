package com.example.pj.Models;

public class Item {
    private String itemName;

    private String itemImage;

    private int itemPrice;

    private String itemStar;

    private int itemNumber;

    private String codeItem;

    public Item() {
    }

    public Item(String itemImage, String itemName, int itemPrice, String itemStar, String codeItem) {
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.itemPrice = itemPrice;
        this.itemStar = itemStar;
        this.itemNumber = 1;
        this.codeItem = codeItem;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemStar() {
        return itemStar;
    }

    public void setItemStar(String itemStar) {
        this.itemStar = itemStar;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getCodeItem() {
        return codeItem;
    }

    public void setCodeItem(String codeItem) {
        this.codeItem = codeItem;
    }
}
