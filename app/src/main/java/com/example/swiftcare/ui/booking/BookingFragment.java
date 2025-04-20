package com.example.swiftcare.ui.booking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swiftcare.MainActivity;
import com.example.swiftcare.R;
import com.example.swiftcare.databinding.FragmentBookingBinding;

public class BookingFragment extends Fragment {


    FragmentBookingBinding fragmentBookingBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentBookingBinding = FragmentBookingBinding.inflate(inflater, container, false);
        View root = fragmentBookingBinding.getRoot();

//        requireActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);
        ((MainActivity) requireActivity()).setBottomNavVisibility(false);

        return root;
    }

//    public void onDestroyView() {
//        super.onDestroyView();
//
//        requireActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
//    }
}