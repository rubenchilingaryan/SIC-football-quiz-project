package com.example.footballquiz.questions;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballquiz.mainActivities.MainMenu;
import com.example.footballquiz.R;
import com.example.footballquiz.mainActivities.TopBar;
import com.example.footballquiz.questionsMethods.GuessThePriceMethods;
import com.google.android.material.slider.Slider;

import java.util.Random;

public class GuessThePrice extends GuessThePriceMethods {

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

    ImageView image,black_screen;
    TextView player_name,price,submit,right_anim,wrong_anim,real_price;
    Slider slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_price);

        image = findViewById(R.id.image_guess_the_price);
        black_screen = findViewById(R.id.black_screen_guess_the_price);
        price = findViewById(R.id.guessed_price);
        player_name = findViewById(R.id.player_name_guess_the_price);
        submit = findViewById(R.id.submit_button);
        right_anim = findViewById(R.id.right_answer_guess_the_price);
        wrong_anim = findViewById(R.id.wrong_answer_guess_the_price);
        real_price = findViewById(R.id.real_price_guess_the_price);
        slider = findViewById(R.id.slider_guess_the_power);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment topBarFragment = new TopBar();
        fragmentTransaction.add(R.id.top_bar_layout, topBarFragment);
        fragmentTransaction.commit();

        Random random = new Random();
        int index = random.nextInt(images.length);
        image.setImageResource(images[index]);
        player_name.setText(player_names[index]);

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                price.setText(String.valueOf(value).substring(0,String.valueOf(value).length()-2) + " mln €");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = slider.getValue();
                if(Math.abs(prices[index] - x) <= 5){
                    right_anim.setVisibility(View.VISIBLE);
                    black_screen.setVisibility(View.VISIBLE);
                    real_price.setText(Integer.toString(prices[index]) + " mln €");
                    real_price.setBackgroundColor(Color.GREEN);
                    ratingIncrease();
                }else{
                    wrong_anim.setVisibility(View.VISIBLE);
                    black_screen.setVisibility(View.VISIBLE);
                    real_price.setText(Integer.toString(prices[index]) + " mln €");
                    real_price.setBackgroundColor(Color.RED);
                    ratingDecrease();
                }

            }
        });



    }
}