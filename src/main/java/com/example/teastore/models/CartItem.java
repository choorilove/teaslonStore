package com.example.teastore.models;

public class CartItem {
    private String itemId,image,title,sort;
    private int quantity;
    private float price;

    public CartItem(){}

    public CartItem(String itemId, int quantity, float basePrice,String image,String title,String sort) {
        this.sort = sort;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = calculatePrice(basePrice, quantity);
        this.image=image;
        this.title=title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private float calculatePrice(float basePrice, int quantity) {
        return (basePrice / 50) * quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
