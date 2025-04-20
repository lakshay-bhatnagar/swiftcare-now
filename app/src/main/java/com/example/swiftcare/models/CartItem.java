package com.example.swiftcare.models;

public class CartItem {
    private String name;
    private double price;
    private int quantity;
    private String pharmacy; // new field
    private int imageResId;

    public CartItem(String name, double price, int quantity, String pharmacy, int imageResId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.pharmacy = pharmacy;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }
}
