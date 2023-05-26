package com.example.footballquiz.questionsMethods;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.footballquiz.questions.WhoHasScoredMore;
import com.example.footballquiz.questions.WhoIsFaster;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class WhoHasScoredMoreMethods extends AppCompatActivity {
    protected void ratingIncrease() {


        // set the elo rating text
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentRef = firestore.collection("users").document(userId);

        documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // Retrieve the current value of the field
                            int currentScore = documentSnapshot.getLong("Who has scored more rating").intValue();

                            Random rand = new Random();
                            // Perform operations on the current score
                            int newScore = currentScore + (15 + rand.nextInt(6));


                            // Update the field with the new value
                            documentRef.update("Who has scored more rating", newScore)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            startActivity(new Intent(getApplicationContext(), WhoHasScoredMore.class));
                                            finish();

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // An error occurred while updating the field
                                            Toast.makeText(getApplicationContext(), "An error occurred while updating the field", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            // Document does not exist
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // An error occurred while retrieving the document
                        Toast.makeText(getApplicationContext(), "An error occurred while retrieving the document", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    protected void ratingDecrease() {


        // set the elo rating text
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentRef = firestore.collection("users").document(userId);

        documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // Retrieve the current value of the field
                            int currentScore = documentSnapshot.getLong("Who has scored more rating").intValue();

                            Random rand = new Random();
                            // Perform operations on the current score
                            int newScore = currentScore - (25 + rand.nextInt(6));


                            // Update the field with the new value
                            documentRef.update("Who has scored more rating", newScore)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            startActivity(new Intent(getApplicationContext(), WhoHasScoredMore.class));
                                            finish();

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // An error occurred while updating the field
                                            Toast.makeText(getApplicationContext(), "An error occurred while updating the field", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            // Document does not exist
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // An error occurred while retrieving the document
                        Toast.makeText(getApplicationContext(), "An error occurred while retrieving the document", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
