package com.example.swiftcare.ui.login;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.swiftcare.MainActivity;
import com.example.swiftcare.OTPService;
import com.example.swiftcare.R;

public class OtpVerificationFragment extends Fragment {

    private Button verifyOtpButton;
    private OTPService otpService;
    private String phoneNumber;

    private static final int SMS_PERMISSION_CODE = 101;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp_verification, container, false);

        verifyOtpButton = view.findViewById(R.id.verifyOtpButton);
        otpService = new OTPService();
        EditText[] otpBoxes = {
                view.findViewById(R.id.otpBox1),
                view.findViewById(R.id.otpBox2),
                view.findViewById(R.id.otpBox3),
                view.findViewById(R.id.otpBox4),
                view.findViewById(R.id.otpBox5),
                view.findViewById(R.id.otpBox6)
        };

        String checker;

        if (getArguments() != null) {
            phoneNumber = getArguments().getString("phoneNumber");

            // Generate random 6-digit OTP
            String generatedOtp = getArguments().getString("generatedOtp");
            checker = generatedOtp;

            // Send the OTP via SMS (if permission is granted)
//            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.SEND_SMS)
//                    == PackageManager.PERMISSION_GRANTED) {
//                android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
//                smsManager.sendTextMessage(phoneNumber, null, "Your SwiftCare OTP is: " + generatedOtp, null, null);
//                Toast.makeText(getContext(), "OTP sent to " + phoneNumber, Toast.LENGTH_SHORT).show();
//            } else {
//                ActivityCompat.requestPermissions(requireActivity(),
//                        new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
//            }
        } else {
            checker = "";
        }

        // Check for SMS permission
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        }

        verifyOtpButton.setOnClickListener(v -> {
            StringBuilder enteredOtpBuilder = new StringBuilder();
            for (EditText box : otpBoxes) {
                enteredOtpBuilder.append(box.getText().toString().trim());
            }
            String enteredOtp = enteredOtpBuilder.toString();

            if (enteredOtp.equals(checker)) {
                Toast.makeText(getContext(), "Verification Successful", Toast.LENGTH_LONG).show();
                //todo to change this nav controller to navigate from otp verification to animation to homescreen
                Navigation.findNavController(v).navigate(R.id.action_otpVerificationFragment_to_homeTransitionFragment);
            } else {
                Toast.makeText(getContext(), "Verification Not Successful", Toast.LENGTH_LONG).show();
            }
        });

        TextView timerTextView = view.findViewById(R.id.timerTextView);

        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                timerTextView.setText("Expires in 00:" + (seconds < 10 ? "0" + seconds : seconds));
            }

            public void onFinish() {
                timerTextView.setText("OTP expired");
                // Optionally disable OTP input
            }
        }.start();

        ((MainActivity) requireActivity()).setBottomNavVisibility(false);

        setupOtpInputs(view);

        return view;
    }

    private void setupOtpInputs(View view) {
        EditText[] otpBoxes = {
                view.findViewById(R.id.otpBox1),
                view.findViewById(R.id.otpBox2),
                view.findViewById(R.id.otpBox3),
                view.findViewById(R.id.otpBox4),
                view.findViewById(R.id.otpBox5),
                view.findViewById(R.id.otpBox6)
        };

        for (int i = 0; i < otpBoxes.length; i++) {
            int currentIndex = i;
            otpBoxes[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 1 && currentIndex < otpBoxes.length - 1) {
                        otpBoxes[currentIndex + 1].requestFocus();
                    } else if (s.length() == 0 && currentIndex > 0) {
                        otpBoxes[currentIndex - 1].requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
    }
}
