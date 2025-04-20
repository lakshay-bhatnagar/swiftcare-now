package com.example.swiftcare.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.swiftcare.MainActivity;
import com.example.swiftcare.R;
import com.example.swiftcare.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding fragmentNotificationsBinding;
    private Button bookAnythingBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        fragmentNotificationsBinding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = fragmentNotificationsBinding.getRoot();

        bookAnythingBtn = fragmentNotificationsBinding.bookAnythingBtn;

        bookAnythingBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_navigation_notifications_to_cabTransitionFragment);
        });

        ((MainActivity) requireActivity()).setBottomNavVisibility(true);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentNotificationsBinding = null;
    }
}