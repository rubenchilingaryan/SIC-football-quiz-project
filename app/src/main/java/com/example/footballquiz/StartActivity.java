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

import com.example.footballquiz.ratings.activities.RatingsActivity;

public class StartActivity extends AppCompatActivity {

//    DBHelper dbHelper = new DBHelper(this);

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

//      Bitmap adamaTraoreImage = BitmapFactory.decodeResource(getResources(), R.drawable.adama_traore);
//      dbHelper.addPlayer("Adama Traore", adamaTraoreImage);
//      dbHelper.addPlayer("Antonio Rüdiger", BitmapFactory.decodeResource(getResources(), R.drawable.antonio_rudiger));
//      dbHelper.addPlayer("Casemiro", BitmapFactory.decodeResource(getResources(), R.drawable.casemiro));
//      dbHelper.addPlayer("Cristiano Ronaldo", BitmapFactory.decodeResource(getResources(), R.drawable.cristiano_ronaldo));
//      dbHelper.addPlayer("Darwin Núñez", BitmapFactory.decodeResource(getResources(), R.drawable.darwin_nunez));
//      dbHelper.addPlayer("Erling Haaland", BitmapFactory.decodeResource(getResources(), R.drawable.erling_haaland));
//      dbHelper.addPlayer("Hector Bellerin", BitmapFactory.decodeResource(getResources(), R.drawable.hector_bellerin));
//      dbHelper.addPlayer("James Rodríguez", BitmapFactory.decodeResource(getResources(), R.drawable.james_rodriguez));
//      dbHelper.addPlayer("Jordi Alba", BitmapFactory.decodeResource(getResources(), R.drawable.jordi_alba));
//      dbHelper.addPlayer("Kai Havertz", BitmapFactory.decodeResource(getResources(), R.drawable.kai_havertz));
//      dbHelper.addPlayer("Karim Benzema", BitmapFactory.decodeResource(getResources(), R.drawable.karim_benzema));
//      dbHelper.addPlayer("Kylian Mbappé", BitmapFactory.decodeResource(getResources(), R.drawable.kylian_mbappe));
//      dbHelper.addPlayer("Luka Modrić", BitmapFactory.decodeResource(getResources(), R.drawable.luka_modric));
//      dbHelper.addPlayer("Mohamed Salah", BitmapFactory.decodeResource(getResources(), R.drawable.mohammed_salah));
//      dbHelper.addPlayer("Ousmane Dembélé", BitmapFactory.decodeResource(getResources(), R.drawable.ousmane_dembele));
//      dbHelper.addPlayer("Raphaël Varane", BitmapFactory.decodeResource(getResources(), R.drawable.raphael_varane));
//      dbHelper.addPlayer("Rodrygo", BitmapFactory.decodeResource(getResources(), R.drawable.rodrygo));
//      dbHelper.addPlayer("Ronald Araújo", BitmapFactory.decodeResource(getResources(), R.drawable.ronald_araujo));
//      dbHelper.addPlayer("Sergio Busquets", BitmapFactory.decodeResource(getResources(), R.drawable.sergio_busquets));
//      dbHelper.addPlayer("Vinícius Júnior", BitmapFactory.decodeResource(getResources(), R.drawable.vinicius_junior));
//      dbHelper.addPlayer("Virgil van Dijk", BitmapFactory.decodeResource(getResources(), R.drawable.virgil_van_dijk));
//      dbHelper.addPlayer("Raheem Sterling", BitmapFactory.decodeResource(getResources(), R.drawable.raheem_sterling));
    }

}
