package com.example.footballquiz.ratings.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.footballquiz.R;
import com.example.footballquiz.ratings.recyclerView.M_RecyclerViewAdapter;
import com.example.footballquiz.ratings.recyclerView.ModesModel;
import com.example.footballquiz.ratings.recyclerView.RecyclerViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FragmentAssists extends Fragment implements RecyclerViewInterface {

    ArrayList<ModesModel> modesModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assists, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_assists);

        M_RecyclerViewAdapter adapter = new M_RecyclerViewAdapter(getContext(), modesModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query leaderboardQuery = db.collection("users")
                .orderBy("Who has assisted more rating", Query.Direction.DESCENDING)
                .limit(100);



        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentRef = firestore.collection("users").document(userId);

        documentRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists() && documentSnapshot.contains("profileImageUrl")) {
                String imageUrl = documentSnapshot.getString("profileImageUrl");
                leaderboardQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documents = task.getResult().getDocuments();
                            Random rand = new Random();
                            int position = 1;


                            for (DocumentSnapshot document : documents) {
                                String username = document.getString("Username");
                                int rating = document.getLong("Who has assisted more rating").intValue();

                                modesModels.add(new ModesModel(
                                        Integer.toString(position),
                                        username,
                                        Integer.toString(rating),
                                        imageUrl != null ? imageUrl : "",
                                        R.drawable.user_icon
                                ));

                                position++;
                            }

                            // Update the RecyclerView adapter with the leaderboard data
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            } else {
                // The user document doesn't exist or doesn't have a profile picture URL
            }
        }).addOnFailureListener(e -> {
            // Error retrieving user document
        });



        return view;
    }

    @Override
    public void onItemClick(int position) {

    }
}
