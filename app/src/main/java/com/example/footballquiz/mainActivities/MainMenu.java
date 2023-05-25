package com.example.footballquiz.mainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.footballquiz.R;
import com.example.footballquiz.questions.GuessThePlayer;
import com.example.footballquiz.questions.GuessThePrice;
import com.example.footballquiz.questions.WhoHasAssistedMore;
import com.example.footballquiz.questions.WhoHasScoredMore;
import com.example.footballquiz.questions.WhoIsFaster;
import com.example.footballquiz.questions.WhoIsMoreExpensive;

public class MainMenu extends AppCompatActivity {

    Button who_is_faster,who_is_more_expensive,guess_the_price,guess_the_player,who_has_scored_more,who_has_assisted_more;
    Button backToStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        who_is_faster = findViewById(R.id.who_is_faster);
        who_is_more_expensive = findViewById(R.id.who_is_more_expensive);
        guess_the_price = findViewById(R.id.guess_the_price);
        guess_the_player = findViewById(R.id.guess_the_player);
        who_has_scored_more = findViewById(R.id.who_has_scored_more);
        who_has_assisted_more = findViewById(R.id.who_has_assisted_more);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment topBarFragment = new TopBar();
        fragmentTransaction.add(R.id.top_bar_layout, topBarFragment);
        fragmentTransaction.commit();



        who_is_faster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WhoIsFaster.class);
                startActivity(i);
                finish();
            }
        });

        who_is_more_expensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WhoIsMoreExpensive.class);
                startActivity(i);
                finish();
            }
        });

        guess_the_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GuessThePrice.class);
                startActivity(i);
                finish();
            }
        });

        guess_the_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GuessThePlayer.class);
                startActivity(i);
                finish();
            }
        });

        who_has_scored_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WhoHasScoredMore.class);
                startActivity(i);
                finish();
            }
        });

        who_has_assisted_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WhoHasAssistedMore.class);
                startActivity(i);
                finish();
            }
        });

    }
}