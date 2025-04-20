package com.example.swiftcare;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;



import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.swiftcare.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setVisibility(View.GONE);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_notifications, R.id.navigation_dashboard, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Check if navigating to HomeFragment
        String navigateTo = getIntent().getStringExtra("navigateTo");
        if ("HomeFragment".equals(navigateTo)) {
            navController.navigate(R.id.navigation_home);
        }
    }

    public void setBottomNavVisibility(boolean isVisible){
        View navView = findViewById(R.id.nav_view);
        if (navView != null) {
            navView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

}