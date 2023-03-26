package com.example.footballquiz.ratings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.footballquiz.R;
import com.google.android.material.tabs.TabLayout;

public class RatingsActivity_2 extends AppCompatActivity {

    Button back;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings2);

        back = findViewById(R.id.back_modes);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        vpAdapter.addFragment(new FragmentGuessThePlayer(),"PLAYER");
        vpAdapter.addFragment(new FragmentScorer(),"SCORER");
        vpAdapter.addFragment(new FragmentAssists(),"Assists");

        viewPager.setAdapter(vpAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RatingsActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}