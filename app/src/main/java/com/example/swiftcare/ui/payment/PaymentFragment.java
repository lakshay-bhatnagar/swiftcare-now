package com.example.swiftcare.ui.payment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swiftcare.MainActivity;
import com.example.swiftcare.R;
import com.example.swiftcare.databinding.FragmentPaymentBinding;
import com.google.android.material.button.MaterialButton;


public class PaymentFragment extends Fragment {


    MaterialButton buyNow;
    FragmentPaymentBinding fragmentPaymentBinding;
    TextView totalAmount, totalAmountBottom, itemTotal, deliveryFee;
    Boolean extraFee;
    Double totalItemAmount, totalAmountCal;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPaymentBinding = FragmentPaymentBinding.inflate(inflater, container, false);

        // payment to order successful

        itemTotal = fragmentPaymentBinding.itemTotal;
        totalAmount = fragmentPaymentBinding.totalAmount;
        totalAmountBottom = fragmentPaymentBinding.totalAmountBottom;
        buyNow = fragmentPaymentBinding.buyNow;
        deliveryFee = fragmentPaymentBinding.deliveryFee;

        if (getArguments() != null){
            totalItemAmount = getArguments().getDouble("itemTotal");
            totalAmountCal = getArguments().getDouble("total");
            extraFee = getArguments().getBoolean("extraFee");
        }

        itemTotal.setText("Rs. "+totalItemAmount.toString());
        totalAmount.setText("Rs. "+totalAmountCal.toString());
        if (extraFee){
            deliveryFee.setText("Rs. 10.50 + Rs. 20 (Super-Fast Delivery)");
        }
        totalAmountBottom.setText("Rs. "+totalAmountCal.toString());

        buyNow.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_paymentFragment_to_orderProgressFragment);
        });

//        requireActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);

        ((MainActivity) requireActivity()).setBottomNavVisibility(false);
        return fragmentPaymentBinding.getRoot();
    }

//    public void onDestroyView() {
//        super.onDestroyView();
//        requireActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
//    }
}