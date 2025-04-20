package com.example.swiftcare.ui.payment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swiftcare.MainActivity;
import com.example.swiftcare.R;


public class OrderSuccessfulFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        requireActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);
        ((MainActivity) requireActivity()).setBottomNavVisibility(false);
        return inflater.inflate(R.layout.fragment_order_successful, container, false);
    }

//    public void onDestroyView() {
//        super.onDestroyView();
//        requireActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
//    }
}