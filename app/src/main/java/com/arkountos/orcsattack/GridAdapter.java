package com.arkountos.orcsattack;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> {

    private ArrayList<Tile> mTiles;
    private Context mContext;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.book_img_id);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.d("OnClick", "GridAdapter.java, Got here");
                    if (listener != null){
                        Log.d("OnClick", "GridAdapter.java, Got in first if");
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            Log.d("OnClick", "GridAdapter.java, Got in second if");
                            listener.onItemClick(position);
                        }
                    }
                }
            });
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
        MyViewHolder mvh = new MyViewHolder(v, mListener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tile currentTile = mTiles.get(position);
        String res = "";
        if (currentTile.getCharacter() != null){
            if(GlobalsActivity.Companion.getGlobal_toggle_background_colours_friend_enemy().equals("On")) {
                if (currentTile.getCharacter().getMyclass() == "Monster") {
                    holder.mImageView.setBackgroundColor(mContext.getResources().getColor(R.color.colorEnemy));
                } else {
                    holder.mImageView.setBackgroundColor(mContext.getResources().getColor(R.color.colorFriend));

                }
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
        switch (map_style){
            case "Standard" : Glide.with(mContext).load(id).into(holder.mImageView);
            case "Sepia" : Log.d("In Sepia", "");Glide.with(mContext).load(id).transform(new SepiaFilterTransformation()).into(holder.mImageView);
            case "Greyscale" : Glide.with(mContext).load(id).transform(new GrayscaleTransformation()).into(holder.mImageView);
            default: Glide.with(mContext).load(id).into(holder.mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mTiles.size();
    }
}
