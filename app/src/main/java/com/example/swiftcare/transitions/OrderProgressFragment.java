package com.example.swiftcare.transitions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swiftcare.R;

public class OrderProgressFragment extends Fragment {

    public OrderProgressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_progress, container, false);

        // After a delay, navigate to OrderSuccessFragment
        new Handler().postDelayed(() -> {
            NavController navController = NavHostFragment.findNavController(OrderProgressFragment.this);
            navController.navigate(R.id.action_orderProgressFragment_to_orderSuccessfulFragment);
        }, 3000); // 3 seconds

        return view;
    }
}