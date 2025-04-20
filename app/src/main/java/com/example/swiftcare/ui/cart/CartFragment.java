package com.example.swiftcare.ui.cart;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiftcare.MainActivity;
import com.example.swiftcare.R;
import com.example.swiftcare.adapters.CartAdapter;
import com.example.swiftcare.managers.CartManager;
import com.example.swiftcare.models.CartItem;
import com.google.android.material.button.MaterialButton;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import android.widget.Button;
import android.widget.TextView;

public class CartFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    private TextView itemTotalText, totalAmountText;
    private MaterialButton buyNow;
    public CartFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);
        itemTotalText = view.findViewById(R.id.itemTotal);
        totalAmountText = view.findViewById(R.id.totalAmount);

        List<CartItem> items = CartManager.getInstance().getItems();
        TextView emptyCartText = view.findViewById(R.id.emptyCartText);

        // Setup adapter with listener
        CartAdapter adapter = new CartAdapter(items, () -> {
            double itemTotalUpdated = CartManager.getInstance().getTotalPrice();
            double totalUpdated = itemTotalUpdated + 10.50;

            itemTotalText.setText("Item Total: Rs. " + String.format("%.2f", itemTotalUpdated));
            totalAmountText.setText("Total: Rs. " + String.format("%.2f", totalUpdated));

            if (CartManager.getInstance().getCartItems().isEmpty()) {
                emptyCartText.setVisibility(View.VISIBLE);
                cartRecyclerView.setVisibility(View.GONE);
            } else {
                emptyCartText.setVisibility(View.GONE);
                cartRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        if (items.isEmpty()) {
            emptyCartText.setVisibility(View.VISIBLE);
            cartRecyclerView.setVisibility(View.GONE);
        } else {
            emptyCartText.setVisibility(View.GONE);
            cartRecyclerView.setVisibility(View.VISIBLE);
        }
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartRecyclerView.setAdapter(adapter);

        updateTotalPrices();

//        double itemTotal = CartManager.getInstance().getTotalPrice();
//        double deliveryFee = 10.50;
//        double total = itemTotal + deliveryFee;

//        itemTotalText.setText("Item Total: Rs. " + String.format("%.2f", itemTotal));
//        totalAmountText.setText("Total: Rs. " + String.format("%.2f", total));

        buyNow = view.findViewById(R.id.buy_now);

        // cart to payment
        buyNow.setOnClickListener(v -> {
            List<CartItem> currentCart = CartManager.getInstance().getItems();
            double itemTotal = CartManager.getInstance().getTotalPrice();

            // Get distinct pharmacies
            Set<String> pharmacies = new HashSet<>();
            for (CartItem item : currentCart) {
                pharmacies.add(item.getPharmacy());
            }

            if (pharmacies.size() > 1) {
                AlertDialog dialog = new AlertDialog.Builder(getContext()).create();

// Set Title and Message
                dialog.setTitle("Multiple Pharmacies Detected");
                dialog.setMessage("Your cart has items from different pharmacies. Choose delivery option:");

// Set Buttons
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Quick Delivery (₹20 extra)", (dialogInterface, i) -> {
                    double total = itemTotal + 10.50 + 20.00;
                    navigateToPayment(v, itemTotal, total, true);
                });

                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Normal Delivery (Free)", (dialogInterface, i) -> {
                    double total = itemTotal + 10.50;
                    navigateToPayment(v, itemTotal, total, false);
                });

                // Show dialog first so we can customize it
                dialog.show();

// Customize background and title/message color
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.white);

                TextView titleView = dialog.findViewById(getContext().getResources()
                        .getIdentifier("alertTitle", "id", "android"));
                TextView messageView = dialog.findViewById(android.R.id.message);
                if (titleView != null) titleView.setTextColor(Color.BLACK);
                if (messageView != null) messageView.setTextColor(Color.BLACK);

// Customize buttons
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                if (positiveButton != null) {
                    positiveButton.setBackgroundColor(Color.WHITE);
                    positiveButton.setTextColor(Color.parseColor("#ffd9d9"));
                }
                if (negativeButton != null) {
                    negativeButton.setBackgroundColor(Color.WHITE);
                    negativeButton.setTextColor(Color.parseColor("#ffd9d9"));
                }

            } else {
                double total = itemTotal + 10.50;
                navigateToPayment(v, itemTotal, total, false);
            }
        });


//        requireActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);

        ((MainActivity) requireActivity()).setBottomNavVisibility(false);

        return view;
    }

    private void updateTotalPrices() {
        double itemTotal = CartManager.getInstance().getTotalPrice();
        double deliveryFee = CartManager.getInstance().getDeliveryFee(); // use your current fee logic
        double total = itemTotal + deliveryFee;

        itemTotalText.setText("Item Total: Rs. " + String.format("%.2f", itemTotal));
        totalAmountText.setText("Total: Rs. " + String.format("%.2f", total));
    }

    private void navigateToPayment(View view, double itemTotal, double total, boolean extraFee) {
        Bundle bundle = new Bundle();
        bundle.putDouble("itemTotal", itemTotal);
        bundle.putDouble("total", total);
        bundle.putBoolean("extraFee", extraFee);
        Navigation.findNavController(view).navigate(R.id.action_cartFragment_to_paymentFragment, bundle);
    }


}
