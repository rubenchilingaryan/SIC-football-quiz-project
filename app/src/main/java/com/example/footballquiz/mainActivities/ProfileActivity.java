package com.example.footballquiz.mainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.footballquiz.R;

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


    }
}