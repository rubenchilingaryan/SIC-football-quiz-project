package com.example.footballquiz.mainActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.footballquiz.R;
import com.example.footballquiz.authorization.LoginActivity;
import com.github.drjacky.imagepicker.ImagePicker;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.UploadTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;



public class ProfileActivity extends AppCompatActivity {

    ImageView profilePic;
    ImageView addProfilePic;

    ImageView scrollRight,scrollLeft;

    private static final int REQUEST_CODE_IMAGE_PICKER = 1001;

    HorizontalScrollView horizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        horizontalScrollView = findViewById(R.id.horizontalScrollView);
        scrollLeft = findViewById(R.id.scroll_left);
        scrollRight = findViewById(R.id.scroll_right);

        scrollLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollViewLeft();
            }
        });

        scrollRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollViewRight();
            }
        });

        // Accuracies
        TextView accuracyWhoIsFaster = findViewById(R.id.accuracyWhoIsFaster);
        TextView accuracyWhoIsMoreExpensive = findViewById(R.id.accuracyWhoIsMoreExpensive);
        TextView accuracyGuessThePrice = findViewById(R.id.accuracyGuessThePrice);
        TextView accuracyGuessThePlayer = findViewById(R.id.accuracyGuessThePlayer);
        TextView accuracyWhoHasScoredMore = findViewById(R.id.accuracyWhoHasScoredMore);
        TextView accuracyWhoHasAssistedMore = findViewById(R.id.accuracyWhoHasAssistedMore);

        // Right answers
        TextView rightAnswersWhoIsFaster = findViewById(R.id.rightAnswersWhoIsFaster);
        TextView rightAnswersWhoIsMoreExpensive = findViewById(R.id.rightAnswersWhoIsMoreExpensive);
        TextView rightAnswersGuessThePrice = findViewById(R.id.rightAnswersGuessThePrice);
        TextView rightAnswersGuessThePlayer = findViewById(R.id.rightAnswersGuessThePlayer);
        TextView rightAnswerWhoHasScoredMore = findViewById(R.id.rightAnswersWhoHasScoredMore);
        TextView rightAnswersWhoHasAssistedMore = findViewById(R.id.rightAnswersWhoHasAssistedMore);

        // Wrong answers

        TextView wrongAnswersWhoIsFaster = findViewById(R.id.wrongAnswersWhoIsFaster);
        TextView wrongAnswersWhoIsMoreExpensive = findViewById(R.id.wrongAnswersWhoIsMoreExpensive);
        TextView wrongAnswersGuessThePrice = findViewById(R.id.wrongAnswersGuessThePrice);
        TextView wrongAnswersGuessThePlayer = findViewById(R.id.wrongAnswersGuessThePlayer);
        TextView wrongAnswersWhoHasScoredMore = findViewById(R.id.wrongAnswersWhoHasScoredMore);
        TextView wrongAnswersWhoHasAssistedMore = findViewById(R.id.wrongAnswersWhoHasAssistedMore);

        // User info

        TextView username = findViewById(R.id.username_profile);
        TextView email = findViewById(R.id.email_profile);

        // Buttons

        ImageView back = findViewById(R.id.backButtonProfile);
        addProfilePic = findViewById(R.id.addProfilePicButton);
        profilePic = findViewById(R.id.profilePic);
        TextView settings = findViewById(R.id.settings_button);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });

        // Firebase

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentRef = firestore.collection("users").document(userId);



        documentRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists() && documentSnapshot.contains("profileImageUrl")) {
                String imageUrl = documentSnapshot.getString("profileImageUrl");
                addProfilePic.setVisibility(View.GONE);
                Glide.with(this)
                        .load(imageUrl)
                        .apply(RequestOptions.circleCropTransform())
                        .into(profilePic);
            } else {
                // The user document doesn't exist or doesn't have a profile picture URL
            }
        }).addOnFailureListener(e -> {
            // Error retrieving user document
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        addProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(ProfileActivity.this)
                        .galleryOnly()
                        .crop(1f,1f)
                        .cropOval()
                        .compress(1024)
                        .maxResultSize(1080,1080)
                        .start();

            }
        });

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(ProfileActivity.this)
                        .galleryOnly()
                        .crop(1f,1f)
                        .cropOval()
                        .compress(1024)
                        .maxResultSize(1080,1080)
                        .start();
            }
        });

        documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String usernameFireStore = documentSnapshot.getString("Username");
                username.setText(usernameFireStore);
                String emailFireStore = documentSnapshot.getString("Email");
                email.setText(emailFireStore);

                // Who is faster
                int rightAnswersWhoIsFasterFireStore = documentSnapshot.getLong("Who is faster right answers").intValue();
                rightAnswersWhoIsFaster.setText(Integer.toString(rightAnswersWhoIsFasterFireStore) + "");
                int wrongAnswersWhoIsFasterFireStore = documentSnapshot.getLong("Who is faster wrong answers").intValue();
                wrongAnswersWhoIsFaster.setText(Integer.toString(wrongAnswersWhoIsFasterFireStore) + "");
                int allAnswersWhoIsFaster = rightAnswersWhoIsFasterFireStore + wrongAnswersWhoIsFasterFireStore;
                int accuracyWhoIsFasterFireStore;
                if(rightAnswersWhoIsFasterFireStore == 0 && wrongAnswersWhoIsFasterFireStore == 0){
                    accuracyWhoIsFasterFireStore = 0;
                }else {
                    accuracyWhoIsFasterFireStore = (rightAnswersWhoIsFasterFireStore * 100)/allAnswersWhoIsFaster;
                }
                accuracyWhoIsFaster.setText(Integer.toString(accuracyWhoIsFasterFireStore) + "%");
                selectColor(accuracyWhoIsFaster,accuracyWhoIsFasterFireStore);

                // Who is more expensive
                int rightAnswersWhoIsMoreExpensiveFireStore = documentSnapshot.getLong("Who is more expensive right answers").intValue();
                rightAnswersWhoIsMoreExpensive.setText(Integer.toString(rightAnswersWhoIsMoreExpensiveFireStore) + "");
                int wrongAnswersWhoIsMoreExpensiveFireStore = documentSnapshot.getLong("Who is more expensive wrong answers").intValue();
                wrongAnswersWhoIsMoreExpensive.setText(Integer.toString(wrongAnswersWhoIsMoreExpensiveFireStore) + "");
                int allAnswersWhoIsMoreExpensive = rightAnswersWhoIsMoreExpensiveFireStore + wrongAnswersWhoIsMoreExpensiveFireStore;
                int accuracyWhoIsMoreExpensiveFireStore;
                if(rightAnswersWhoIsMoreExpensiveFireStore == 0 && wrongAnswersWhoIsMoreExpensiveFireStore == 0){
                    accuracyWhoIsMoreExpensiveFireStore = 0;
                }else {
                    accuracyWhoIsMoreExpensiveFireStore = (rightAnswersWhoIsMoreExpensiveFireStore * 100) / allAnswersWhoIsMoreExpensive;
                }
                accuracyWhoIsMoreExpensive.setText(Integer.toString(accuracyWhoIsMoreExpensiveFireStore) + "%");
                selectColor(accuracyWhoIsMoreExpensive,accuracyWhoIsMoreExpensiveFireStore);


                // Guess the price
                int rightAnswersGuessThePriceFireStore = documentSnapshot.getLong("Guess the price right answers").intValue();
                rightAnswersGuessThePrice.setText(Integer.toString(rightAnswersGuessThePriceFireStore) + "");
                int wrongAnswersGuessThePriceFireStore = documentSnapshot.getLong("Guess the price wrong answers").intValue();
                wrongAnswersGuessThePrice.setText(Integer.toString(wrongAnswersGuessThePriceFireStore) + "");
                int allAnswersGuessThePrice = rightAnswersGuessThePriceFireStore + wrongAnswersGuessThePriceFireStore;
                int accuracyGuessThePriceFireStore;
                if(rightAnswersGuessThePriceFireStore == 0 && wrongAnswersGuessThePriceFireStore == 0){
                    accuracyGuessThePriceFireStore = 0;
                }else{
                    accuracyGuessThePriceFireStore  = (rightAnswersGuessThePriceFireStore * 100)/allAnswersGuessThePrice;
                }
                accuracyGuessThePrice.setText(Integer.toString(accuracyGuessThePriceFireStore) + "%");
                selectColor(accuracyGuessThePrice,accuracyGuessThePriceFireStore);

                // Guess the player
                int rightAnswersGuessThePlayerFireStore = documentSnapshot.getLong("Guess the player right answers").intValue();
                rightAnswersGuessThePlayer.setText(Integer.toString(rightAnswersGuessThePlayerFireStore) + "");
                int wrongAnswersGuessThePlayerFireStore = documentSnapshot.getLong("Guess the player wrong answers").intValue();
                wrongAnswersGuessThePlayer.setText(Integer.toString(wrongAnswersGuessThePlayerFireStore) + "");
                int allAnswersGuessThePlayer = rightAnswersGuessThePlayerFireStore + wrongAnswersGuessThePlayerFireStore;
                int accuracyGuessThePlayerFireStore;
                if(rightAnswersGuessThePlayerFireStore == 0 && wrongAnswersGuessThePriceFireStore == 0){
                    accuracyGuessThePlayerFireStore = 0;
                }else{
                    accuracyGuessThePlayerFireStore = (rightAnswersGuessThePlayerFireStore * 100)/allAnswersGuessThePlayer;
                }
                accuracyGuessThePlayer.setText(Integer.toString(accuracyGuessThePlayerFireStore) + "%");
                selectColor(accuracyGuessThePlayer,accuracyGuessThePlayerFireStore);

                // Who has scored more
                int rightAnswersWhoHasScoredMoreFireStore = documentSnapshot.getLong("Who has scored more right answers").intValue();
                rightAnswerWhoHasScoredMore.setText(Integer.toString(rightAnswersWhoHasScoredMoreFireStore) + "");
                int wrongAnswersWhoHasScoredMoreFireStore = documentSnapshot.getLong("Who has scored more wrong answers").intValue();
                wrongAnswersWhoHasScoredMore.setText(Integer.toString(wrongAnswersWhoHasScoredMoreFireStore) + "");
                int allAnswersWhoHasScoredMore = rightAnswersWhoHasScoredMoreFireStore + wrongAnswersWhoHasScoredMoreFireStore;
                int accuracyWhoHasScoredMoreFireStore;
                if(rightAnswersWhoHasScoredMoreFireStore == 0 && wrongAnswersWhoHasScoredMoreFireStore == 0){
                    accuracyWhoHasScoredMoreFireStore = 0;
                }else{
                    accuracyWhoHasScoredMoreFireStore = (rightAnswersWhoHasScoredMoreFireStore * 100)/allAnswersWhoHasScoredMore;
                }
                accuracyWhoHasScoredMore.setText(Integer.toString(accuracyWhoHasScoredMoreFireStore) + "%");
                selectColor(accuracyWhoHasScoredMore,accuracyWhoHasScoredMoreFireStore);

                // Who has assisted more
                int rightAnswersWhoHasAssistedMoreFireStore = documentSnapshot.getLong("Who has assisted more right answers").intValue();
                rightAnswersWhoHasAssistedMore.setText(Integer.toString(rightAnswersWhoHasAssistedMoreFireStore) + "");
                int wrongAnswersWhoHasAssistedMoreFireStore = documentSnapshot.getLong("Who has assisted more wrong answers").intValue();
                wrongAnswersWhoHasAssistedMore.setText(Integer.toString(wrongAnswersWhoHasAssistedMoreFireStore) + "");
                int allAnswersWhoHasAssistedMore = rightAnswersWhoHasAssistedMoreFireStore + wrongAnswersWhoHasAssistedMoreFireStore;
                int accuracyWhoHasAssistedMoreFireStore;
                if(rightAnswersWhoHasAssistedMoreFireStore == 0 && wrongAnswersWhoHasAssistedMoreFireStore == 0){
                    accuracyWhoHasAssistedMoreFireStore = 0;
                }else{
                    accuracyWhoHasAssistedMoreFireStore = (rightAnswersWhoHasAssistedMoreFireStore * 100)/allAnswersWhoHasAssistedMore;
                }
                accuracyWhoHasAssistedMore.setText(Integer.toString(accuracyWhoHasAssistedMoreFireStore) + "%");
                selectColor(accuracyWhoHasAssistedMore,accuracyWhoHasAssistedMoreFireStore);
            }




        });



    }
    private void selectColor(TextView accuracyTextView,int accuracyFireStore){
        if(accuracyFireStore >= 0 && accuracyFireStore <= 20){
            accuracyTextView.setTextColor(Color.parseColor("#930000"));
        }else if(accuracyFireStore >= 21 && accuracyFireStore<= 30){
            accuracyTextView.setTextColor(Color.parseColor("#E10A0A"));
        }else if(accuracyFireStore >= 31 && accuracyFireStore<= 45){
            accuracyTextView.setTextColor(Color.parseColor("#E16E0A"));
        }else if(accuracyFireStore >= 46 && accuracyFireStore<= 55){
            accuracyTextView.setTextColor(Color.parseColor("#E1A40A"));
        }else if(accuracyFireStore >= 56 && accuracyFireStore<= 65){
            accuracyTextView.setTextColor(Color.parseColor("#D3E10A"));
        }else if(accuracyFireStore >= 66 && accuracyFireStore<= 75){
            accuracyTextView.setTextColor(Color.parseColor("#7DE10A"));
        }else if(accuracyFireStore >= 76 && accuracyFireStore<= 85){
            accuracyTextView.setTextColor(Color.parseColor("#55E10A"));
        }else if(accuracyFireStore >= 86 && accuracyFireStore<= 92){
            accuracyTextView.setTextColor(Color.parseColor("#0AE159"));
        }else if(accuracyFireStore >= 93 && accuracyFireStore<= 100){
            accuracyTextView.setTextColor(Color.parseColor("#0AE1AF"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImagePicker.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            // Create a Firebase Storage reference with a unique filename
            String fileName = "profile_image_" + userId + ".jpg";
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("profile_images").child(fileName);

            // Upload the image to Firebase Storage
            UploadTask uploadTask = storageRef.putFile(imageUri);
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                // Image upload successful

                // Get the download URL of the uploaded image
                storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();

                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                    DocumentReference documentRef = firestore.collection("users").document(userId);

                    documentRef.update("profileImageUrl", imageUrl)
                            .addOnSuccessListener(aVoid -> {
                                // Document updated successfully
                                Toast.makeText(ProfileActivity.this, "Document updated successfully", Toast.LENGTH_SHORT).show();



                                documentRef.get().addOnSuccessListener(documentSnapshot -> {
                                    if (documentSnapshot.exists() && documentSnapshot.contains("profileImageUrl")) {
                                        addProfilePic.setVisibility(View.GONE);
                                        Glide.with(this)
                                                .load(imageUrl)
                                                .apply(RequestOptions.circleCropTransform())
                                                .into(profilePic);
                                    } else {
                                        // The user document doesn't exist or doesn't have a profile picture URL
                                    }
                                }).addOnFailureListener(e -> {
                                    // Error retrieving user document
                                });
                            })
                            .addOnFailureListener(e -> {
                                // Error updating document
                                Toast.makeText(ProfileActivity.this, "Failed to update document", Toast.LENGTH_SHORT).show();
                            });


                    Toast.makeText(ProfileActivity.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {
                    // Error getting the download URL
                    Toast.makeText(ProfileActivity.this, "Failed to get download URL", Toast.LENGTH_SHORT).show();
                });
            }).addOnFailureListener(e -> {
                // Image upload failed
                Toast.makeText(ProfileActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
            });


        }
    }

    private void scrollViewLeft() {
        horizontalScrollView.smoothScrollBy(-700, 0);
    }

    private void scrollViewRight() {
        horizontalScrollView.smoothScrollBy(700, 0);
    }

}