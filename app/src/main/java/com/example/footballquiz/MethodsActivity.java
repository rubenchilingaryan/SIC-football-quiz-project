package com.example.footballquiz;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.footballquiz.questions.Who_is_faster;
import com.example.footballquiz.questions.Who_is_more_expensive;

import java.util.Random;

public class MethodsActivity extends AppCompatActivity {

    protected void showPopUpDialogFaster() {
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
        int ELO = 1000;
        eloTextView.setText(Integer.toString(ELO));
        Random random = new Random();
        int RANDOM_ADDED_RATING = 20 + random.nextInt(11);

        ValueAnimator animator = ValueAnimator.ofInt(ELO,ELO + RANDOM_ADDED_RATING);
        animator.setDuration(4000);

        TimeInterpolator interpolator = new DecelerateInterpolator();

        animator.setInterpolator(interpolator);


        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                eloTextView.setText("Your new ELO: " + String.valueOf(animatedValue));
            }
        });

        animator.start();

        // set the click listener for the return button
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(i);
                finish();
            }
        });

        // set the click listener for the next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(), Who_is_faster.class);
                startActivity(i);
                finish();
            }
        });

        // show the dialog box
        dialog.show();
    }

    protected void showPopUpDialogMoreExpensive() {
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
        int ELO = 1000;
        eloTextView.setText(Integer.toString(ELO));
        Random random = new Random();
        int RANDOM_ADDED_RATING = 20 + random.nextInt(11);

        ValueAnimator animator = ValueAnimator.ofInt(ELO,ELO + RANDOM_ADDED_RATING);
        animator.setDuration(4000);

        TimeInterpolator interpolator = new DecelerateInterpolator();

        animator.setInterpolator(interpolator);


        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                eloTextView.setText("Your new ELO: " + String.valueOf(animatedValue));
            }
        });

        animator.start();

        // set the click listener for the return button
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(i);
                finish();
            }
        });

        // set the click listener for the next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(), Who_is_more_expensive.class);
                startActivity(i);
                finish();
            }
        });

        // show the dialog box
        dialog.show();
    }
}
