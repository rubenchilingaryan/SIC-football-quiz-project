package com.example.footballquiz.ratings.recyclerView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.footballquiz.R;

import java.util.ArrayList;

public class M_RecyclerViewAdapter extends RecyclerView.Adapter<M_RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private static final int PLACEHOLDER_IMAGE = R.drawable.user_icon;


    Context context;
    ArrayList<ModesModel> modesModels;

    public M_RecyclerViewAdapter(Context context, ArrayList<ModesModel> modesModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context.getApplicationContext();
        this.modesModels = modesModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        View rowLayout = holder.itemView;
        rowLayout.setBackgroundColor(Color.TRANSPARENT);

        holder.number.setText(modesModels.get(position).getNumber());
        holder.username.setText(modesModels.get(position).getUsername());
        holder.rating.setText(modesModels.get(position).getRating());

        String imageUrl = modesModels.get(position).getImageUrl();

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context)
                    .load(imageUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.imageView);
        } else {
            holder.imageView.setImageResource(modesModels.get(position).getImageview());
        }
    }


    @Override
    public int getItemCount() {
        return modesModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView number, username,rating;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            username = itemView.findViewById(R.id.username);
            rating = itemView.findViewById(R.id.rating);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
