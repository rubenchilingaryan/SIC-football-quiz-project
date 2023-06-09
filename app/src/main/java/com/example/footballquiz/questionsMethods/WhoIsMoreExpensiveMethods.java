package com.example.footballquiz.questionsMethods;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.footballquiz.R;
import com.example.footballquiz.mainActivities.MainMenu;
import com.example.footballquiz.questions.WhoIsMoreExpensive;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class WhoIsMoreExpensiveMethods extends AppCompatActivity {
    protected void showPopUpDialogMoreExpensiveDecrease() {
        // create a dialog instance
        Dialog dialog = new Dialog(this);

        // set the layout for the dialog
        dialog.setContentView(R.layout.dialog_layout);

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);

        // set the title for the dialog
        dialog.setTitle("Result");

        // find the elements in the custom layout
        TextView eloTextView = (TextView) dialog.findViewById(R.id.eloTextView);
        Button returnButton = (Button) dialog.findViewById(R.id.returnButton);
        Button nextButton = (Button) dialog.findViewById(R.id.nextButton);

        // set the elo rating text
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentRef = firestore.collection("users").document(userId);

        documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // Retrieve the current value of the field
                            int currentScore = documentSnapshot.getLong("Who is more expensive rating").intValue();

                            Random rand = new Random();
                            // Perform operations on the current score
                            int newScore = currentScore - (25 + rand.nextInt(6));


                            // Update the field with the new value
                            documentRef.update("Who is more expensive rating", newScore)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Field updated successfully
                                            eloTextView.setText(Integer.toString(currentScore));
                                            ValueAnimator animator = ValueAnimator.ofInt(currentScore, newScore);
                                            animator.setDuration(2000);

                                            TimeInterpolator interpolator = new DecelerateInterpolator();

                                            animator.setInterpolator(interpolator);

                                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                                @Override
                                                public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                                                    int animatedValue = (int) animation.getAnimatedValue();
                                                    eloTextView.setText("Your new rating: " + String.valueOf(animatedValue));
                                                }
                                            });

                                            animator.start();
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

        // set the click listener for the return button
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(i);
                finish();
            }
        });

        // set the click listener for the next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(), WhoIsMoreExpensive.class);
                startActivity(i);
                finish();
            }
        });

        // show the dialog box
        dialog.show();
    }
    protected void showPopUpDialogMoreExpensiveIncrease() {
        // create a dialog instance
        Dialog dialog = new Dialog(this);

        // set the layout for the dialog
        dialog.setContentView(R.layout.dialog_layout);

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);

        // set the title for the dialog
        dialog.setTitle("Result");

        // find the elements in the custom layout
        TextView eloTextView = (TextView) dialog.findViewById(R.id.eloTextView);
        Button returnButton = (Button) dialog.findViewById(R.id.returnButton);
        Button nextButton = (Button) dialog.findViewById(R.id.nextButton);

        // set the elo rating text
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentRef = firestore.collection("users").document(userId);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(i);
                finish();
            }
        });

        // set the click listener for the next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(), WhoIsMoreExpensive.class);
                startActivity(i);
                finish();
            }
        });

        documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // Retrieve the current value of the field
                            int currentScore = documentSnapshot.getLong("Who is more expensive rating").intValue();

                            Random rand = new Random();
                            // Perform operations on the current score
                            int newScore = currentScore + (15 + rand.nextInt(6));


                            // Update the field with the new value
                            documentRef.update("Who is more expensive rating", newScore)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Field updated successfully
                                            eloTextView.setText(Integer.toString(currentScore));
                                            ValueAnimator animator = ValueAnimator.ofInt(currentScore, newScore);
                                            animator.setDuration(2000);

                                            TimeInterpolator interpolator = new DecelerateInterpolator();

                                            animator.setInterpolator(interpolator);

                                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                                @Override
                                                public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                                                    int animatedValue = (int) animation.getAnimatedValue();
                                                    eloTextView.setText("Your new rating: " + String.valueOf(animatedValue));
                                                }
                                            });

                                            animator.start();

                                            dialog.show();
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
                            int currentScore = documentSnapshot.getLong("Who is more expensive rating").intValue();

                            Random rand = new Random();
                            // Perform operations on the current score
                            int newScore = currentScore + (15 + rand.nextInt(6));

                            int rightAnswers = documentSnapshot.getLong("Who is more expensive right answers").intValue();
                            int newRightAnswers = rightAnswers + 1;


                            documentRef.update("Who is more expensive right answers",newRightAnswers);


                            // Update the field with the new value
                            documentRef.update("Who is more expensive rating", newScore)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
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
                            int currentScore = documentSnapshot.getLong("Who is more expensive rating").intValue();

                            Random rand = new Random();
                            // Perform operations on the current score
                            int newScore = currentScore - (25 + rand.nextInt(6));
                            int wrongAnswers = documentSnapshot.getLong("Who is more expensive wrong answers").intValue();
                            int newWrongAnswers = wrongAnswers + 1;


                            documentRef.update("Who is more expensive wrong answers",newWrongAnswers);


                            // Update the field with the new value
                            documentRef.update("Who is more expensive rating", newScore)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
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

    protected void nextActivity(){
        startActivity(new Intent(getApplicationContext(), WhoIsMoreExpensive.class));
        finish();
    }
}
