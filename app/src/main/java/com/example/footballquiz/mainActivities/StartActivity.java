package com.example.footballquiz.mainActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballquiz.R;
import com.example.footballquiz.ratings.activities.RatingsActivity;

public class StartActivity extends AppCompatActivity {

    Button startButton;
    TextView ratings, dailyMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startButton = findViewById(R.id.start_button);
        ratings = findViewById(R.id.ratings);
        dailyMode = findViewById(R.id.daily_mode);

        showDailyMode();

        dailyMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDailyMode();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainMenu.class);
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

        // Add the top bar fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment topBarFragment = new TopBar();
        fragmentTransaction.add(R.id.top_bar_layout, topBarFragment);
        fragmentTransaction.commit();

    }

    void showDailyMode() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.daily_mode_dialog);
        dialog.setTitle("Daily mode");

        Button first_team = dialog.findViewById(R.id.first_team_button);
        Button second_team = dialog.findViewById(R.id.second_team_button);
        Button draw = dialog.findViewById(R.id.draw_button);
        TextView first_team_name = dialog.findViewById(R.id.first_team_name);
        TextView second_team_name = dialog.findViewById(R.id.second_team_name);
        ImageView first_team_image = dialog.findViewById(R.id.first_team);
        ImageView second_team_image = dialog.findViewById(R.id.second_team);

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
