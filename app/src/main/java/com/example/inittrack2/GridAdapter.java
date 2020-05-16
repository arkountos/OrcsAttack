package com.example.inittrack2;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;

import static java.sql.DriverManager.println;

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
        String res = "";
        if (currentTile.getCharacter() != null){
            if (currentTile.getCharacter().getMyclass() == "Monster"){
                holder.mImageView.setBackgroundColor(mContext.getResources().getColor(R.color.colorEnemy));
            }
            else{
                holder.mImageView.setBackgroundColor(mContext.getResources().getColor(R.color.colorFriend));

            }
            res = "ic_" + currentTile.getCharacter().getMyclass().toLowerCase();
        }
        else if (currentTile.getContent() != null){
            res = "map_" + currentTile.getContent().getType().toLowerCase();
        }
        else{
            Log.d("GRID_ADAPTER", "Tile character and ground both null");
            return;
        }

        int id = mContext.getResources().getIdentifier(res, "drawable", mContext.getPackageName());
        String map_style = GlobalsActivity.Companion.getGlobal_styles();
//        Log.d("Map_style", ":" + map_style + ":");
//        if (map_style.equals("Sepia")){
//            Log.d("SEPIA!!!", "");
//        }
//        else{
//            Log.d("NOT SEPIA!!!", "");
//        }
//        Log.d("DONE???", "");
        switch (map_style){
            case "Standard" : Glide.with(mContext).load(id).into(holder.mImageView);
            case "Sepia" : Log.d("In Sepia", "");Glide.with(mContext).load(id).transform(new SepiaFilterTransformation()).into(holder.mImageView);
            case "Greyscale" : Glide.with(mContext).load(id).transform(new GrayscaleTransformation()).into(holder.mImageView);
            default: Glide.with(mContext).load(id).into(holder.mImageView);
        }
//        Glide.with(mContext).load(id).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mTiles.size();
    }
}
