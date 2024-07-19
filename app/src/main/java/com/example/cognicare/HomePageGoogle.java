package com.example.cognicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePageGoogle extends AppCompatActivity {

    private FirebaseAuth authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_google);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CogniCare");

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.common_menu_google, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu_settings_google){
            Toast.makeText(HomePageGoogle.this, "menu_settings", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.menu_delete_profile_google){
            Intent intent = new Intent(HomePageGoogle.this, DeleteProfileGoogleActivity.class);
            startActivity(intent);
        } else if(id == R.id.menu_logout_google){
            authProfile.signOut();
            Toast.makeText(HomePageGoogle.this, "Logged Out", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(HomePageGoogle.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else if(id == R.id.menu_share_google) {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String sharebody = "Greetings from CogniCare \nElevate cognitive well-being with CogniCare, it offers engaging activities, assessments, etc.\nDownload it for free at: "+"https://play.google.com/store/apps/details?id=com.example.cognicare&h1=en";
            String sharehub = "CogniCare App";
            myIntent.putExtra(Intent.EXTRA_SUBJECT, sharehub);
            myIntent.putExtra(Intent.EXTRA_TEXT, sharebody);
            startActivity(Intent.createChooser(myIntent, "share using"));
        } else if(id == R.id.menu_terms_google) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cognicareapplication.blogspot.com/2024/01/privacy-policy-your-privacy-is.html"));
            startActivity(intent);
        } else {
            Toast.makeText(HomePageGoogle.this, "Something went wrong!", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}