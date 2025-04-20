package com.example.swiftcare;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OTPService {
    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Ensures 6-digit
        return String.valueOf(otp);
    }
}
