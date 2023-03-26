package com.example.footballquiz.questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.footballquiz.MainMenu;
import com.example.footballquiz.R;

import java.util.Random;

public class Who_has_scored_more_MidMode extends AppCompatActivity {

    Button return_button,next_button;
    TextView rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        setContentView(R.layout.activity_who_has_scored_more_mid_mode);

        return_button = findViewById(R.id.return_button_scored_more);
        next_button = findViewById(R.id.next_button_scored_more);
        rating = findViewById(R.id.rating_who_has_scored_more);

        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(i);
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Who_has_scored_more.class);
                startActivity(i);
            }
        });

        int ELO = 1000;
        rating.setText(Integer.toString(ELO));
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
                rating.setText(String.valueOf(animatedValue));
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Intent i = new Intent(getApplicationContext(),Who_has_scored_more.class);
                startActivity(i);
                finish();
            }
        });

        animator.start();
    }
}