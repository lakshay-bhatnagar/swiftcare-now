package com.example.swiftcare.ui.medicinedesc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swiftcare.MainActivity;
import com.example.swiftcare.R;
import com.example.swiftcare.databinding.FragmentMedicine1Binding;
import com.example.swiftcare.databinding.FragmentMedicine2Binding;


public class Medicine2Fragment extends Fragment {


    FragmentMedicine2Binding fragmentMedicine2Binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMedicine2Binding = FragmentMedicine2Binding.inflate(inflater, container, false);


        View root = fragmentMedicine2Binding.getRoot();

//        requireActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);

        ((MainActivity) requireActivity()).setBottomNavVisibility(false);
        return root;
    }

//    public void onDestroyView() {
//        super.onDestroyView();
//        requireActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
//    }
}