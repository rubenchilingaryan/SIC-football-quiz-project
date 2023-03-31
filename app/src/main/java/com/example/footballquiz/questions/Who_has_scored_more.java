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
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballquiz.MainMenu;
import com.example.footballquiz.MethodsActivity;
import com.example.footballquiz.R;

import java.util.Random;

public class Who_has_scored_more extends MethodsActivity {

    ImageView image1,image2,black_screen1,black_screen2;
    TextView player1_name,player2_name,player1_goals,player2_goals,back;

    int[] images = new int[]{R.drawable.adama_traore,R.drawable.antonio_rudiger,R.drawable.casemiro,
            R.drawable.cristiano_ronaldo,R.drawable.darwin_nunez,R.drawable.erling_haaland,
            R.drawable.hector_bellerin,R.drawable.james_rodriguez,R.drawable.jordi_alba,
            R.drawable.kai_havertz,R.drawable.karim_benzema,R.drawable.kylian_mbappe,
            R.drawable.luka_modric,R.drawable.mohammed_salah,R.drawable.ousmane_dembele,
            R.drawable.raphael_varane,R.drawable.rodrygo,R.drawable.ronald_araujo,
            R.drawable.sergio_busquets,R.drawable.vinicius_junior,R.drawable.virgil_van_dijk,R.drawable.raheem_sterling};

    String[] player_names = new String[]{"Adama Traore","Antonio Rüdiger","Casemiro",
            "Cristiano Ronaldo","Darwin Núñez","Erling Haaland","Hector Bellerin","James Rodríguez",
            "Jordi Alba","Kai Havertz","Karim Benzema","Kylian Mbappé","Luka Modrić","Mohamed Salah",
            "Ousmane Dembélé","Raphaël Varane","Rodrygo","Ronald Araújo","Sergio Busquets",
            "Vinícius Júnior","Virgil van Dijk","Raheem Sterling"};

    int[] goals = new int[]{30,26,47,706,78,166,14,118,36,103,403,228,75,250,62,20,40,19,18,66,48,168};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_has_scored_more);

        image1 = findViewById(R.id.player1_image_scored_more);
        image2 = findViewById(R.id.player2_image_scored_more);
        black_screen1 = findViewById(R.id.black_screen1_scored_more);
        black_screen2 = findViewById(R.id.black_screen2_scored_more);
        player1_name = findViewById(R.id.player1_name_scored_more);
        player2_name = findViewById(R.id.player2_name_scored_more);
        player1_goals = findViewById(R.id.player1_goals);
        player2_goals = findViewById(R.id.player2_goals);
        back = findViewById(R.id.backButton_who_has_scored_more);

/*        scoredMore(image1,image2,black_screen1,black_screen2,player1_name,player2_name,player1_goals,
                player2_goals,images,player_names,goals,back); */

        Random rand = new Random();
        int indexPic1 = rand.nextInt(images.length);
        int indexPic2 = rand.nextInt(images.length);
        while (true){
            if(indexPic1 == indexPic2 || goals[indexPic1] == goals[indexPic2]) {
                indexPic2 = rand.nextInt(images.length);
            }else{
                break;
            }
        }

        ValueAnimator animator1 = ValueAnimator.ofInt(0,goals[indexPic1]);
//        animator1.setDuration(4000);
        if(goals[indexPic1] > 0 || goals[indexPic1] < 50)
            animator1.setDuration(2000);
        else if(goals[indexPic1] >= 50 || goals[indexPic1] < 100)
            animator1.setDuration(2500);
        else if(goals[indexPic1] >= 100 || goals[indexPic1] < 150)
            animator1.setDuration(3000);
        else if(goals[indexPic1] >= 150 || goals[indexPic1] < 200)
            animator1.setDuration(3500);
        else if(goals[indexPic1] >= 200 || goals[indexPic1] < 250)
            animator1.setDuration(4000);
        else if(goals[indexPic1] >= 250 || goals[indexPic1] < 300)
            animator1.setDuration(4500);
        else if(goals[indexPic1] >= 300 || goals[indexPic1] < 350)
            animator1.setDuration(5000);
        else if(goals[indexPic1] >= 350)
            animator1.setDuration(5500);

        TimeInterpolator interpolator = new DecelerateInterpolator();

        animator1.setInterpolator(interpolator);


        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                player1_goals.setText(String.valueOf(animatedValue)  + " Goals");
            }
        });




        ValueAnimator animator2 = ValueAnimator.ofInt(0,goals[indexPic2]);
//        animator2.setDuration(4000);
        if(goals[indexPic2] > 0 || goals[indexPic2] < 50)
            animator2.setDuration(2000);
        else if(goals[indexPic2] >= 50 || goals[indexPic2] < 100)
            animator2.setDuration(2500);
        else if(goals[indexPic2] >= 100 || goals[indexPic2] < 150)
            animator2.setDuration(3000);
        else if(goals[indexPic2] >= 150 || goals[indexPic2] < 200)
            animator2.setDuration(3500);
        else if(goals[indexPic2] >= 200 || goals[indexPic2] < 250)
            animator2.setDuration(4000);
        else if(goals[indexPic2] >= 250 || goals[indexPic2] < 300)
            animator2.setDuration(4500);
        else if(goals[indexPic2] >= 300 || goals[indexPic2] < 350)
            animator2.setDuration(5000);
        else if(goals[indexPic2] >= 350)
            animator2.setDuration(5500);

        TimeInterpolator interpolator2 = new DecelerateInterpolator();

        animator2.setInterpolator(interpolator2);


        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                player2_goals.setText(String.valueOf(animatedValue)  + " Goals");
            }
        });

        final Handler handler = new Handler();

        image1.setImageResource(images[indexPic1]);
        image2.setImageResource(images[indexPic2]);
        player1_name.setText(player_names[indexPic1]);
        player2_name.setText(player_names[indexPic2]);
//        player1_goals.setText(Integer.toString(goals[indexPic1]) + " Goals");
//        player2_goals.setText(Integer.toString(goals[indexPic2]) + " Goals");

        if(goals[indexPic1]>goals[indexPic2]){

            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image1.setClickable(false);
                    image2.setClickable(false);
                    back.setClickable(false);
                    player1_goals.setVisibility(View.VISIBLE);
                    //                   player1_goals.setTextColor(Color.parseColor("#0FA80A"));
                    player2_goals.setVisibility(View.VISIBLE);
                    //                   player2_goals.setTextColor(Color.parseColor("#CA0616"));
                    black_screen1.setVisibility(View.VISIBLE);
                    black_screen2.setVisibility(View.VISIBLE);
                    animator1.start();
                    animator2.start();
                    if(animator1.getDuration() >= animator2.getDuration())
                    animator1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            player1_goals.setTextColor(Color.parseColor("#0FA80A"));
                            player2_goals.setTextColor(Color.parseColor("#CA0616"));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(getApplicationContext(), Who_has_scored_more.class);
                                    startActivity(i);
                                    finish();
                                }
                            },1000);
                        }
                    });

                    else if(animator1.getDuration() < animator2.getDuration())
                        animator2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                player1_goals.setTextColor(Color.parseColor("#0FA80A"));
                                player2_goals.setTextColor(Color.parseColor("#CA0616"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent(getApplicationContext(), Who_has_scored_more.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
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
                    player1_goals.setVisibility(View.VISIBLE);
//                    player1_goals.setTextColor(Color.parseColor("#0FA80A"));
                    player2_goals.setVisibility(View.VISIBLE);
//                    player2_goals.setTextColor(Color.parseColor("#CA0616"));
                    black_screen1.setVisibility(View.VISIBLE);
                    black_screen2.setVisibility(View.VISIBLE);
                    animator1.start();
                    animator2.start();
                    if(animator1.getDuration() >= animator2.getDuration())
                    animator1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            player1_goals.setTextColor(Color.parseColor("#0FA80A"));
                            player2_goals.setTextColor(Color.parseColor("#CA0616"));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(getApplicationContext(), Who_has_scored_more.class);
                                    startActivity(i);
                                    finish();
                                }
                            },1000);
                        }
                    });
                    else if(animator1.getDuration() < animator2.getDuration())
                        animator2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                player1_goals.setTextColor(Color.parseColor("#0FA80A"));
                                player2_goals.setTextColor(Color.parseColor("#CA0616"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent(getApplicationContext(), Who_has_scored_more.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
                            }
                        });
//                  Intent i = new Intent(getApplicationContext(),Who_has_scored_more_MidMode.class);
//                  startActivity(i);
//                  finish();
                }
            });
        }else if(goals[indexPic1]<goals[indexPic2]){
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image1.setClickable(false);
                    image2.setClickable(false);
                    back.setClickable(false);
                    player1_goals.setVisibility(View.VISIBLE);
                    //                   player1_goals.setTextColor(Color.parseColor("#CA0616"));
                    player2_goals.setVisibility(View.VISIBLE);
//                    player2_goals.setTextColor(Color.parseColor("#0FA80A"));
                    black_screen1.setVisibility(View.VISIBLE);
                    black_screen2.setVisibility(View.VISIBLE);
                    animator1.start();
                    animator2.start();
                    if(animator1.getDuration() >= animator2.getDuration())
                    animator1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            player1_goals.setTextColor(Color.parseColor("#CA0616"));
                            player2_goals.setTextColor(Color.parseColor("#0FA80A"));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(getApplicationContext(), Who_has_scored_more.class);
                                    startActivity(i);
                                    finish();
                                }
                            },1000);
                        }
                    });
                    else if(animator1.getDuration() < animator2.getDuration())
                        animator2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                player1_goals.setTextColor(Color.parseColor("#CA0616"));
                                player2_goals.setTextColor(Color.parseColor("#0FA80A"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent(getApplicationContext(), Who_has_scored_more.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
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
                    player1_goals.setVisibility(View.VISIBLE);
                    //                   player1_goals.setTextColor(Color.parseColor("#CA0616"));
                    player2_goals.setVisibility(View.VISIBLE);
                    //                   player2_goals.setTextColor(Color.parseColor("#0FA80A"));
                    black_screen1.setVisibility(View.VISIBLE);
                    black_screen2.setVisibility(View.VISIBLE);
                    animator1.start();
                    animator2.start();
                    if(animator1.getDuration() >= animator2.getDuration())
                    animator1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            player1_goals.setTextColor(Color.parseColor("#CA0616"));
                            player2_goals.setTextColor(Color.parseColor("#0FA80A"));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(getApplicationContext(), Who_has_scored_more.class);
                                    startActivity(i);
                                    finish();
                                }
                            },1000);
                        }
                    });
                    else if(animator1.getDuration() < animator2.getDuration())
                        animator2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                player1_goals.setTextColor(Color.parseColor("#CA0616"));
                                player2_goals.setTextColor(Color.parseColor("#0FA80A"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent(getApplicationContext(), Who_has_scored_more.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
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