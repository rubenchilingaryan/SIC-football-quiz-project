package com.example.footballquiz.mainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballquiz.R;
import com.example.footballquiz.authorization.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        ImageView signOut = findViewById(R.id.btnSignOut);

        // Firebase

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentRef = firestore.collection("users").document(userId);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

    }
}