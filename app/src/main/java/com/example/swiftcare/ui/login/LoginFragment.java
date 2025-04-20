package com.example.swiftcare.ui.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.swiftcare.MainActivity;
import android.Manifest;
import com.example.swiftcare.OTPService;
import com.example.swiftcare.R;

public class LoginFragment extends Fragment {

    private EditText phoneNumberEditText;
    private Button sendOtpButton;
    private OTPService otpService;
    private ImageView imageView;

    private int[] images = {R.drawable.login_img1_effect, R.drawable.login_img2, R.drawable.login_img3};
    private int currentIndex = 0;
    private Handler handler = new Handler(Looper.getMainLooper());
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        phoneNumberEditText = view.findViewById(R.id.phoneNumberEditText);
        sendOtpButton = view.findViewById(R.id.sendOtpButton);

        imageView = view.findViewById(R.id.login_imageview);
        otpService = new OTPService();

        startImageAnimation();
        // removed functionality of twillo
//        sendOtpButton.setOnClickListener(v -> sendOtp());

        sendOtpButton.setOnClickListener(v -> sendOtp());

        ((MainActivity) requireActivity()).setBottomNavVisibility(false);

        return view;
    }

    // Image Switching using Handler
    private void startImageAnimation() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentIndex = (currentIndex + 1) % images.length;
                imageView.setImageResource(images[currentIndex]);
                handler.postDelayed(this, 3000); // 3 seconds
            }
        }, 3000); // Initial delay of 3 seconds
    }


    private void sendOtp() {
        String phoneNumber = phoneNumberEditText.getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            Toast.makeText(getContext(), "Please enter a phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, 101);
            return; // Exit and wait for permission result
        }

        String otp = otpService.generateOtp();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, "Your SwiftCare OTP is: " + otp, null, null);
            Toast.makeText(getContext(), "OTP Sent Successfully", Toast.LENGTH_SHORT).show();

            // Navigate and pass OTP as well (for demo)
            Bundle bundle = new Bundle();
            bundle.putString("phoneNumber", phoneNumber);
            bundle.putString("generatedOtp", otp);
            Navigation.findNavController(requireView()).navigate(R.id.otpVerificationFragment, bundle);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Failed to send OTP: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Try sending OTP again
                sendOtp();
            } else {
                Toast.makeText(getContext(), "SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
