package com.example.swiftcare.transitions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swiftcare.MainActivity;
import com.example.swiftcare.R;

public class CabTransitionFragment extends Fragment {

    public CabTransitionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cab_transition, container, false);

        // After a delay, navigate to OrderSuccessFragment
        new Handler().postDelayed(() -> {
            NavController navController = NavHostFragment.findNavController(CabTransitionFragment.this);
            //todo change this to booking
            navController.navigate(R.id.action_cabTransitionFragment_to_bookingFragment);
        }, 3000); // 3 seconds

        ((MainActivity) requireActivity()).setBottomNavVisibility(false);

        return view;
    }
}