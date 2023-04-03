package com.example.footballquiz;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.footballquiz.R;

public class LevelFragment extends Fragment {

    private ImageView profilePictureView;
    private TextView usernameTextView;
    private TextView ratingTextView;
    private ProgressBar levelBar;

    public LevelFragment() {
        // Required empty public constructor
    }

    public static LevelFragment newInstance(String username, String rating) {
        LevelFragment fragment = new LevelFragment();
        Bundle args = new Bundle();
        args.putString("username", username);
        args.putString("rating", rating);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_bar, container, false);

        profilePictureView = view.findViewById(R.id.profile_picture);
        usernameTextView = view.findViewById(R.id.username_text);
        ratingTextView = view.findViewById(R.id.rating_text);

        AppCompatButton backButton = view.findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to previous activity
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}
