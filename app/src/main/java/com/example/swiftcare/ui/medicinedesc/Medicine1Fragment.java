package com.example.swiftcare.ui.medicinedesc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swiftcare.MainActivity;
import com.example.swiftcare.R;
import com.example.swiftcare.databinding.FragmentMedicine1Binding;
import com.example.swiftcare.managers.CartManager;
import com.example.swiftcare.models.CartItem;
import com.google.android.material.button.MaterialButton;


public class Medicine1Fragment extends Fragment {

    FragmentMedicine1Binding fragmentMedicine1Binding;

    ImageView medicineImage;
    TextView name, price;
    MaterialButton addBtn;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       fragmentMedicine1Binding = FragmentMedicine1Binding.inflate(inflater, container, false);

       View root = fragmentMedicine1Binding.getRoot();
       name = fragmentMedicine1Binding.medicine1Name;
       price = fragmentMedicine1Binding.medicine1Price;
       medicineImage = fragmentMedicine1Binding.medicine1Image;
       addBtn = fragmentMedicine1Binding.addToCartSaridon;

       addBtn.setOnClickListener(v -> {
           CartManager.getInstance().addItem(new CartItem("Saridon", 46.50, 1, "Pharmacy A", R.drawable.saridon));
           Navigation.findNavController(v).navigate(R.id.action_medicine1Fragment_to_cartFragment);
       });

        ((MainActivity) requireActivity()).setBottomNavVisibility(false);

       return root;
    }

//    public void onDestroyView() {
//        super.onDestroyView();
//        requireActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
//    }
}