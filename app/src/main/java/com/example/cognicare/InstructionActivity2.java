package com.example.cognicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class InstructionActivity2 extends AppCompatActivity {

    String buttonValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction2);

        Intent intent = getIntent();
        buttonValue = intent.getStringExtra("value");

        int intValue = Integer.valueOf(buttonValue);

        switch (intValue) {
            case 1:
                Intent bow = new Intent(InstructionActivity2.this, BowPoseActivity2.class);
                startActivity(bow);
                break;
            case 2:
                Intent bridge = new Intent(InstructionActivity2.this, BridgePoseActivity2.class);
                startActivity(bridge);
                break;
            case 3:
                Intent chair = new Intent(InstructionActivity2.this, ChairPoseActivity2.class);
                startActivity(chair);
                break;
            case 4:
                Intent child = new Intent(InstructionActivity2.this, ChildPostActivity2.class);
                startActivity(child);
                break;
            case 5:
                Intent cobbler = new Intent(InstructionActivity2.this, CobblerPostActivity2.class);
                startActivity(cobbler);
                break;
            case 6:
                Intent cow = new Intent(InstructionActivity2.this, CowPostActivity2.class);
                startActivity(cow);
                break;
            case 7:
                Intent playji = new Intent(InstructionActivity2.this, PlayjiPostActivity2.class);
                startActivity(playji);
                break;
            case 8:
                Intent pauseji = new Intent(InstructionActivity2.this, PausejiPostActivity2.class);
                startActivity(pauseji);
                break;
            case 9:
                Intent plank = new Intent(InstructionActivity2.this, PlankActivity2.class);
                startActivity(plank);
                break;
            case 10:
                Intent crunches = new Intent(InstructionActivity2.this, CrunchesActivity2.class);
                startActivity(crunches);
                break;
            case 11:
                Intent situp = new Intent(InstructionActivity2.this, SitupActivity2.class);
                startActivity(situp);
                break;
            case 12:
                Intent rotation = new Intent(InstructionActivity2.this, RotationActivity2.class);
                startActivity(rotation);
                break;
            case 13:
                Intent twist = new Intent(InstructionActivity2.this, TwistActivity2.class);
                startActivity(twist);
                break;
            case 14:
                Intent windmill = new Intent(InstructionActivity2.this, WindMillActivity2.class);
                startActivity(windmill);
                break;
            case 15:
                Intent legup = new Intent(InstructionActivity2.this, LegUpActivity2.class);
                startActivity(legup);
                break;
        }
    }
}