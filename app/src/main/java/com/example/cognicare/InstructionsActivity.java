package com.example.cognicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class InstructionsActivity extends AppCompatActivity {

    String buttonValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Intent intent = getIntent();
        buttonValue = intent.getStringExtra("value");

        int intValue = Integer.valueOf(buttonValue);

        switch (intValue) {
            case 1:
                Intent bow = new Intent(InstructionsActivity.this, BowPoseActivity.class);
                startActivity(bow);
                break;
            case 2:
                Intent bridge = new Intent(InstructionsActivity.this, BridgePoseActivity.class);
                startActivity(bridge);
                break;
            case 3:
                Intent chair = new Intent(InstructionsActivity.this, ChairPoseActivity.class);
                startActivity(chair);
                break;
            case 4:
                Intent child = new Intent(InstructionsActivity.this, ChildPostActivity.class);
                startActivity(child);
                break;
            case 5:
                Intent cobbler = new Intent(InstructionsActivity.this, CobblerPostActivity.class);
                startActivity(cobbler);
                break;
            case 6:
                Intent cow = new Intent(InstructionsActivity.this, CowPostActivity.class);
                startActivity(cow);
                break;
            case 7:
                Intent playji = new Intent(InstructionsActivity.this, PlayjiPostActivity.class);
                startActivity(playji);
                break;
            case 8:
                Intent pauseji = new Intent(InstructionsActivity.this, PausejiPostActivity.class);
                startActivity(pauseji);
                break;
            case 9:
                Intent plank = new Intent(InstructionsActivity.this, PlankActivity.class);
                startActivity(plank);
                break;
            case 10:
                Intent crunches = new Intent(InstructionsActivity.this, CrunchesActivity.class);
                startActivity(crunches);
                break;
            case 11:
                Intent situp = new Intent(InstructionsActivity.this, SitupActivity.class);
                startActivity(situp);
                break;
            case 12:
                Intent rotation = new Intent(InstructionsActivity.this, RotationActivity.class);
                startActivity(rotation);
                break;
            case 13:
                Intent twist = new Intent(InstructionsActivity.this, TwistActivity.class);
                startActivity(twist);
                break;
            case 14:
                Intent windmill = new Intent(InstructionsActivity.this, WindMillActivity.class);
                startActivity(windmill);
                break;
            case 15:
                Intent legup = new Intent(InstructionsActivity.this, LegUpActivity.class);
                startActivity(legup);
                break;
        }
    }
}