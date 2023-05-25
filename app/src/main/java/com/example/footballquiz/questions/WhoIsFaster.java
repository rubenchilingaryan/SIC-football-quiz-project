package com.example.footballquiz.questions;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.footballquiz.mainActivities.MainMenu;
import com.example.footballquiz.R;
import com.example.footballquiz.mainActivities.TopBar;
import com.example.footballquiz.questionsMethods.WhoIsFasterMethods;

import java.util.Random;

public class WhoIsFaster extends WhoIsFasterMethods {


    ImageView image1, image2, image1_black, image2_black;
    TextView player1_speed, player2_speed, player1_name, player2_name;

    int[] images = new int[]{R.drawable.adama_traore, R.drawable.antonio_rudiger, R.drawable.casemiro,
            R.drawable.cristiano_ronaldo, R.drawable.darwin_nunez, R.drawable.erling_haaland,
            R.drawable.hector_bellerin, R.drawable.james_rodriguez, R.drawable.jordi_alba,
            R.drawable.kai_havertz, R.drawable.karim_benzema, R.drawable.kylian_mbappe,
            R.drawable.luka_modric, R.drawable.mohammed_salah, R.drawable.ousmane_dembele,
            R.drawable.raphael_varane, R.drawable.rodrygo, R.drawable.ronald_araujo,
            R.drawable.sergio_busquets, R.drawable.vinicius_junior, R.drawable.virgil_van_dijk, R.drawable.raheem_sterling};

    String[] player_names = new String[]{"Adama Traore", "Antonio Rüdiger", "Casemiro",
            "Cristiano Ronaldo", "Darwin Núñez", "Erling Haaland", "Hector Bellerin", "James Rodríguez",
            "Jordi Alba", "Kai Havertz", "Karim Benzema", "Kylian Mbappé", "Luka Modrić", "Mohamed Salah",
            "Ousmane Dembélé", "Raphaël Varane", "Rodrygo", "Ronald Araújo", "Sergio Busquets",
            "Vinícius Júnior", "Virgil van Dijk", "Raheem Sterling"};

    double[] speeds = new double[]{36.6, 34.5, 30.6, 34.2, 38.0, 36.04, 34.99, 26.1, 32.6, 32.4, 32.0,
            37.9, 30.9, 33.9, 36.6, 32.71, 34.0, 31.8, 30.0, 35.4, 33.3, 33.8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_is_faster);


        image1 = findViewById(R.id.player1_image_fast);
        image1_black = findViewById(R.id.black_screen1_faster);
        image2 = findViewById(R.id.player2_image_fast);
        image2_black = findViewById(R.id.black_screen2_faster);
        player1_name = findViewById(R.id.player1_name_fast);
        player2_name = findViewById(R.id.player2_name_fast);
        player1_speed = findViewById(R.id.player1_speed);
        player2_speed = findViewById(R.id.player2_speed);




        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment topBarFragment = new TopBar();
        fragmentTransaction.add(R.id.top_bar_layout, topBarFragment);
        fragmentTransaction.commit();


        Random rand = new Random();
        int indexPic1 = rand.nextInt(images.length);
        int indexPic2 = rand.nextInt(images.length);
        while (true) {
            if (indexPic1 == indexPic2 || speeds[indexPic1] == speeds[indexPic2]) {
                indexPic2 = rand.nextInt(images.length);
            } else {
                break;
            }
        }

        player1_name.setText(player_names[indexPic1]);
        player2_name.setText(player_names[indexPic2]);
        image1.setImageResource(images[indexPic1]);
        image2.setImageResource(images[indexPic2]);
        player1_speed.setText(Double.toString(speeds[indexPic1]) + " km/h");
        player2_speed.setText(Double.toString(speeds[indexPic2]) + " km/h");

// Create the animators
        ValueAnimator animator1 = ValueAnimator.ofFloat(20f, (float) speeds[indexPic1]);
        ValueAnimator animator2 = ValueAnimator.ofFloat(20f, (float) speeds[indexPic2]);
        animator1.setDuration(2000); // Animation duration in milliseconds
        animator2.setDuration(2000); // Animation duration in milliseconds

        TimeInterpolator interpolator = new DecelerateInterpolator(); // Use any interpolator of your choice
        animator1.setInterpolator(interpolator);
        animator2.setInterpolator(interpolator);

        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                player1_speed.setText(String.format("%.2f", value) + " km/h");
            }
        });

        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                player2_speed.setText(String.format("%.2f", value) + " km/h");
            }
        });

        Handler handler = new Handler();

        if (speeds[indexPic1] > speeds[indexPic2]) {
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player1_speed.setVisibility(View.VISIBLE);
                    player1_speed.setTextColor(Color.parseColor("#0FA80A"));
                    player2_speed.setVisibility(View.VISIBLE);
                    player2_speed.setTextColor(Color.parseColor("#CA0616"));
                    image1_black.setVisibility(View.VISIBLE);
                    image2_black.setVisibility(View.VISIBLE);

                    // Start the animation for player1_speed
                    animator1.start();

                    // Start the animation for player2_speed
                    animator2.start();

                    // Execute ratingIncrease() after the animation duration
                    animator1.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            // Execute ratingIncrease() after the animation duration
                            ratingIncrease();
                        }

                        @Override
                        public void onAnimationCancel(@NonNull Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(@NonNull Animator animation) {

                        }
                    });

                    image2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            player1_speed.setVisibility(View.VISIBLE);
                            player1_speed.setTextColor(Color.parseColor("#0FA80A"));
                            player2_speed.setVisibility(View.VISIBLE);
                            player2_speed.setTextColor(Color.parseColor("#CA0616"));
                            image1_black.setVisibility(View.VISIBLE);
                            image2_black.setVisibility(View.VISIBLE);

                            // Start the animation for player1_speed
                            animator1.start();

                            // Start the animation for player2_speed
                            animator2.start();

                            // Execute ratingDecrease() after the animation duration
                            animator1.addListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    // Execute ratingIncrease() after the animation duration
                                    ratingDecrease();
                                }

                                @Override
                                public void onAnimationCancel(@NonNull Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(@NonNull Animator animation) {

                                }
                            });

                            // ...
                        }
                    });
                }
            });

            // ...
        } else if (speeds[indexPic1] < speeds[indexPic2]) {
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player1_speed.setVisibility(View.VISIBLE);
                    player1_speed.setTextColor(Color.parseColor("#CA0616"));
                    player2_speed.setVisibility(View.VISIBLE);
                    player2_speed.setTextColor(Color.parseColor("#0FA80A"));
                    image1_black.setVisibility(View.VISIBLE);
                    image2_black.setVisibility(View.VISIBLE);

                    // Start the animation for player1_speed
                    animator1.start();

                    // Start the animation for player2_speed
                    animator2.start();

                    // Execute ratingDecrease() after the animation duration
                    animator1.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            // Execute ratingIncrease() after the animation duration
                            ratingDecrease();
                        }

                        @Override
                        public void onAnimationCancel(@NonNull Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(@NonNull Animator animation) {

                        }
                    });

                    // ...
                }
            });

            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player1_speed.setVisibility(View.VISIBLE);
                    player1_speed.setTextColor(Color.parseColor("#CA0616"));
                    player2_speed.setVisibility(View.VISIBLE);
                    player2_speed.setTextColor(Color.parseColor("#0FA80A"));
                    image1_black.setVisibility(View.VISIBLE);
                    image2_black.setVisibility(View.VISIBLE);

                    // Start the animation for player1_speed
                    animator1.start();

                    // Start the animation for player2_speed
                    animator2.start();

                    // Execute ratingIncrease() after the animation duration
                    animator1.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            // Execute ratingIncrease() after the animation duration
                            ratingIncrease();
                        }

                        @Override
                        public void onAnimationCancel(@NonNull Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(@NonNull Animator animation) {

                        }
                    });

                    // ...
                }
            });

            // ...
        }
    }
}