package com.example.cognicare;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProgressActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Progress Report");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showInitialDialog();

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
        retrieveData();

        button = findViewById(R.id.button_take_assessment_test);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = auth.getCurrentUser();

                if (firebaseUser != null) {
                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users")
                            .child(firebaseUser.getUid());
                    referenceProfile.child("i").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()) {
                                int currentIValue = snapshot.getValue(Integer.class);
                                int updatedIValue = currentIValue + 1;
                                referenceProfile.child("i").setValue(updatedIValue);

                                String attemptKey = "attempts" + updatedIValue;
                                DatabaseReference attemptReference = referenceProfile.child(attemptKey);

                                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                attemptReference.child("date").setValue(currentDate);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                Intent intent = new Intent(ProgressActivity.this, TestAssessment2.class);
                startActivity(intent);
            }
        });
    }

    private void showInitialDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Welcome to your Progress Report page! \n\nWe recommend taking the assessment test every two weeks to track your progress effectively. \n\nRegular assessments help in understanding your growth and identifying areas for improvement. \n\nKeep up the good work on your cognitive journey!")
                .setCancelable(false)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void retrieveData() {
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();

            DatabaseReference userReference = databaseReference.child(userId);

            userReference.child("i").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot iSnapshot) {
                    if (iSnapshot.exists()) {
                        int totalAttempts = iSnapshot.getValue(Integer.class);

                        for (int i = 1; i <= totalAttempts; i++) {
                            final int attemptNumber = i;

                            String attemptKey = "attempts" + attemptNumber;
                            DatabaseReference attemptReference = userReference.child(attemptKey);

                            attemptReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        String attemptValue = String.valueOf(dataSnapshot.child("attempts").getValue());
                                        String dateValue = String.valueOf(dataSnapshot.child("date").getValue());

                                        TextView textViewAttempts = findViewById(R.id.textViewAttempts);
                                        textViewAttempts.append("Test Assessment " + attemptNumber + "\n" + "Attempt Value = " + attemptValue +"\nDate Taken = " + dateValue +"\n\n");
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


}