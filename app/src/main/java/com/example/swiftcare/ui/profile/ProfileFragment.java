package com.example.swiftcare.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swiftcare.MainActivity;
import com.example.swiftcare.R;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((MainActivity) requireActivity()).setBottomNavVisibility(true);

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}