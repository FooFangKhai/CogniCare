package com.example.cognicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Collections;

public class TestAssessment extends AppCompatActivity {

    private TextView score, attempts;
    private ImageView iv_11, iv_12, iv_13, iv_14, iv_21, iv_22, iv_23, iv_24;
    private Integer[] cardsArray = {101, 102, 103, 104, 201, 202, 203, 204};

    private int image101, image102, image103, image104, image201, image202, image203, image204;
    private int firstCard, secondCard;
    private int clickedFirst, clickedSecond;
    private int cardNumber = 1;
    private int turn = 1;
    private int playerPoints = 0;
    private int playerAttempts = 0;
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private static final String TAG = "TestAssessment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_assessment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cognitive Test Assessment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();

        showInitialDialog();

        score = findViewById(R.id.score);
        attempts = findViewById(R.id.attempts);
        iv_11 = findViewById(R.id.iv_11);
        iv_12 = findViewById(R.id.iv_12);
        iv_13 = findViewById(R.id.iv_13);
        iv_14 = findViewById(R.id.iv_14);
        iv_21 = findViewById(R.id.iv_21);
        iv_22 = findViewById(R.id.iv_22);
        iv_23 = findViewById(R.id.iv_23);
        iv_24 = findViewById(R.id.iv_24);

        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");
        iv_21.setTag("4");
        iv_22.setTag("5");
        iv_23.setTag("6");
        iv_24.setTag("7");
    }

    private void showInitialDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Welcome to Cognitive Test Assessment! Completion of this test is a prerequisite for registration.")
                .setCancelable(false)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        initializeGame();

                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteUserData(firebaseUser);
                        finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void deleteUserData(FirebaseUser firebaseUser) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
        databaseReference.child(firebaseUser.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Exited");
                deleteUser();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, e.getMessage());
                Toast.makeText(TestAssessment.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteUser() {
        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    authProfile.signOut();
                    Toast.makeText(TestAssessment.this, "Exited", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TestAssessment.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    try {
                        throw task.getException();
                    } catch (Exception e) {
                        Toast.makeText(TestAssessment.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initializeGame() {
        flipAllCards();
        frontOfCardResources();

        iv_11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_11, theCard);
            }
        });
        iv_12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_12, theCard);
            }
        });
        iv_13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_13, theCard);
            }
        });
        iv_14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_14, theCard);
            }
        });
        iv_21.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_21, theCard);
            }
        });
        iv_22.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_22, theCard);
            }
        });
        iv_23.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_23, theCard);
            }
        });
        iv_24.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_24, theCard);
            }
        });
    }

    private void flipAllCards() {
        frontOfCardResources();

        Collections.shuffle(Arrays.asList(cardsArray));
        iv_11.setImageResource(getImageResource(cardsArray[0]));
        iv_12.setImageResource(getImageResource(cardsArray[1]));
        iv_13.setImageResource(getImageResource(cardsArray[2]));
        iv_14.setImageResource(getImageResource(cardsArray[3]));
        iv_21.setImageResource(getImageResource(cardsArray[4]));
        iv_22.setImageResource(getImageResource(cardsArray[5]));
        iv_23.setImageResource(getImageResource(cardsArray[6]));
        iv_24.setImageResource(getImageResource(cardsArray[7]));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iv_11.setImageResource(R.drawable.ic_back);
                iv_12.setImageResource(R.drawable.ic_back);
                iv_13.setImageResource(R.drawable.ic_back);
                iv_14.setImageResource(R.drawable.ic_back);
                iv_21.setImageResource(R.drawable.ic_back);
                iv_22.setImageResource(R.drawable.ic_back);
                iv_23.setImageResource(R.drawable.ic_back);
                iv_24.setImageResource(R.drawable.ic_back);
            }
        }, 3000);
    }

    private int getImageResource(Integer cardNumber) {
        switch (cardNumber) {
            case 101:
                return image101;
            case 102:
                return image102;
            case 103:
                return image103;
            case 104:
                return image104;
            case 201:
                return image201;
            case 202:
                return image202;
            case 203:
                return image203;
            case 204:
                return image204;
            default:
                return R.drawable.ic_back;
        }
    }

    private void doStuff(ImageView iv, int card) {
        if(cardsArray[card] == 101){
            iv.setImageResource(image101);
        } else if(cardsArray[card] == 102){
            iv.setImageResource(image102);
        } else if(cardsArray[card] == 103){
            iv.setImageResource(image103);
        } else if(cardsArray[card] == 104){
            iv.setImageResource(image104);
        } else if(cardsArray[card] == 201){
            iv.setImageResource(image201);
        } else if(cardsArray[card] == 202){
            iv.setImageResource(image202);
        } else if(cardsArray[card] == 203){
            iv.setImageResource(image203);
        } else if(cardsArray[card] == 204){
            iv.setImageResource(image204);
        }

        if(cardNumber == 1){
            firstCard = cardsArray[card];
            if(firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;

            iv.setEnabled(false);
        } else if(cardNumber == 2){
            secondCard = cardsArray[card];
            if(secondCard > 200){
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = card;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            }, 1000);
        }
    }
    private void calculate() {
        playerAttempts++;
        attempts.setText("Attempts: " + playerAttempts);
        if(firstCard == secondCard){
            if(clickedFirst == 0){
                iv_11.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 1){
                iv_12.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 2){
                iv_13.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 3){
                iv_14.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 4){
                iv_21.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 5){
                iv_22.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 6){
                iv_23.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 7){
                iv_24.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond == 0){
                iv_11.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 1){
                iv_12.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 2){
                iv_13.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 3){
                iv_14.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 4){
                iv_21.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 5){
                iv_22.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 6){
                iv_23.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 7){
                iv_24.setVisibility(View.INVISIBLE);
            }

            if(turn == 1){
                playerPoints++;
                score.setText("Score: " + playerPoints);
            }
        } else {
            iv_11.setImageResource(R.drawable.ic_back);
            iv_12.setImageResource(R.drawable.ic_back);
            iv_13.setImageResource(R.drawable.ic_back);
            iv_14.setImageResource(R.drawable.ic_back);
            iv_21.setImageResource(R.drawable.ic_back);
            iv_22.setImageResource(R.drawable.ic_back);
            iv_23.setImageResource(R.drawable.ic_back);
            iv_24.setImageResource(R.drawable.ic_back);
        }

        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_14.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_24.setEnabled(true);

        checkEnd();
    }

    private void checkEnd() {
        if(iv_11.getVisibility() == View.INVISIBLE &&
                iv_12.getVisibility() == View.INVISIBLE &&
                iv_13.getVisibility() == View.INVISIBLE &&
                iv_14.getVisibility() == View.INVISIBLE &&
                iv_21.getVisibility() == View.INVISIBLE &&
                iv_22.getVisibility() == View.INVISIBLE &&
                iv_23.getVisibility() == View.INVISIBLE &&
                iv_24.getVisibility() == View.INVISIBLE) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TestAssessment.this);

            alertDialogBuilder
                    .setMessage("You have completed the test!")
                    .setCancelable(false)
                    .setPositiveButton("Finish", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            firebaseUser.sendEmailVerification();
                            Toast.makeText(TestAssessment.this, "User registered successfully. Please verify your email", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TestAssessment.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteUserData(firebaseUser);
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        updateScoreAndAttempts(playerAttempts);
    }

    private void updateScoreAndAttempts(int attempts) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();

        if (firebaseUser != null) {
            DatabaseReference referenceAttempts = FirebaseDatabase.getInstance().getReference("Registered Users")
                    .child(firebaseUser.getUid());
            referenceAttempts.child("i").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        int currentIValue = snapshot.getValue(Integer.class);
                        String value = String.valueOf(currentIValue);
                        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
                        referenceProfile.child(firebaseUser.getUid()).child("attempts"+currentIValue)
                                .child("attempts").setValue(attempts);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void frontOfCardResources() {
        image101 = R.drawable.ic_image101;
        image102 = R.drawable.ic_image102;
        image103 = R.drawable.ic_image103;
        image104 = R.drawable.ic_image104;
        image201 = R.drawable.ic_image201;
        image202 = R.drawable.ic_image202;
        image203 = R.drawable.ic_image203;
        image204 = R.drawable.ic_image204;
    }
}