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
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FragmentFasterRatings extends Fragment implements RecyclerViewInterface {

    ArrayList<ModesModel> modesModels = new ArrayList<>();
    String currentUserImageUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_who_is_faster_ratings, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_faster);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query leaderboardQuery = db.collection("users")
                .orderBy("Who is faster rating", Query.Direction.DESCENDING)
                .limit(100);

        M_RecyclerViewAdapter adapter = new M_RecyclerViewAdapter(getContext(),modesModels,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentRef = firestore.collection("users").document(userId);

        documentRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists() && documentSnapshot.contains("profileImageUrl")) {
                currentUserImageUrl = documentSnapshot.getString("profileImageUrl");
            }

            leaderboardQuery.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> documents = task.getResult().getDocuments();
                    Random rand = new Random();
                    int position = 1;

                    for (DocumentSnapshot document : documents) {
                        String username = document.getString("Username");
                        int rating = document.getLong("Who is faster rating").intValue();
                        String imageUrl = document.getString("profileImageUrl");

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
            });
        }).addOnFailureListener(e -> {
            // Error retrieving user document
        });

        return view;
    }


    @Override
    public void onItemClick(int position) {

    }
}