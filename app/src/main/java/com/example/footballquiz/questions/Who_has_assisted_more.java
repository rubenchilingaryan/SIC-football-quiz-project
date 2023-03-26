package com.example.footballquiz.questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballquiz.MainMenu;
import com.example.footballquiz.R;

import java.util.Random;

public class Who_has_assisted_more extends AppCompatActivity {

    ImageView image1, image2, black_screen1, black_screen2;
    TextView player1_name, player2_name, player1_assists, player2_assists, back;

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

    int[] assists = new int[]{58, 13, 48, 225, 24, 41, 41, 147, 107, 50, 191, 114, 122, 127, 68, 8, 34, 7,
            44, 59, 23, 133};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_has_assisted_more);

        image1 = findViewById(R.id.player1_image_assisted_more);
        image2 = findViewById(R.id.player2_image_assisted_more);
        black_screen1 = findViewById(R.id.black_screen1_assisted_more);
        black_screen2 = findViewById(R.id.black_screen2_assisted_more);
        player1_name = findViewById(R.id.player1_name_assisted_more);
        player2_name = findViewById(R.id.player2_name_assisted_more);
        player1_assists = findViewById(R.id.player1_assists);
        player2_assists = findViewById(R.id.player2_assists);
        back = findViewById(R.id.backButton_who_has_assisted_more);


        Random rand = new Random();
        int indexPic1 = rand.nextInt(images.length);
        int indexPic2 = rand.nextInt(images.length);
        while (true) {
            if (indexPic1 == indexPic2 || assists[indexPic1] == assists[indexPic2]) {
                indexPic2 = rand.nextInt(images.length);
            } else {
                break;
            }
        }

        ValueAnimator animator1 = ValueAnimator.ofInt(0, assists[indexPic1]);
        animator1.setDuration(4000);

        TimeInterpolator interpolator = new DecelerateInterpolator();

        animator1.setInterpolator(interpolator);


        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                player1_assists.setText(String.valueOf(animatedValue) + " Assists");
            }
        });


        ValueAnimator animator2 = ValueAnimator.ofInt(0, assists[indexPic2]);
        animator2.setDuration(4000);

        TimeInterpolator interpolator2 = new DecelerateInterpolator();

        animator2.setInterpolator(interpolator2);


        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                player2_assists.setText(String.valueOf(animatedValue) + " Assists");
            }
        });

        final Handler handler = new Handler();

        image1.setImageResource(images[indexPic1]);
        image2.setImageResource(images[indexPic2]);
        player1_name.setText(player_names[indexPic1]);
        player2_name.setText(player_names[indexPic2]);
//        player1_Assists.setText(Integer.toString(Assists[indexPic1]) + " Assists");
//        player2_Assists.setText(Integer.toString(Assists[indexPic2]) + " Assists");

        if (assists[indexPic1] > assists[indexPic2]) {

            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image1.setClickable(false);
                    image2.setClickable(false);
                    back.setClickable(false);
                    player1_assists.setVisibility(View.VISIBLE);
                    //                   player1_Assists.setTextColor(Color.parseColor("#0FA80A"));
                    player2_assists.setVisibility(View.VISIBLE);
                    //                   player2_Assists.setTextColor(Color.parseColor("#CA0616"));
                    black_screen1.setVisibility(View.VISIBLE);
                    black_screen2.setVisibility(View.VISIBLE);
                    animator1.start();
                    animator2.start();
                    animator1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            player1_assists.setTextColor(Color.parseColor("#0FA80A"));
                            player2_assists.setTextColor(Color.parseColor("#CA0616"));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(getApplicationContext(), Who_has_assisted_more.class);
                                    startActivity(i);
                                    finish();
                                }
                            }, 1000);
                        }
                    });
//                   Intent i = new Intent(getApplicationContext(), Who_has_scored_more_MidMode.class);
//                   startActivity(i);
//                   finish();
                }
            });

            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image1.setClickable(false);
                    image2.setClickable(false);
                    back.setClickable(false);
                    player1_assists.setVisibility(View.VISIBLE);
//                    player1_Assists.setTextColor(Color.parseColor("#0FA80A"));
                    player2_assists.setVisibility(View.VISIBLE);
//                    player2_Assists.setTextColor(Color.parseColor("#CA0616"));
                    black_screen1.setVisibility(View.VISIBLE);
                    black_screen2.setVisibility(View.VISIBLE);
                    animator1.start();
                    animator2.start();
                    animator1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            player1_assists.setTextColor(Color.parseColor("#0FA80A"));
                            player2_assists.setTextColor(Color.parseColor("#CA0616"));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(getApplicationContext(), Who_has_assisted_more.class);
                                    startActivity(i);
                                    finish();
                                }
                            }, 1000);
                        }
                    });
//                  Intent i = new Intent(getApplicationContext(),Who_has_scored_more_MidMode.class);
//                  startActivity(i);
//                  finish();
                }
            });
        } else if (assists[indexPic1] < assists[indexPic2]) {
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image1.setClickable(false);
                    image2.setClickable(false);
                    back.setClickable(false);
                    player1_assists.setVisibility(View.VISIBLE);
                    //                   player1_Assists.setTextColor(Color.parseColor("#CA0616"));
                    player2_assists.setVisibility(View.VISIBLE);
//                    player2_Assists.setTextColor(Color.parseColor("#0FA80A"));
                    black_screen1.setVisibility(View.VISIBLE);
                    black_screen2.setVisibility(View.VISIBLE);
                    animator1.start();
                    animator2.start();
                    animator1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            player1_assists.setTextColor(Color.parseColor("#CA0616"));
                            player2_assists.setTextColor(Color.parseColor("#0FA80A"));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(getApplicationContext(), Who_has_assisted_more.class);
                                    startActivity(i);
                                    finish();
                                }
                            }, 1000);
                        }
                    });
                    //                  Intent i = new Intent(getApplicationContext(),Who_has_scored_more_MidMode.class);
                    //                  startActivity(i);
                    //                  finish();
                }
            });

            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image1.setClickable(false);
                    image2.setClickable(false);
                    back.setClickable(false);
                    player1_assists.setVisibility(View.VISIBLE);
                    //                   player1_Assists.setTextColor(Color.parseColor("#CA0616"));
                    player2_assists.setVisibility(View.VISIBLE);
                    //                   player2_Assists.setTextColor(Color.parseColor("#0FA80A"));
                    black_screen1.setVisibility(View.VISIBLE);
                    black_screen2.setVisibility(View.VISIBLE);
                    animator1.start();
                    animator2.start();
                    animator1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            player1_assists.setTextColor(Color.parseColor("#CA0616"));
                            player2_assists.setTextColor(Color.parseColor("#0FA80A"));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(getApplicationContext(), Who_has_assisted_more.class);
                                    startActivity(i);
                                    finish();
                                }
                            }, 1000);
                        }
                    });
//                   Intent i = new Intent(getApplicationContext(),Who_has_scored_more_MidMode.class);
//                   startActivity(i);
//                   finish();
                }
            });
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(i);
                    finish();
                }
            });

        }
    }
}