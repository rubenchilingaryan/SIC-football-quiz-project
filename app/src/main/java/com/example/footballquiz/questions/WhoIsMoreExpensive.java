package com.example.footballquiz.questions;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballquiz.R;
import com.example.footballquiz.mainActivities.TopBar;
import com.example.footballquiz.questionsMethods.WhoIsMoreExpensiveMethods;

import java.util.Random;

public class WhoIsMoreExpensive extends WhoIsMoreExpensiveMethods {

    ImageView image1,image2,black_screen1,black_screen2;
    TextView player1_price,player2_price,player1_name,player2_name;

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

    int[] prices = new int[]{15,40,50,20,70,170,15,9,5,70,35,180,10,80,60,40,80,60,5,120,50,70};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_is_more_expensive);

        image1 = findViewById(R.id.player1_image_expensive);
        image2 = findViewById(R.id.player2_image_expensive);
        black_screen1 = findViewById(R.id.black_screen1_expensive);
        black_screen2 = findViewById(R.id.black_screen2_expensive);
        player1_price = findViewById(R.id.player1_price);
        player2_price = findViewById(R.id.player2_price);
        player1_name = findViewById(R.id.player1_name_expensive);
        player2_name = findViewById(R.id.player2_name_expensive);

/*        expensivePlayer(image1,image2,black_screen1,black_screen2,player1_name,player2_name,player1_price,
                player2_price,images,player_names,prices,back);*/

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment topBarFragment = new TopBar();
        fragmentTransaction.add(R.id.top_bar_layout, topBarFragment);
        fragmentTransaction.commit();


        Random rand = new Random();
        int indexPic1 = rand.nextInt(images.length);
        int indexPic2 = rand.nextInt(images.length);
        while (true){
            if(indexPic1 == indexPic2 || prices[indexPic1] == prices[indexPic2]) {
                indexPic2 = rand.nextInt(images.length);
            }else{
                break;
            }
        }



        ValueAnimator animator1 = ValueAnimator.ofInt(0,prices[indexPic1]);
//        animator1.setDuration(4000);
        if(prices[indexPic1] > 0 || prices[indexPic1] < 20)
            animator1.setDuration(2000);
        else if(prices[indexPic1] >= 20 || prices[indexPic1] < 40)
            animator1.setDuration(2500);
        else if(prices[indexPic1] >= 40 || prices[indexPic1] < 60)
            animator1.setDuration(3000);
        else if(prices[indexPic1] >= 60 || prices[indexPic1] < 80)
            animator1.setDuration(3500);
        else if(prices[indexPic1] >= 80 || prices[indexPic1] < 100)
            animator1.setDuration(4000);
        else if(prices[indexPic1] >= 100 || prices[indexPic1] < 120)
            animator1.setDuration(4500);
        else if(prices[indexPic1] >= 120 || prices[indexPic1] < 140)
            animator1.setDuration(5000);
        else if(prices[indexPic1] >= 140)
            animator1.setDuration(5500);

        TimeInterpolator interpolator = new DecelerateInterpolator();

        animator1.setInterpolator(interpolator);


        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                player1_price.setText(String.valueOf(animatedValue)  + " mln €");
            }
        });




        ValueAnimator animator2 = ValueAnimator.ofInt(0,prices[indexPic2]);
//        animator2.setDuration(4000);
        if(prices[indexPic2] > 0 || prices[indexPic2] < 20)
            animator2.setDuration(2000);
        else if(prices[indexPic2] >= 20 || prices[indexPic2] < 40)
            animator2.setDuration(2500);
        else if(prices[indexPic2] >= 40 || prices[indexPic2] < 60)
            animator2.setDuration(3000);
        else if(prices[indexPic2] >= 60 || prices[indexPic2] < 80)
            animator2.setDuration(3500);
        else if(prices[indexPic2] >= 80 || prices[indexPic2] < 100)
            animator2.setDuration(4000);
        else if(prices[indexPic2] >= 100 || prices[indexPic2] < 120)
            animator2.setDuration(4500);
        else if(prices[indexPic2] >= 120 || prices[indexPic2] < 140)
            animator2.setDuration(5000);
        else if(prices[indexPic2] >= 140)
            animator2.setDuration(5500);

        TimeInterpolator interpolator2 = new DecelerateInterpolator();

        animator2.setInterpolator(interpolator2);


        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                player2_price.setText(String.valueOf(animatedValue)  + " mln €");
            }
        });

        final Handler handler = new Handler();


        image1.setImageResource(images[indexPic1]);
        image2.setImageResource(images[indexPic2]);
        player1_name.setText(player_names[indexPic1]);
        player2_name.setText(player_names[indexPic2]);
//        player1_price.setText(Integer.toString(prices[indexPic1]) + " mln €");
//        player2_price.setText(Integer.toString(prices[indexPic2]) + " mln €");

        if(prices[indexPic1]>prices[indexPic2]){

            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image1.setClickable(false);
                    image2.setClickable(false);
                    player1_price.setVisibility(View.VISIBLE);
//                    player1_price.setTextColor(Color.parseColor("#0FA80A"));
                    player2_price.setVisibility(View.VISIBLE);
//                    player2_price.setTextColor(Color.parseColor("#CA0616"));
                    black_screen1.setVisibility(View.VISIBLE);
                    black_screen2.setVisibility(View.VISIBLE);
                    ratingIncrease();
                    animator1.start();
                    animator2.start();
                    if(animator1.getDuration() >= animator2.getDuration())
                        animator1.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                player1_price.setTextColor(Color.parseColor("#0FA80A"));
                                player2_price.setTextColor(Color.parseColor("#CA0616"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        nextActivity();
                                    }
                                },500);
                            }
                        });

                    else if(animator2.getDuration() < animator1.getDuration())
                        animator2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                player1_price.setTextColor(Color.parseColor("#0FA80A"));
                                player2_price.setTextColor(Color.parseColor("#CA0616"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        nextActivity();
                                    }
                                },500);
                            }
                        });
                    //                 Intent i = new Intent(getApplicationContext(),Who_is_more_expensive_MidMode.class);
                    //                 startActivity(i);
                    //                 finish();
                }
            });

            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image1.setClickable(false);
                    image2.setClickable(false);
                    player1_price.setVisibility(View.VISIBLE);
//                    player1_price.setTextColor(Color.parseColor("#0FA80A"));
                    player2_price.setVisibility(View.VISIBLE);
//                    player2_price.setTextColor(Color.parseColor("#CA0616"));
                    black_screen1.setVisibility(View.VISIBLE);
                    black_screen2.setVisibility(View.VISIBLE);
                    ratingDecrease();
                    animator1.start();
                    animator2.start();
                    if(animator1.getDuration() >= animator2.getDuration())
                        animator1.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                player1_price.setTextColor(Color.parseColor("#0FA80A"));
                                player2_price.setTextColor(Color.parseColor("#CA0616"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        nextActivity();
                                    }
                                },500);
                            }
                        });

                    else if(animator2.getDuration() < animator1.getDuration())
                        animator2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                player1_price.setTextColor(Color.parseColor("#0FA80A"));
                                player2_price.setTextColor(Color.parseColor("#CA0616"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        nextActivity();
                                    }
                                },500);
                            }
                        });
                    //                Intent i = new Intent(getApplicationContext(),Who_is_more_expensive_MidMode.class);
                    //                startActivity(i);
                    //                finish();
                }
            });
        }else if(prices[indexPic1]<prices[indexPic2]){
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image1.setClickable(false);
                    image2.setClickable(false);
                    player1_price.setVisibility(View.VISIBLE);
//                    player1_price.setTextColor(Color.parseColor("#CA0616"));
                    player2_price.setVisibility(View.VISIBLE);
//                    player2_price.setTextColor(Color.parseColor("#0FA80A"));
                    black_screen1.setVisibility(View.VISIBLE);
                    black_screen2.setVisibility(View.VISIBLE);
                    ratingDecrease();
                    animator1.start();
                    animator2.start();
                    if(animator1.getDuration() >= animator2.getDuration())
                        animator1.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                player1_price.setTextColor(Color.parseColor("#CA0616"));
                                player2_price.setTextColor(Color.parseColor("#0FA80A"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        nextActivity();
                                    }
                                },500);
                            }
                        });

                    else if(animator2.getDuration() < animator1.getDuration())
                        animator2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                player1_price.setTextColor(Color.parseColor("#CA0616"));
                                player2_price.setTextColor(Color.parseColor("#0FA80A"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        nextActivity();
                                    }
                                },500);
                            }
                        });
//                  Intent i = new Intent(getApplicationContext(),Who_is_more_expensive_MidMode.class);
//                  startActivity(i);
//                  finish();
                }
            });

            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image1.setClickable(false);
                    image2.setClickable(false);
                    player1_price.setVisibility(View.VISIBLE);
//                    player1_price.setTextColor(Color.parseColor("#CA0616"));
                    player2_price.setVisibility(View.VISIBLE);
//                    player2_price.setTextColor(Color.parseColor("#0FA80A"));
                    black_screen1.setVisibility(View.VISIBLE);
                    black_screen2.setVisibility(View.VISIBLE);
                    animator1.start();
                    animator2.start();
                    ratingIncrease();
                    if(animator1.getDuration() >= animator2.getDuration())
                        animator1.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                player1_price.setTextColor(Color.parseColor("#CA0616"));
                                player2_price.setTextColor(Color.parseColor("#0FA80A"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        nextActivity();
                                    }
                                },500);
                            }
                        });

                    else if(animator2.getDuration() < animator1.getDuration())
                        animator2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                player1_price.setTextColor(Color.parseColor("#CA0616"));
                                player2_price.setTextColor(Color.parseColor("#0FA80A"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        nextActivity();
                                    }
                                },500);
                            }
                        });
//                  Intent i = new Intent(getApplicationContext(),Who_is_more_expensive_MidMode.class);
//                  startActivity(i);
//                  finish();
                }
            });

        }
    }
}