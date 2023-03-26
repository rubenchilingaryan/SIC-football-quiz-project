package com.example.footballquiz.ratings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.footballquiz.MainMenu;
import com.example.footballquiz.R;
import com.example.footballquiz.StartActivity;
import com.example.footballquiz.questions.ModesModel;

import java.util.ArrayList;
import java.util.Random;

public class RatingsActivity extends AppCompatActivity implements RecyclerViewInterface{

    Button faster,expensive,guessThePrice;
    Button other_modes;
    Button back;

    ArrayList<ModesModel> modesModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        faster = findViewById(R.id.btn_rating_faster);
        expensive = findViewById(R.id.btn_rating_more_expensive);
        guessThePrice = findViewById(R.id.btn_rating_guess_the_price);

        other_modes = findViewById(R.id.other_modes);

        back = findViewById(R.id.button_back_to_menu);

        faster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,FragmentFasterRatings.class,null)
                        .setReorderingAllowed(true)
                        .addToBackStack("faster")
                        .commit();
            }
        });

        expensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,FragmentMoreExpensiveRatings.class,null)
                        .setReorderingAllowed(true)
                        .addToBackStack("moreExpensive")
                        .commit();
            }
        });

        guessThePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,FragmentGuessThePrice.class,null)
                        .setReorderingAllowed(true)
                        .addToBackStack("guessThePrice")
                        .commit();
            }
        });

        other_modes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RatingsActivity_2.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(i);
                finish();
            }
        });
    }






    @Override
    public void onItemClick(int position) {

    }
}