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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {

    private FirebaseAuth authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CogniCare");

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

        LinearLayout brainStimulation = findViewById(R.id.brain_stimulation_game);
        LinearLayout nutritionAdvice = findViewById(R.id.nutrition_advice);
        LinearLayout progressReport = findViewById(R.id.progress_report);
        LinearLayout fitness = findViewById(R.id.fitness);

        brainStimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, BrainGamesActivity.class);
                startActivity(intent);
            }
        });

        nutritionAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, NutritionAdviceHomePage.class);
                startActivity(intent);
            }
        });

        progressReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, SplashScreenProgress.class);
                startActivity(intent);
            }
        });

        fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, SplashScreen.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu_view_profile_hp){
            Intent intent = new Intent(HomePage.this, UserProfileActivity.class);
            startActivity(intent);
        } else if(id == R.id.menu_settings_hp){
            Toast.makeText(HomePage.this, "menu_settings", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.menu_logout_hp){
            authProfile.signOut();
            Toast.makeText(HomePage.this, "Logged Out", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(HomePage.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else if(id == R.id.menu_share_hp) {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String sharebody = "Greetings from CogniCare \nElevate cognitive well-being with CogniCare, it offers engaging activities, assessments, etc.\nDownload it for free at: "+"https://play.google.com/store/apps/details?id=com.example.cognicare&h1=en";
            String sharehub = "CogniCare App";
            myIntent.putExtra(Intent.EXTRA_SUBJECT, sharehub);
            myIntent.putExtra(Intent.EXTRA_TEXT, sharebody);
            startActivity(Intent.createChooser(myIntent, "share using"));
        } else if(id == R.id.menu_terms_hp) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cognicareapplication.blogspot.com/2024/01/privacy-policy-your-privacy-is.html"));
            startActivity(intent);
        } else {
            Toast.makeText(HomePage.this, "Something went wrong!", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}