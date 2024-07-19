package com.example.cognicare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cognicare.R;

public class NutritionAdviceHomePage extends AppCompatActivity {

    private Button button_search_recipe, button_health_news, button_exit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_advice_home_page);

        button_search_recipe = findViewById(R.id.button_search_recipe);
        button_health_news = findViewById(R.id.button_health_news);
        button_exit = findViewById(R.id.button_exit);

        button_search_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NutritionAdviceHomePage.this, NutritionActivity.class);
                startActivity(intent);
            }
        });

        button_health_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NutritionAdviceHomePage.this, NewsActivity.class);
                startActivity(intent);
            }
        });

        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NutritionAdviceHomePage.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

    }
}