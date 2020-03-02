package com.example.inittrack2;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

//import static androidx.core.graphics.drawable.IconCompat.getResources;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Character> mcharacters = new ArrayList<>();
    private Context mContext;

    // Default Constructor!
    public RecyclerViewAdapter(Context context, ArrayList<Character> characters) {
        mcharacters = characters;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_on_recyclerlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.image_name.setText(mcharacters.get(position).getName());
        Log.d(TAG, "" + mcharacters.get(position).getInitiative_modifier());
        holder.initiative_rolled.setText(("Initiative: " + mcharacters.get(position).getInitiative()));

        String res = "ic_" + mcharacters.get(position).getMyclass().toLowerCase();
        int id = mContext.getResources().getIdentifier(res, "drawable", mContext.getPackageName());
        holder.image.setImageResource(id);
        Log.d(TAG, "class is" + mcharacters.get(position).getMyclass());
        if(mcharacters.get(position).getMyclass().equals("Monster")){
            Log.d(TAG, "INSIDE COLOUR CHANGE");
            holder.parent_layout.setBackgroundColor(Color.parseColor("#d7263d"));
        }
    }

    @Override
    public int getItemCount() {
        return mcharacters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // Parameters go here
        TextView image_name;
        TextView initiative_rolled;
        ImageView image;
        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent_layout = itemView.findViewById(R.id.parent_layout);
            image = itemView.findViewById(R.id.image);
            image_name = itemView.findViewById(R.id.image_name);
            initiative_rolled = itemView.findViewById(R.id.inititative_rolled);
        }
    }
}
