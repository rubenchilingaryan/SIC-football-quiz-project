package com.example.footballquiz.questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballquiz.mainActivities.MainMenu;
import com.example.footballquiz.R;
import com.example.footballquiz.questionsMethods.Guess_the_price_Methods;
import com.google.android.material.slider.Slider;

import java.util.Random;

public class Guess_the_price extends Guess_the_price_Methods {

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
    AppCompatButton back;

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
        back = findViewById(R.id.button_back_guess_the_price);

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
                    showPopUpDialogGuessThePriceIncrease();
                }else{
                    wrong_anim.setVisibility(View.VISIBLE);
                    black_screen.setVisibility(View.VISIBLE);
                    real_price.setText(Integer.toString(prices[index]) + " mln €");
                    real_price.setBackgroundColor(Color.RED);
                    showPopUpDialogGuessThePriceDecrease();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void showPopUpDialog() {
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
                Intent i = new Intent(getApplicationContext(),Guess_the_price.class);
                startActivity(i);
                finish();
            }
        });

        // show the dialog box
        dialog.show();
    }

}