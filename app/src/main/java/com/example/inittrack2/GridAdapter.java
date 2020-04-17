package com.example.inittrack2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> {

    private ArrayList<Tile> mTiles;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.book_img_id);

        }
    }

    public GridAdapter(ArrayList<Tile> tileList, Context context) {
        mTiles = tileList;
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_tile_layout, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tile currentTile = mTiles.get(position);
        String res = "ic_" + currentTile.getContent().getMyclass().toLowerCase();
        int id = mContext.getResources().getIdentifier(res, "drawable", mContext.getPackageName());

        holder.mImageView.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return mTiles.size();
    }
}
