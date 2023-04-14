package com.example.footballquiz.ratings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.footballquiz.R;
import com.example.footballquiz.StartActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class RatingsActivity extends AppCompatActivity implements RecyclerViewInterface{

    Button other_modes;
    Button back;
    TabLayout tabLayout;
    ViewPager viewPager;

    ArrayList<ModesModel> modesModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        other_modes = findViewById(R.id.other_modes);
        back = findViewById(R.id.button_back_to_menu);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        vpAdapter.addFragment(new FragmentFasterRatings(),"Faster");
        vpAdapter.addFragment(new FragmentMoreExpensiveRatings(),"Expensive");
        vpAdapter.addFragment(new FragmentGuessThePrice(),"Price");

        viewPager.setAdapter(vpAdapter);


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