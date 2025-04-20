package com.example.swiftcare.managers;

import com.example.swiftcare.models.CartItem;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItem> cartItems = new ArrayList<>();
    private double deliveryFee = 0;

    private CartManager() {}

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItem(CartItem item) {
        cartItems.add(item);
    }

    public List<CartItem> getItems() {
        return cartItems;
    }

    // For compatibility with old method name
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void removeItem(int position) {
        if (position >= 0 && position < cartItems.size()) {
            cartItems.remove(position);
        }
    }

    public void clearCart() {
        cartItems.clear();
        deliveryFee = 0;
    }

    public void setDeliveryFee(double fee) {
        this.deliveryFee = fee;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    // Calculates the total price of items without delivery
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    // Optional: calculate final payable total (items + delivery)
    public double getFinalTotal() {
        return getTotalPrice() + deliveryFee;
    }
}
