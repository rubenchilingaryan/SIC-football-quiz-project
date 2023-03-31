package com.example.footballquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.footballquiz.ratings.RatingsActivity;

public class StartActivity extends AppCompatActivity {

// activity views

    Button start_button;
    TextView ratings,daily_mode;

// dialog views

    Button first_team,draw,second_team;
    TextView first_team_name,second_team_name;
    ImageView first_team_image,second_team_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

// activity views

        start_button = findViewById(R.id.start_button);
        ratings = findViewById(R.id.ratings);
        daily_mode = findViewById(R.id.daily_mode);

// dialog views

        showDailyMode();

        daily_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDailyMode();
            }
        });

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(i);
            }
        });

        ratings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RatingsActivity.class);
                startActivity(i);
            }
        });
    }

    void showDailyMode() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.daily_mode_dialog);
        dialog.setTitle("Daily mode");

        View dialogView = LayoutInflater.from(this).inflate(R.layout.daily_mode_dialog, null);
        first_team = dialogView.findViewById(R.id.first_team_button);
        second_team = dialogView.findViewById(R.id.second_team_button);
        draw = dialogView.findViewById(R.id.draw_button);
        first_team_name = dialogView.findViewById(R.id.first_team_name);
        second_team_name = dialogView.findViewById(R.id.second_team_name);
        first_team_image = dialogView.findViewById(R.id.first_team);
        second_team_image = dialogView.findViewById(R.id.second_team);


        dialog.setContentView(dialogView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        first_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write the code here
                dialog.dismiss();
            }
        });

        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write your code here
                dialog.dismiss();
            }
        });

        second_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write your code here
                dialog.dismiss();
            }
        });
    }



}
