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


public class HomeTransitionFragment extends Fragment {

    public HomeTransitionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_transition, container, false);

        // After a delay, navigate to OrderSuccessFragment
        new Handler().postDelayed(() -> {
            NavController navController = NavHostFragment.findNavController(HomeTransitionFragment.this);
            //todo change this to home
            navController.navigate(R.id.action_homeTransitionFragment_to_navigation_home);
        }, 2500); // 2.5 seconds

        return view;
    }
}