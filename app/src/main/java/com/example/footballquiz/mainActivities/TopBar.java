package com.example.footballquiz.mainActivities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.footballquiz.R;
import com.example.footballquiz.questions.GuessThePlayer;
import com.example.footballquiz.questions.GuessThePrice;
import com.example.footballquiz.questions.WhoHasAssistedMore;
import com.example.footballquiz.questions.WhoHasScoredMore;
import com.example.footballquiz.questions.WhoIsFaster;
import com.example.footballquiz.questions.WhoIsMoreExpensive;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TopBar extends Fragment {

    TextView userName, rating;
    Button back;
    ImageView profilePicture;

    public TopBar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_bar, container, false);

        userName = view.findViewById(R.id.username_text);
        rating = view.findViewById(R.id.rating_text);
        back = view.findViewById(R.id.button_back);
        profilePicture = view.findViewById(R.id.profile_picture);

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProfileActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });

        loadUserData();

        return view;
    }

    private void loadUserData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            long userRating = documentSnapshot.getLong("Who is faster rating");;
                            String username = documentSnapshot.getString("Username");
                            if(getActivity() instanceof WhoIsFaster) {
                                userRating = documentSnapshot.getLong("Who is faster rating");
                            }else if(getActivity() instanceof WhoIsMoreExpensive){
                                userRating = documentSnapshot.getLong("Who is more expensive rating");
                            }else if(getActivity() instanceof GuessThePrice){
                                userRating = documentSnapshot.getLong("Guess the price rating");
                            } else if(getActivity() instanceof GuessThePlayer){
                                userRating = documentSnapshot.getLong("Guess the player rating");
                            } else if(getActivity() instanceof WhoHasAssistedMore){
                                userRating = documentSnapshot.getLong("Who has assisted more rating");
                            } else if (getActivity() instanceof WhoHasScoredMore) {
                                userRating = documentSnapshot.getLong("Who has scored more rating");
                            }
                            updateTopBar(username, userRating);
                        } else {
                            Toast.makeText(getActivity(), "User data not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Error retrieving user data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void updateTopBar(String username, long userRating) {
        userName.setText(username);
        rating.setText(String.valueOf(userRating));
    }
}
