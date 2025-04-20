package com.example.swiftcare.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiftcare.R;
import com.example.swiftcare.models.CartItem;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItems;
    private OnCartChangeListener listener;

    public interface OnCartChangeListener {
        void onCartChanged();
    }

    public CartAdapter(List<CartItem> cartItems, OnCartChangeListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.medicineName.setText(item.getName());
        holder.medicinePrice.setText("Rs. " + item.getPrice());
        holder.medicinePharmacy.setText("Pharmacy: " + item.getPharmacy());
        holder.medicineImage.setImageResource(item.getImageResId());
        holder.quantity.setText(String.valueOf(item.getQuantity()));

        holder.increaseQty.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
            listener.onCartChanged();
        });

        holder.decreaseQty.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(position);
                listener.onCartChanged();
            }
        });

        holder.removeItem.setOnClickListener(v -> {
            if (position >= 0 && position < cartItems.size()) {
                cartItems.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartItems.size()); // <- fix layout inconsistency
                listener.onCartChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView medicineName, medicinePrice, quantity, medicinePharmacy;
        ImageView medicineImage;
        MaterialButton increaseQty, decreaseQty;
        ImageButton removeItem;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            medicineName = itemView.findViewById(R.id.itemName);
            medicinePrice = itemView.findViewById(R.id.itemPrice);
            medicinePharmacy = itemView.findViewById(R.id.itemPharmacy);
            quantity = itemView.findViewById(R.id.itemQuantity);
            medicineImage = itemView.findViewById(R.id.itemImage);
            increaseQty = itemView.findViewById(R.id.incrementButton);
            decreaseQty = itemView.findViewById(R.id.decrementButton);
            removeItem = itemView.findViewById(R.id.removeButton);
        }
    }
}

