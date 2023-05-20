package com.example.footballquiz.questions;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.footballquiz.mainActivities.MainMenu;
import com.example.footballquiz.R;

import java.util.Random;

public class Guess_the_player extends AppCompatActivity {

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_player);

        ImageView image = findViewById(R.id.image_guess_the_player);
        ImageView black_screen = findViewById(R.id.black_screen_guess_the_player);
        TextView ans1 = findViewById(R.id.answer1_guess_the_player);
        TextView ans2 = findViewById(R.id.answer2_guess_the_player);
        TextView ans3 = findViewById(R.id.answer3_guess_the_player);
        TextView ans4 = findViewById(R.id.answer4_guess_the_player);
        TextView wrong_anim = findViewById(R.id.wrong_answer_guess_the_player);
        TextView right_anim = findViewById(R.id.right_answer_guess_the_player);
        TextView[] answers = {ans1, ans2, ans3, ans4};
        AppCompatButton back = findViewById(R.id.button_back_guess_the_player);

        int[] images = new int[]{R.drawable.adama_traore,R.drawable.antonio_rudiger,R.drawable.casemiro,
                R.drawable.cristiano_ronaldo,R.drawable.darwin_nunez,R.drawable.erling_haaland,
                R.drawable.hector_bellerin,R.drawable.james_rodriguez,R.drawable.jordi_alba,
                R.drawable.kai_havertz,R.drawable.karim_benzema,R.drawable.kylian_mbappe,
                R.drawable.luka_modric,R.drawable.mohammed_salah,R.drawable.ousmane_dembele,
                R.drawable.raphael_varane,R.drawable.rodrygo,R.drawable.ronald_araujo,
                R.drawable.sergio_busquets,R.drawable.vinicius_junior,R.drawable.virgil_van_dijk,R.drawable.raheem_sterling};

        String[] names = new String[]{"Adama Traore","Antonio Rüdiger","Casemiro",
                "Cristiano Ronaldo","Darwin Núñez","Erling Haaland","Hector Bellerin","James Rodríguez",
                "Jordi Alba","Kai Havertz","Karim Benzema","Kylian Mbappé","Luka Modrić","Mohamed Salah",
                "Ousmane Dembélé","Raphaël Varane","Rodrygo","Ronald Araújo","Sergio Busquets",
                "Vinícius Júnior","Virgil van Dijk","Raheem Sterling"};

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(i);
                finish();
            }
        });

        Random rand = new Random();

        int[] check;
        check = new int[names.length];

        int indexPic = rand.nextInt(images.length);
        check[0] = indexPic;
        int indexAns = rand.nextInt(4);
        int img = images[indexPic];
        image.setImageResource(img);
        answers[indexAns].setText(Integer.toString(indexAns + 1) + ". " + names[indexPic]);

        int i = 0;
        while (i < 4) {
            if (i != indexAns) {
                int indexPic2 = rand.nextInt(names.length);
                Boolean test = true;
                for (int j = 0; j < check.length; j++) {
                    if (indexPic2 == check[j]) {
                        test = false;
                        break;
                    }
                }
                if (test) {
                    check[i + 1] = indexPic2;
                    answers[i].setText(Integer.toString(i + 1) + ". " + names[indexPic2]);
                    i++;
                }
            } else {
                i++;
            }
        }

        for (i = 0; i < 4; i++) {
            if (i != indexAns) {
                int finalI = i;
                answers[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wrong_anim.setVisibility(View.VISIBLE);
                        answers[indexAns].setTextColor(Color.GREEN);
                        answers[finalI].setTextColor(Color.RED);
                        black_screen.setVisibility(View.VISIBLE);
                        showPopUpDialog();
//                      Intent i = new Intent(Guess_the_player.this, Guess_the_player_MidMode.class);
//                      startActivity(i);
//                      finish();
                    }
                });
            }
            else if(i == indexAns){
                answers[indexAns].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        right_anim.setVisibility(View.VISIBLE);
                        answers[indexAns].setTextColor(Color.GREEN);
                        black_screen.setVisibility(View.VISIBLE);
                        showPopUpDialog();
//                       Intent i = new Intent(Guess_the_player.this, Guess_the_player_MidMode.class);
//                       startActivity(i);
//                       finish();
                    }
                });
            }
        }

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
                Intent i = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(i);
                finish();
            }
        });

        // set the click listener for the next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(),Guess_the_player.class);
                startActivity(i);
                finish();
            }
        });

        // show the dialog box
        dialog.show();
    }
}