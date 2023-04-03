package com.example.footballquiz.ratings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.footballquiz.R;

import java.util.ArrayList;
import java.util.Random;


public class FragmentGuessThePrice extends Fragment implements RecyclerViewInterface{

    ArrayList<ModesModel> modesModels = new ArrayList<>();
    int ratingBest = 11979;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guess_the_price, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_guess_the_price);

        setUpModesModels();

        M_RecyclerViewAdapter adapter = new M_RecyclerViewAdapter(getContext(),modesModels,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private void setUpModesModels() {

        Random rand = new Random();


        for (int i = 1; i < 101; i++) {
            modesModels.add(new ModesModel(Integer.toString(i),"user_" + Integer.toString(rand.nextInt(10000)),
                    Integer.toString(ratingBest),R.drawable.user_icon));
            ratingBest-=rand.nextInt(150);
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}