package com.example.footballquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.footballquiz.questions.Guess_the_player;
import com.example.footballquiz.questions.Guess_the_price;
import com.example.footballquiz.questions.Who_has_assisted_more;
import com.example.footballquiz.questions.Who_has_scored_more;
import com.example.footballquiz.questions.Who_is_faster;
import com.example.footballquiz.questions.Who_is_more_expensive;

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
        backToStart = findViewById(R.id.button_back_to_start);


        backToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),StartActivity.class);
                startActivity(i);
                finish();
            }
        });

        who_is_faster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Who_is_faster.class);
                startActivity(i);
                finish();
            }
        });

        who_is_more_expensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Who_is_more_expensive.class);
                startActivity(i);
                finish();
            }
        });

        guess_the_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Guess_the_price.class);
                startActivity(i);
                finish();
            }
        });

        guess_the_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Guess_the_player.class);
                startActivity(i);
                finish();
            }
        });

        who_has_scored_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Who_has_scored_more.class);
                startActivity(i);
                finish();
            }
        });

        who_has_assisted_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Who_has_assisted_more.class);
                startActivity(i);
                finish();
            }
        });

    }
}