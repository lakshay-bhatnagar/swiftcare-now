package com.example.swiftcare.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.swiftcare.managers.CartManager;
import com.example.swiftcare.MainActivity;
import com.example.swiftcare.R;
import com.example.swiftcare.databinding.FragmentHomeBinding;
import com.example.swiftcare.models.CartItem;
import com.google.android.material.button.MaterialButton;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;
    private CardView getItem1, getItem2;
    private ImageButton cart;

    private MaterialButton buybtn1, buybtn2, buybtn3, buybtn4;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private MedicineAdapter adapter;
    private List<String> medicineList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = fragmentHomeBinding.getRoot();

        getItem1 = fragmentHomeBinding.item1Home;
        getItem2 = fragmentHomeBinding.item2Home;
        cart = fragmentHomeBinding.cart;
        searchView = fragmentHomeBinding.searchView;
        recyclerView = fragmentHomeBinding.recyclerView;

        buybtn1 = fragmentHomeBinding.buyBtn1;
        buybtn2 = fragmentHomeBinding.buyBtn2;
        buybtn3 = fragmentHomeBinding.buyBtn3;
        buybtn4 = fragmentHomeBinding.buyBtn4;

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample Medicine List
        medicineList = Arrays.asList(
                "Paracetamol", "Aspirin", "Ibuprofen", "Amoxicillin", "Metformin",
                "Omeprazole", "Losartan", "Atorvastatin", "Levothyroxine", "Gabapentin"
        );

        // Set up RecyclerView with Adapter
        adapter = new MedicineAdapter(medicineList);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.GONE);
        // Medicine navigation
        getItem1.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate(R.id.action_navigation_home_to_medicine1Fragment)
        );

        getItem2.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate(R.id.action_navigation_home_to_medicine2Fragment)
        );

        // Cart navigation
        cart.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate(R.id.action_navigation_home_to_cartFragment)
        );

        // adding to cart

        buybtn1.setOnClickListener(v -> {
            CartManager.getInstance().addItem(
                    new CartItem("Saridon", 46.50, 1, "Pharmacy A", R.drawable.saridon)
            );
        });

        buybtn2.setOnClickListener(v -> {
            CartManager.getInstance().addItem(
                    new CartItem("Dolo 650", 146.50, 1, "Pharmacy A", R.drawable.dolo650)
            );
        });

        buybtn3.setOnClickListener(v -> {
            CartManager.getInstance().addItem(
                    new CartItem("Paracetamol", 26.50, 1, "Pharmacy B", R.drawable.medicine_placeholder)
            );
        });

        buybtn4.setOnClickListener(v -> {
            CartManager.getInstance().addItem(
                    new CartItem("Painkiller", 246.00, 1, "Pharmacy B", R.drawable.medicine_placeholder)
            );
        });


        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.GONE);
            }
        });

        // Implement Search Logic
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                if (adapter.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });


        // Ensure Bottom Navigation Visibility
        ((MainActivity) requireActivity()).setBottomNavVisibility(true);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentHomeBinding = null;
    }
}
