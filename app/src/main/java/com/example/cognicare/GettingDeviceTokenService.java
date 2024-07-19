package com.example.cognicare;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class GettingDeviceTokenService extends FirebaseMessagingService {
    private static final String TAG = "GettingDeviceTokenService";
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.e(TAG, "Refreshed token: " +  FirebaseMessaging.getInstance().getToken());
    }
}